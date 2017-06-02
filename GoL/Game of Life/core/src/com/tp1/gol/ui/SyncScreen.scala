package com.tp1.gol.ui

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{Gdx, InputProcessor, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.tp1.gol.hardcode.{GameController, GameEngine}

/**
  * Classe de sincronização das matrizes
  */
class SyncScreen(var game : GameofLife, batch : SpriteBatch) extends Screen with InputProcessor{


    var camera =  new OrthographicCamera

    this.camera.setToOrtho(false, GameInfo.width, GameInfo.height)

    def loop : Boolean = GameController.loop

    override def render(delta : Float) {
        update()
        draw()
        game.setScreen(new GameScreen(this.game,batch)) //Volta a classe de controle
    }

    /** Atualização das células */
    def update() {
        for( i <- 0 until GameInfo.width / GameInfo.size)
            for( j <- 0 until  GameInfo.height / GameInfo.size)
                game.cells(i)(j).alive = GameEngine.cells(i)(j).isAlive
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
    }

    /** Desenho do jogo*/
    def draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        game.batch.begin()
        game.background.draw(batch,0 ,0 ,GameInfo.width,GameInfo.height)
        for( i <- 0 until GameInfo.width/GameInfo.size) {
            for( j <- 0 until GameInfo.height/GameInfo.size) {
                game.cells(i)(j).draw()
            }
        }
        game.frame.draw(batch,0 ,0 ,GameInfo.width,GameInfo.height)
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
