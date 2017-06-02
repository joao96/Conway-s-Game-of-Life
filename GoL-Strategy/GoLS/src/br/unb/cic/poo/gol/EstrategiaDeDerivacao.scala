package br.unb.cic.poo.gol

/**
  * Created by victor on 07/05/17.
  */
trait EstrategiaDeDerivacao{

    def shouldRevive(i: Int, j: Int): Boolean
    def shouldKeepAlive(i: Int, j: Int): Boolean

}
