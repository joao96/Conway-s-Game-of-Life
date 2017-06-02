package com.tp1.gol.ui

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.{Button, Skin, Table, TextButton}
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.tp1.gol.hardcode.{EstrategiaDeDerivacao, GameController}

import scala.collection.mutable.ListBuffer

/**
  * Classe do Menu de Regras do jogo
  */
class EngineMenu(game : GameofLife, batch : SpriteBatch) extends Screen {

    private var stage : Stage = new Stage(new ScreenViewport())
    Gdx.input.setInputProcessor(stage)

    //Skin utilizada nos botões
    val Skin : Skin = new Skin()
    val atlas = new TextureAtlas("Skins/craftacular-ui.atlas")
    Skin.addRegions(atlas)
    Skin.load(Gdx.files.internal("Skins/craftacular-ui.json"))

    //Tabela e lista de botõse
    val table : Table = new Table()
    val button : ListBuffer[Button] = new ListBuffer[Button]

    //Captura a lista de regras disponíveis
    private val engines : List[EstrategiaDeDerivacao] =  GameController.engines

    //Instanciação dos botões
    for(i <- engines.indices) {
        button += new TextButton(engines(i).getClass.getSimpleName, Skin)
        button(i).addListener(
            new InputListener() {
                //Ao pressionar o botão, a nova regra é selecionada e volta-se ao jogo
                override def touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int){
                    game.setScreen(new GameScreen(game, batch))
                }
                override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
                    GameController.selectEngine(i)
                    true
                }
            }
        )
        table.add(button(i)).pad(10) //Padding e adição a tabela
        table.row()
    }
    //Adição da tabela ao stage
    table.setFillParent(true)
    stage.addActor(table)

    override def render (delta : Float) {
        stage.act()
        stage.draw()
    }

    override def show(): Unit = {}

    override def resume(): Unit = {}

    override def pause(): Unit = {}

    override def hide(): Unit = {}

    override def resize(width: Int, height: Int): Unit = {}

    override def dispose(): Unit = {}
}
