package com.tp1.gol.ui

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.{Gdx, Screen}
import com.tp1.gol.hardcode.GameController

/**
  * Classe do Menu Inicial
  */
class Menu (var game : GameofLife, batch : SpriteBatch) extends Screen{

    var camera : OrthographicCamera = _

    camera = new OrthographicCamera()
    camera.setToOrtho(false, GameInfo.width, GameInfo.height)

    var loopMenu : Int = 0 //Define o loop das imagens do Menu

    //Instanciação das texturas de fundo
    var golImage1 = new TextureRegionDrawable(new TextureRegion(new Texture("Images/GameofLife1.png")))
    var golImage2 = new TextureRegionDrawable(new TextureRegion(new Texture("Images/GameofLife2.png")))

    //Vericação do mute
    if(!Sounds.getMute)
        Sounds.playGameSound()

    //Apaga padrões passados do jogo
    GameController.clear()

    override def render(delta : Float) {
        update()
    }

    private def update() : Unit = {
        camera.update()
        game.shape.setProjectionMatrix( camera.combined)

        //Inicio do jogo
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new SyncScreen(game, batch))
        }

        //Botão Mute
        if (Gdx.input.isKeyJustPressed(Keys.M)) {
            if(Sounds.Mute)
                Sounds.stopGameSound()
            else
                Sounds.playGameSound()
        }

        //Laço das imagens de fundo
        if(loopMenu <= 10){
            loopMenu += 1
            draw(golImage1)
        }
        if(loopMenu > 10){
            loopMenu += 1
            if(loopMenu == 20)
                loopMenu = 0
            draw(golImage2)
        }
    }

    private def draw(image : TextureRegionDrawable) : Unit = {
        //Apaga a tela
        Gdx.gl.glClearColor(255, 255, 255, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //Desenha a imagem de fundo
        batch.begin()
        image.draw(batch,0,0,GameInfo.width,GameInfo.height)
        batch.end()
    }

    override def show() {}
    override def resize(width : Int, height : Int) {}
    override def pause() {}
    override def resume() {}
    override def hide() {}
    override def dispose() {}
}
