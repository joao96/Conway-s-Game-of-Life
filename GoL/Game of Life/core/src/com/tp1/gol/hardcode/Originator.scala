package com.tp1.gol.hardcode

/**
  * Realiza o controle da geração atual com o Memento
  */

class Originator {

    private val generation : Generation = new Generation

    //copia a geração atual antes de realizar o nextGeneration
    def setGeneration(thatGen : Generation) : Unit = this.generation.copyGeneration(thatGen)

    def getGeneration : Generation = generation

    //copia a geração para o memento, retornando-o em seguida
    def saveGenerationToMemento : Memento = {
        val memento_ : Memento = new Memento
        memento_.generation.copyGeneration(this.generation)
        memento_
    }

    //extrai a matriz que deseja-se retirar e armazena em this.generation
    def getGenerationFromMemento(memento : Memento) : Unit = this.generation.copyGeneration(memento.generation)

    def update(memento: Memento) : Unit = {
        getGenerationFromMemento(memento)
        // Seta as estatísitcas para a geração pedida, bem como as células vivas
        Statistics.resetKilledCells(generation.killedCells)
        Statistics.resetRevivedCells(generation.revivedCells)
        GameEngine.resetCells(generation.genCells)
    }
}
