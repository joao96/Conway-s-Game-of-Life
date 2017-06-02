package com.tp1.gol.ui

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Batch, TextureRegion}
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

/**
  * Classe das células da interface
  */
class CellUI(x : Int, y : Int, size : Int, batch : Batch){

    var alive = false

    //Texturas de células vivas e mortas
    var textureAlive = new TextureRegionDrawable(new TextureRegion(new Texture("Images/alive.png")))
    var textureDead = new TextureRegionDrawable(new TextureRegion(new Texture("Images/dead.jpg")))

    def isAlive : Boolean = alive

    //Desenha as células
    def draw() {
        if(alive) {
            textureAlive.draw(batch,x*GameInfo.size,y*size,GameInfo.size,GameInfo.size)
        }
        else{

            textureDead.draw(batch,x*GameInfo.size,y*GameInfo.size, GameInfo.size,GameInfo.size)
        }
    }

    //Realiza a troca de estado
    def swap() {
        if (!alive)
            alive = true
        else
            alive = false
    }

}
