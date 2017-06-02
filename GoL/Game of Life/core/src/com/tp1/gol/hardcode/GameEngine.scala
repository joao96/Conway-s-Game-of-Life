package com.tp1.gol.hardcode

import com.tp1.gol.ui.GameInfo

import scala.collection.mutable.ListBuffer

/**
  * A GameEngine é responsável pela execução de comandos
  * passados pela GameController. Realiza as manipulações necessárias
  * para mantar o programa funcionando, além de validar suas operações.
  */

object GameEngine {

    val height: Int = GameInfo.height/GameInfo.size
    val width: Int = GameInfo.width/GameInfo.size
    var engineAtual: EstrategiaDeDerivacao = new Conway
    var cells : Array[Array[Cell]] = Array.ofDim[Cell](width,height)

    val careTaker = new CareTaker
    val originator = new Originator

    var State = 1

    for (i <- 0 until width) {
        for (j <- 0 until height) {
            cells(i)(j) = new Cell
        }
    }

    //Calcula uma nova geracao do ambiente de acordo com a Regra que está sendo utilizada no momento.
    def nextGeneration(): Unit = {

        val mustRevive = new ListBuffer[Cell]
        val mustKill = new ListBuffer[Cell]
        val newGen = new Generation

        //Ao realizar a operação de nextGeneration, deve-se salvar na pilha de matrizes as informações
        //da matriz atual (estatísticas e a posição de cada célula viva), a fim de ao realizar o undo,
        // tudo esteja de acordo com o que era antes.
        newGen.genCells = cells
        newGen.killedCells = Statistics.getKilledCells
        newGen.revivedCells = Statistics.getRevivedCells

        //salva na pilha
        originator.setGeneration(newGen)
        careTaker.addUndo(originator.saveGenerationToMemento)

        // realiza a checagem utilizando a Regra atual de jogo.
        for (i <- 0 until width) {
            for (j <- 0 until height) {
                if (engineAtual.shouldRevive(i, j)) {
                    mustRevive += cells(i)(j)
                }
                else if ((!engineAtual.shouldKeepAlive(i, j)) && cells(i)(j).isAlive) {
                    mustKill += cells(i)(j)
                }
            }
        }

        // atualiza a quantidade de células revividas
        for (c <- mustRevive) {
            c.revive()
            Statistics.recordRevive()
        }
        // atualiza a quantidade de células mortas
        for (c <- mustKill) {
            c.kill()
            Statistics.recordKill()
        }

    }

    /*
       * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
       */
    protected def validPosition(i: Int, j: Int): Boolean = i >= 0 && i < width && j >= 0 && j < height

    //Torna a celula de posicao (i, j) viva, validando cada posição.
    @throws(classOf[IllegalArgumentException])
    def makeCellAlive(i: Int, j: Int): Unit = {
        if (validPosition(i, j)) {
            cells(i)(j).revive()
            Statistics.recordRevive()
        } else {
            throw new IllegalArgumentException
        }
    }

    //Verifica se uma celula na posicao (i, j) estah viva, validando cada posição.
    @throws(classOf[IllegalArgumentException])
    def isCellAlive(i: Int, j: Int): Boolean = {
        if (validPosition(i, j)) {
            cells(i)(j).isAlive
        } else {
            throw new IllegalArgumentException
        }
    }


    // retorna o número de células vivas no jogo na geração em questão
    def numberOfAliveCells() {
        var aliveCells = 0
        for (i <- 0 until height) {
            for (j <- 0 until width) {
                if (isCellAlive(i, j)) aliveCells += 1
            }
        }
    }


    // realiza o cálculo de células vizinhas vivas da célula em questão, utilizando o ambiente infinito.
    def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
        var alive = 0
        for (a <- i - 1 to i + 1) {
            for (b <- j - 1 to j + 1) {
                if ((!(a == i && b == j)) && cells((a + width) % width)((b + height) % height).isAlive) {
                    alive += 1
                }
            }
        }
        //retorna a quantidade de células vizinhas vivas
        alive
    }

    // realiza a operação de undo
    def undo() : Boolean = {
        try {
            //retira da pilha de matrizes a mais recente (torna-se a matriz do momento atul)
            originator.update(careTaker.pop)
        }
        catch {
            // checa se a pilha está fazia ou se tentou fazer um acesso à um índice não existente
            case ex: NoSuchElementException => return false
            case ex: IndexOutOfBoundsException => return false
        }
        true
    }

    // faz uma limpeza na matriz atual, matando todas as células
    def clear(): Unit = {
        for (i <- 0 until width) {
            for (j <- 0 until height) {
                cells(i)(j).alive = false
            }
        }
        // apaga a pilha de matrizes salvas
        careTaker.clear()
    }

    def resetCells(newCells: Array[Array[Cell]]) : Unit = cells = newCells
}
