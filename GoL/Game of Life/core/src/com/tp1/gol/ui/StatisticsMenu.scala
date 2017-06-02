package com.tp1.gol.ui

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch, TextureRegion}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.{Gdx, Screen}
import com.tp1.gol.hardcode.Statistics


/**
  * Created by lucas-mac on 19/05/17.
  */
class StatisticsMenu (var game : GameofLife, batch : SpriteBatch) extends Screen{

    var camera : OrthographicCamera = new OrthographicCamera()
    camera.setToOrtho(false, GameInfo.width, GameInfo.height)

    val marioFont = new BitmapFont(Gdx.files.internal("Skins/MarioFont.fnt"),Gdx.files.internal("Skins/MarioFont.png"), false)


    var background = new TextureRegionDrawable(new TextureRegion(new Texture("Images/StatisticsBackground.jpg")))

    private val killedCells : Int = Statistics.getKilledCells
    private val revivedCells : Int = Statistics.getRevivedCells

    Sounds.stopGameSound()
    if(!Sounds.getMute)
        Sounds.playEndSound()

    override def render(delta : Float) {
        update()
    }

    private def update() : Unit = {
        camera.update()
        game.shape.setProjectionMatrix( camera.combined)

        draw(background)

        if (Gdx.input.isKeyJustPressed(Keys.M))
            Sounds.Mute

        if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
            game.setScreen(new EndMenu(game, batch))
        }
    }
    private def draw(image : TextureRegionDrawable) : Unit = {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.setProjectionMatrix(camera.combined)
        batch.enableBlending()

        batch.begin()
        marioFont.getData.setScale(1.2f)
        image.draw(batch,0 ,0 ,GameInfo.width,GameInfo.height)

        marioFont.setColor(255,255,255,1)
        marioFont.draw(batch,"COINS COLLECTED  " + revivedCells.toString, 10, GameInfo.height - 10)
        marioFont.draw(batch,"BRICKS REBUILT   " + killedCells.toString, 10, GameInfo.height - 60)
        marioFont.getData.setScale(1f)
        marioFont.draw(batch,"press ANY key to continue", GameInfo.width/3, GameInfo.height/2)

        batch.end()
    }


    override def show() {}
    override def resize(width : Int, height : Int) {}
    override def pause() {}
    override def resume() {}
    override def hide() {}
    override def dispose() {}
}
