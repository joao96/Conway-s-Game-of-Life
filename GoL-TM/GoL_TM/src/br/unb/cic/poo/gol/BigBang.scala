package br.unb.cic.poo.gol

/**
  * Created by lucas-mac on 09/05/17.
  */
class BigBang extends GameEngine{
    /* verifica se uma celula deve ser mantida viva */
    def shouldKeepAlive(i: Int, j: Int): Boolean = {
        (cells(i)(j).isAlive) &&
          (numberOfNeighborhoodAliveCells(i, j) == 0 || numberOfNeighborhoodAliveCells(i, j) == 0)
    }

    /* verifica se uma celula deve (re)nascer */
    def shouldRevive(i: Int, j: Int): Boolean = {
        (!cells(i)(j).isAlive) &&
          (numberOfNeighborhoodAliveCells(i, j) == 0)
    }
}
