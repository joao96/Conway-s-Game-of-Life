package com.tp1.gol.hardcode

import scala.collection.mutable.ListBuffer
import GameEngine.careTaker

/**
  * Realiza o controle das gerações passadas.
  */

class CareTaker {

    //lista de gerações
    val mementoStack : ListBuffer[Memento] = new ListBuffer[Memento]

    //adiciona uma nova geração no topo da lista, movendo cada item em 1 posição
    def push(gen : Memento) : Unit = {
        val memento_ : Memento = new Memento()

        memento_.generation.copyGeneration(gen.generation)
        mementoStack.prepend(memento_)
    }

    //retorna o primeiro item da lista
    def get : Memento = mementoStack.head

    //retorna uma determinada geração da lista
    def getIndex(i : Int) : Memento = mementoStack.apply(i)

    //retira a geração mais recente
    def pop : Memento = mementoStack.remove(0)

    //checa se a lista está cheia, se estiver, retira-se o último elemento e coloca-se o novo no início
    def addUndo(memento : Memento) : Unit = {
        if(GameController.getUndosAvaliable <= mementoStack.size){
            mementoStack -= mementoStack.last
        }
        careTaker.push(memento)
    }

    // apaga a lista de gerações
    def clear(): Unit = mementoStack.clear()

}
