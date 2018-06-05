package mnist

import ai.network.Perceptron
import ai.trainer.TrainData
import ai.trainer.Trainer
import ai.util.*

import java.nio.file.Paths
import java.util.ArrayList

/**
 * Created by Luecx on 10.08.2017.
 */
object Mnist {

  @JvmStatic
  fun main(args: Array<String>) {
    val perceptron = Perceptron(784, 70, 35, 10)
    val mnistSequence = mnistTrainData()

    val set = mnistSequence.take(1000).toList()
    trainData(perceptron, set, 100, 50, 100)

    val testSet = mnistSequence.drop(1000).take(5000).toList()
    printResults(perceptron, testSet)
  }

  private fun trainData(net: Perceptron, set: List<TrainData>, epochs: Int, loops: Int, batch_size: Int) {
    val trainer = Trainer(net)
    for (e in 0 until epochs) {
      println(">>>>>>>>>>>>>>>>>>>>>>>>>   $e   <<<<<<<<<<<<<<<<<<<<<<<<<<")
      trainer.train(set, loops, batch_size)
      printResults(net, set)
    }
  }

  private fun printResults(net: Perceptron, trainData: List<TrainData>) {
    var correct = 0
    for (data in trainData) {
      val highest = indexOfHighestValue(net.activate(data.input)).toDouble()
      val actualHighest = indexOfHighestValue(data.targetOutput).toDouble()
      if (highest == actualHighest) {
        correct++
      }
    }
    println("RESULT: " + correct + " / " + trainData.size + "  -> " + 100 * correct.toDouble() / trainData.size.toDouble() + " %")
  }
}
