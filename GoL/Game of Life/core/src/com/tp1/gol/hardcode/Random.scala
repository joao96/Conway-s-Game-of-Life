package com.tp1.gol.hardcode

/**
  * Uma das regras do jogo.
  */
class Random extends EstrategiaDeDerivacao{
    def cells : Array[Array[Cell]] = GameEngine.cells

    //gerador randômico
    val rnd = new scala.util.Random

    //Recebe a quantidade de vizinhos vivos da célula em questão
    def numberOfNeighborhoodAliveCells(i : Int, j : Int) : Int = GameEngine.numberOfNeighborhoodAliveCells(i,j)


    //Verifica se uma celula deve ser mantida viva.
    //Para manter-se viva o ńumero de vizinhos vivos deve estar dentro do resultado gerado pela variável rnd
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {
        cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 0 + rnd.nextInt((15) + 1) || numberOfNeighborhoodAliveCells(i, j) == 0 + rnd.nextInt((15) + 1))
    }

    //Verifica se uma celula deve (re)nascer
    //Para reviver o ńumero de vizinhos vivos deve estar dentro do resultado gerado pela variável rnd
    override def shouldRevive(i: Int, j: Int): Boolean = {
        !cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 0 + rnd.nextInt((15) + 1))
    }
}