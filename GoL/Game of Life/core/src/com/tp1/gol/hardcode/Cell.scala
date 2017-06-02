package com.tp1.gol.hardcode

/**
  * Representação de uma célula
  */
class Cell() {

  var alive : Boolean = false

  def isAlive : Boolean = alive

  def kill() : Unit = alive = false

  def revive() : Unit  = alive = true

    def swap() : Unit = {
        if(alive)
            alive = false
        else
            alive = true
    }
}