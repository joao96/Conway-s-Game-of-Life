package com.tp1.gol.hardcode

/**
  * Computa as estatísticas do jogo.
  */
object Statistics {

	private var revivedCells = 0

	private var killedCells = 0

	def getRevivedCells : Int = revivedCells

	def recordRevive() : Unit = revivedCells += 1

	def getKilledCells : Int = killedCells

	def recordKill() : Unit = killedCells += 1

	def resetRevivedCells(x : Int) : Unit = revivedCells = x

	def resetKilledCells(x : Int) : Unit = killedCells = x

	def clear() : Unit = {
        revivedCells = 0
        killedCells = 0
    }
}
