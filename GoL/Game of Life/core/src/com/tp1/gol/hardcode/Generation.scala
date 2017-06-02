package com.tp1.gol.hardcode

import GameEngine.{height, width}

/**
  * Classe criada com o intuito de facilitar o armazenamento de uma geração.
  */
class Generation {

    var genCells : Array[Array[Cell]] = Array.ofDim[Cell](width, height)
    var revivedCells = 0
    var killedCells = 0

    //cria-se uma nova matriz de células
    for(i <- 0 until width) {
        for(j <- 0 until height) {
            genCells(i)(j) = new Cell
        }
    }

    //copia a geração atual para essa nova matriz, bem como as estatísticas
    def copyGeneration(thatGen : Generation) : Unit = {
        for(i <- 0 until width) {
            for(j <- 0 until height) {
                this.genCells(i)(j).alive = thatGen.genCells(i)(j).alive
            }
        }
        this.revivedCells = thatGen.revivedCells
        this.killedCells = thatGen.killedCells
    }
}
