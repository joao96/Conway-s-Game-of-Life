package com.tp1.gol.hardcode

/**
  * Possui as funções que deverão ser implementadas
  * pelas regras do jogo.
  */
trait EstrategiaDeDerivacao{
    def shouldRevive(i: Int, j: Int): Boolean
    def shouldKeepAlive(i: Int, j: Int): Boolean
}
