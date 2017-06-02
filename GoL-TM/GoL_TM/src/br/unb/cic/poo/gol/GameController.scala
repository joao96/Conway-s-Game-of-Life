package br.unb.cic.poo.gol

/**
  * Relaciona o componente View com o componente Model.
  *
  * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
  */
object GameController {

    var GameEngine : GameEngine = new Conway
    var cells_ = Array.ofDim[Cell](GameEngine.height, GameEngine.width)

    def start {
        GameView.update
    }

    def halt() {
        //oops, nao muito legal fazer sysout na classe Controller
        println("\n \n")
        Statistics.display
        System.exit(0)
    }

    def makeCellAlive(i: Int, j: Int) {
        try {
            GameEngine.makeCellAlive(i, j)
            GameView.update
        }
        catch {
            case ex: IllegalArgumentException => {
                println(ex.getMessage)
            }
        }
    }

    def nextGeneration {
        GameEngine.nextGeneration
        GameView.update
    }

    def selectEngine1 = {
        cells_ = GameEngine.cells
        GameEngine = new Conway
        GameEngine.cells = cells_
        GameView.update
    }
    def selectEngine2 = {
        cells_ = GameEngine.cells
        GameEngine = new BigBang
        GameEngine.cells = cells_
        GameView.update
    }

}