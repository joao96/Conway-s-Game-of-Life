package br.unb.cic.poo.gol

/**
  * Created by victor on 07/05/17.
  */
class Conway extends EstrategiaDeDerivacao{
    def cells : Array[Array[Cell]] = GameEngine.cells
    def numberOfNeighborhoodAliveCells(i : Int, j : Int) : Int = GameEngine.numberOfNeighborhoodAliveCells(i,j)

    /* verifica se uma celula deve ser mantida viva */
    override def shouldKeepAlive(i: Int, j: Int): Boolean = {
        cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
    }

    /* verifica se uma celula deve (re)nascer */
    override def shouldRevive(i: Int, j: Int): Boolean = {
        !cells(i)(j).isAlive && (numberOfNeighborhoodAliveCells(i, j) == 3)
    }
}
