package com.tp1.gol.ui

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.{Gdx, InputProcessor, Screen}
import com.tp1.gol.hardcode.{GameController, GameEngine}

/**
  * Classe de visualização do jogo e processamento de controles
  */
class GameScreen(var game : GameofLife, batch : SpriteBatch) extends Screen with InputProcessor{

    var camera : OrthographicCamera = _
    var touchPos : Vector3 = new Vector3()

    camera = new OrthographicCamera()
    camera.setToOrtho(false, GameInfo.width, GameInfo.height)
    touchPos = new Vector3()

    private def loop = GameController.loop //Captura se opção de play está ativa

    override def render(delta : Float) {
        update()
        draw()

    }

    /**
      * Processamento dos controles
      */
    private def update() {
        camera.update()
        game.shape.setProjectionMatrix(camera.combined)

        //Fim do jogo
        if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
            game.setScreen(new StatisticsMenu(game, batch))
        }

        //Botão play
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            if(loop) {
                GameController.loop = false
                Sounds.stopStarSound()
                if(!Sounds.getMute)
                    Sounds.playGameSound()
            }
            else{
                GameController.loop = true
                Sounds.stopGameSound()
                if(!Sounds.getMute)
                    Sounds.playStarSound()
            }

        }

        //Configuração dos controles com o play ativo
        if(loop) {
            GameController.nextGeneration()
            game.setScreen(new SyncScreen(game,batch))
            if (Gdx.input.isKeyJustPressed(Keys.M)) {
                if(Sounds.Mute)
                    Sounds.stopStarSound()
                else
                    Sounds.playStarSound()
            }
        }
        //Configuração dos controles com o play inativo
        else {
            //Seleção de células
            if (Gdx.input.justTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0)
                camera.unproject(touchPos)

                try {
                    //Swap da célula nas matrizes
                    GameEngine.cells((touchPos.x / GameInfo.size).toInt)((touchPos.y / GameInfo.size).toInt).swap()
                    game.cells((touchPos.x / GameInfo.size).toInt)((touchPos.y / GameInfo.size).toInt).swap()

                    //Som ao clicar nas células
                    if(game.cells((touchPos.x / GameInfo.size).toInt)((touchPos.y / GameInfo.size).toInt).alive)
                        Sounds.playCoinSound()
                    else
                        Sounds.playBrickSound()
                }
                catch {
                    case e: ArrayIndexOutOfBoundsException => Gdx.app.log("Erro", "Fora da área de jogo")
                }
            }

            //Próxima Geração
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
                GameController.nextGeneration()
                Sounds.playNextSound()
                game.setScreen(new SyncScreen(game, batch))
            }

            //Refazer
            if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
                if(GameController.undo()) {
                    Sounds.playUndoSound()
                    game.setScreen(new SyncScreen(game, batch))
                }else {
                    Sounds.playUnavailableUndoSound()
                }
            }

            //Menu de Regras
            if(Gdx.input.isKeyJustPressed(Keys.TAB)){
                game.setScreen(new EngineMenu(game, batch))
            }

            //Mute
            if (Gdx.input.isKeyJustPressed(Keys.M)) {
                if(Sounds.Mute)
                    Sounds.stopGameSound()
                else
                    Sounds.playGameSound()

            }

            //Clear
            if (Gdx.input.isKeyJustPressed(Keys.C)) {
                GameController.clear()
                game.setScreen(new SyncScreen(game, batch))
            }
        }

    }

    /**
      * Desenho do jogo
      */
    private def draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin()

        //Desenho do jogo normal
        if(!loop) {
            game.background.draw(batch, 0, 0, GameInfo.width, GameInfo.height)
            for (i <- 0 until GameInfo.width / GameInfo.size)
                for (j <- 0 until GameInfo.height / GameInfo.size)
                    game.cells(i)(j).draw()
            game.frame.draw(batch, 0, 0, GameInfo.width, GameInfo.height)
        }
        //Desenho do modo play
        else {
            game.starbackground.draw(batch, 0, 0, GameInfo.width, GameInfo.height)
            for (i <- 0 until GameInfo.width / GameInfo.size)
                for (j <- 0 until GameInfo.height / GameInfo.size)
                    game.cells(i)(j).draw()
            game.starframe.draw(batch,0,0,GameInfo.width,GameInfo.height)
        }

        game.batch.end()
    }

    override def show() {}
    override def resize(width : Int, height : Int) {}
    override def pause() {}
    override def resume() {}
    override def hide() {}
    override def dispose() {}

    override def keyUp(keycode: Int): Boolean = false
    override def keyTyped(character: Char): Boolean = false
    override def mouseMoved(screenX: Int, screenY: Int): Boolean = false
    override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false
    override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false
    override def scrolled(amount: Int): Boolean = false
    override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
    override def keyDown(keycode: Int): Boolean = false
}
