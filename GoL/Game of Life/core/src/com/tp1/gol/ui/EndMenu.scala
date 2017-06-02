package com.tp1.gol.ui

import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch, TextureAtlas, TextureRegion}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.{Gdx, Screen}
import com.tp1.gol.hardcode.Statistics

import scala.collection.mutable.ListBuffer

/**
  * Classe do Menu após finalização do jogo
  */

class EndMenu(game : GameofLife, batch : SpriteBatch) extends Screen{

    private val stage : Stage = new Stage(new ScreenViewport())
    Gdx.input.setInputProcessor(stage)

    var camera : OrthographicCamera = new OrthographicCamera()
    camera.setToOrtho(false, GameInfo.width, GameInfo.height)

    //Fonte utilizada no botão
    val marioFont = new BitmapFont(Gdx.files.internal("Skins/MarioFont.fnt"),Gdx.files.internal("Skins/MarioFont.png"), false)
    marioFont.getData.setScale(1.2f)

    //Imagem de background do Menu
    var background = new TextureRegionDrawable(new TextureRegion(new Texture("Images/StatisticsBackground.jpg")))

    //Statísticas do jogo
    private val killedCells : Int = Statistics.getKilledCells
    private val revivedCells : Int = Statistics.getRevivedCells

    override def render (delta : Float) {
        drawClear()
        stage.act()
        stage.draw()
    }

    override def show(): Unit = {
        //Skin utilizada nos botões
        val Skin : Skin = new Skin()
        val atlas = new TextureAtlas("Skins/craftacular-ui.atlas")
        Skin.addRegions(atlas)
        Skin.load(Gdx.files.internal("Skins/craftacular-ui.json"))


        val table : Table = new Table() //Tabela dos botões

        val button : ListBuffer[Button] = new ListBuffer[Button] //Vetor de botões
        button += new TextButton("Resume", Skin)
        button.head.addListener(
            new InputListener(){
                override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
                    stage.dispose() //Elimina os botões
                    //Verifica se o Mute esta ativo
                    if(!Sounds.getMute)
                        Sounds.playGameSound()
                    //Volta ao jogo
                    game.setScreen(new GameScreen(game, batch))
                }
                override def touchDown (event : InputEvent, x : Float, y: Float, pointer : Int, button : Int) : Boolean = {
                    true
                }
            }
        )

        button += new TextButton("New Game", Skin)
        button(1).addListener(
            new InputListener(){
                override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
                    stage.dispose() //Elimina os botões
                    //Volta ao Menu inicial
                    game.setScreen(new Menu(game, batch))
                }
                override def touchDown (event : InputEvent, x : Float, y: Float, pointer : Int, button : Int) : Boolean = {
                    true
                }
            }
        )

        button += new TextButton("End Game", Skin)
        button(2).addListener(
            new InputListener(){
                override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Unit = {
                    stage.dispose() //Elimina os botões
                    Gdx.app.exit() //Finaliza o jogo
                }
                override def touchDown (event : InputEvent, x : Float, y: Float, pointer : Int, button : Int) : Boolean = {
                    true
                }
            }
        )

        //Adição dos botões na tabela e realização de padding
        table.add(button.head).pad(10)
        table.row()
        table.add(button(1)).pad(10)
        table.row()
        table.add(button(2)).pad(10)

        //Configuração da tabela e adição no Stage
        table.setFillParent(true)
        stage.addActor(table)
    }
    private def drawClear() : Unit = {
        //Limpa a tela
        Gdx.gl.glClearColor(255, 255, 255, 1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.setProjectionMatrix(camera.combined)
        batch.enableBlending()

        batch.begin()

        background.draw(batch,0 ,0 ,GameInfo.width,GameInfo.height) //Desenha a imagem de fundo

        //Imprime as estatísticas
        marioFont.setColor(255,255,255,1)
        marioFont.draw(batch,"COINS COLLECTED  " + revivedCells.toString, 10, GameInfo.height - 10)
        marioFont.draw(batch,"BRICKS REBUILT   " + killedCells.toString, 10, GameInfo.height - 60)

        batch.end()
    }

    override def resume(): Unit = {}
    override def pause(): Unit = {}
    override def hide(): Unit = {}
    override def resize(width: Int, height: Int): Unit = {}
    override def dispose(): Unit = {}
}
