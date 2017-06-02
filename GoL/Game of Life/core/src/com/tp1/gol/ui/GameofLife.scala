package com.tp1.gol.ui

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, SpriteBatch, TextureRegion}
import com.badlogic.gdx.{Game, Gdx}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

/**
  * Classe principal do jogo
  *     Define texturas principais e células da interface
  */
class GameofLife extends Game{

    var shape : ShapeRenderer = _
    var cells :  Array[Array[CellUI]] = _
    var batch : SpriteBatch = _
    var frame : TextureRegionDrawable = _
    var starframe : TextureRegionDrawable = _
    var background : TextureRegionDrawable = _
    var starbackground : TextureRegionDrawable = _
    var sprite : Sprite = _
    var timer : Int = 0
    var marioFont : BitmapFont = _

    override def create () {

        shape = new ShapeRenderer()
        batch = new SpriteBatch()

        shape.setAutoShapeType(true)

        cells = Array.ofDim[CellUI](GameInfo.width/GameInfo.size, GameInfo.height/GameInfo.size)

        //Principais texturas
        frame = new TextureRegionDrawable(new TextureRegion(new Texture("Images/Frame.png")))
        starframe = new TextureRegionDrawable(new TextureRegion(new Texture("Images/FrameStar.png")))
        background = new TextureRegionDrawable(new TextureRegion(new Texture("Images/StatisticsBackground.jpg")))
        starbackground = new TextureRegionDrawable(new TextureRegion(new Texture("Images/StarBackground.png")))

        //Principal fonte
        marioFont = new BitmapFont(Gdx.files.internal("Skins/MarioFont.fnt"),Gdx.files.internal("Skins/MarioFont.png"), false)
        marioFont.setColor(1,1,0,1)
        marioFont.getData.setScale(1.5f)

        //Instanciação das células da interface
        for( i <- 0 until GameInfo.width/GameInfo.size)
            for( j <- 0 until GameInfo.height/GameInfo.size)
                cells(i)(j) = new CellUI(i, j, GameInfo.size, batch)

        //Inicia o Menu
        this.setScreen(new Menu(this, batch))
    }

    override def render () {
        super.render()
    }

    override def dispose() {
        shape.dispose()
    }
}
