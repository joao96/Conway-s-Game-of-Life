package com.tp1.gol.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound

/**
  * Controle de sons
  */
object Sounds {
    /**
      * Instanciação de todos os sons
      */
    private val coinSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/CoinSound.mp3"))
    private val brickSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/BrickSound.mp3"))
    private val nextSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/NextSound.mp3"))
    private val undoSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/UndoSound.mp3"))
    private val starSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/StarSound.mp3"))
    private val gameSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/GameSound.mp3"))
    private val endSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/EndSound.mp3"))
    private val UnavailableUndoSound : Sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/UnavailableUndoSound.mp3"))

    //Identificadores dos sons
    private var starID : Long = _
    private var gameID : Long = _

    private var mute = false

    /**
      * Botão Mute
      * @return Mute ativo ou não
      */
    def Mute : Boolean = {
        if(mute)
            mute = false
        else
            mute = true
        mute
    }

    def getMute : Boolean = mute

    def playCoinSound() : Unit = if(!mute) coinSound.play(0.5f)

    def playBrickSound() : Unit = if(!mute) brickSound.play(0.5f)

    def playNextSound() : Unit = if(!mute) nextSound.play(0.2f)

    def playUndoSound() : Unit = if(!mute) undoSound.play(0.2f)

    def playUnavailableUndoSound() : Unit = if(!mute) UnavailableUndoSound.play(0.5f)

    def playStarSound() : Unit = {
            starID = starSound.play(0.3f)
            starSound.setLooping(starID, true)
    }

    def stopStarSound() : Unit = {

            starSound.stop(starID)
    }

    def playGameSound() : Unit = {

            gameID = gameSound.play(0.3f)
            gameSound.setLooping(gameID, true)

    }

    def stopGameSound() : Unit = {

            gameSound.stop(gameID)

    }

    def playEndSound() : Unit = {
        endSound.play(0.3f)
    }
}
