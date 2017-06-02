package com.tp1.gol.hardcode

/**
  * Realiza o controle da geração em questão.
  */

class Memento() {

    var generation : Generation = new Generation

    def Memento(thatGen : Generation) : Unit = this.generation.copyGeneration(thatGen)

    def getGeneration : Generation = generation
}
