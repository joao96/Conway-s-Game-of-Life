package com.tp1.gol.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.tp1.gol.ui.{GameInfo, GameofLife}

/**
  * This is the Game Launcher, according to the
  * libgdx game engine. It is basically a refactoring
  * Java => Scala of the original implementation.
  *
  * Learning Unit: a Scala application can be implemented
  * by a singleton (an object definition) having the
  * trait scala.App. A singleton is a class that
  * has only a single instance. Another interesting
  * finding here is that Scala and Java can iteroperate
  * quite well.
  *
  * Running this application in eclipse is
  * straightforward. Just click with the right
  * button in the file, and select the run as
  * option (Scala Application).
  */

object GameOfLife extends scala.App {
    val config = new LwjglApplicationConfiguration()
    config.height = GameInfo.height
    config.width = GameInfo.width
    config.foregroundFPS = 10
    new LwjglApplication(new GameofLife, config)
}