package com.tp1.gol.hardcode

/**
  * Uma das regras do jogo.
  */
class Conway extends EstrategiaDeDerivacao{
    def cells : Array[Array[Cell]] = GameEngine.cells

    //Recebe a quantidade de vizinhos vivos da célula em questão
    def numberOfNeighborhoodAliveCells(i : Int, j : Int) : Int = GameEngine.numberOfNeighborhoodAliveCells(i,j)

    //Verifica se uma celula deve ser mantida viva.
    //Para manter-se viva deve possir 2 ou 3 vizinhos vivos
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {
        cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
    }

    //Verifica se uma celula deve (re)nascer
    //Para reviver deve possuir 3 vizinhos vivos
    override def shouldRevive(i: Int, j: Int): Boolean = {
        !cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 3)
    }
}
