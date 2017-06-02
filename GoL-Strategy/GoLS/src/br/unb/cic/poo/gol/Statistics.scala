package br.unb.cic.poo.gol

/**
  * Classe usada para computar as estatisticas
  * do GoL.
  *
  * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br)
  */
object Statistics {

	private var revivedCells = 0
	private var killedCells = 0


	def getRevivedCells : Int = revivedCells

	def recordRevive() : Unit = revivedCells += 1

	def getKilledCells : Int = killedCells

	def recordKill() : Unit = killedCells += 1

	def display() : Unit =  {
		println("=================================")
		println("           Statistics            ")
		println("=================================")
		println("Revived cells: " + revivedCells)
		println("Killed cells: " + killedCells)
		println("=================================")
	}

}