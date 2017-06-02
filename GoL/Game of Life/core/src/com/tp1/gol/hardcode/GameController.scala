package com.tp1.gol.hardcode

/**
  * A GameController é encarregada de realizar a comunicação
  * das operações pedidas pelo usuário com a execução feita na GameEngine.
  */
object GameController{

    //Lê cada linha do arquivo "EstrategiaDeDerivacao, as quais correspondem às regras implementadas do jogo.
    val enginesNames : Iterator[String] = scala.io.Source.fromFile("EstrategiaDeDerivacao").getLines()
    //preenche uma lista com os nomes das Regras
    val engines : List[EstrategiaDeDerivacao] = loadEngines(enginesNames).toList
    // quantidade de undos permitidos
    private val undosAvailable = 10
    var loop : Boolean = false
    // instancia cada Regra
    def loadEngines(enginesNames : Iterator[String]) : Iterator[EstrategiaDeDerivacao] =
        enginesNames.map(e => Class.forName(e).newInstance().asInstanceOf[EstrategiaDeDerivacao])

    // chama a função makeCellAlive da GameEngine, tomando o cuidado para não acessar posições inválidas.
    def makeCellAlive(i: Int, j: Int) {
        try {
            GameEngine.makeCellAlive(i, j)
		}
		catch {
            case ex: IllegalArgumentException => println(ex.getMessage)
		}
    }

    // Chama a nextGeneration da GameEngine
    def nextGeneration() {
        GameEngine.nextGeneration()
    }

    // Seta a Regra que deseja-se utilizar no momento, de acordo com a escolha do usuário.
    def selectEngine(indice : Int) {
        GameEngine.engineAtual = engines(indice)
    }

    // Chama a função Undo da GameEngine
    def undo(): Boolean = {
        GameEngine.undo()
    }

    // Chama a função clear da GameEngine e a função clear da Statistics
    // Responsáveis por limpar a matriz e zerar as estatísticas
    def clear(): Unit = {
        GameEngine.clear()
        Statistics.clear()
    }

    // retorna a quantidade de undos que podem ser realizados
    def getUndosAvaliable: Int = undosAvailable

}
