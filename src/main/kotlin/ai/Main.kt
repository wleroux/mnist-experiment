package ai

import ai.network.Perceptron
import ai.trainer.*
import ai.util.meanSquaredError
import java.lang.String.format

fun main(args: Array<String>) {
  val perceptron = Perceptron(2, 3, 3, 7)

  val trainData = mutableListOf<TrainData>()
  trainData.add(TrainData(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0)))
  trainData.add(TrainData(doubleArrayOf(0.0, 1.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
  trainData.add(TrainData(doubleArrayOf(1.0, 0.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
  trainData.add(TrainData(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0)))

  val trainer = Trainer(perceptron)
  trainer.train(trainData, 10000)

  trainData.forEach { data ->
    val output = perceptron.activate(data.input)
    val mse = meanSquaredError(output, data.targetOutput)
    val outputAsString = output.joinToString(", ", "[", "]") { format("%.2f", it) }

    println("$outputAsString: ${format("%.5f", mse)}")
  }
  val average = trainData.map { data ->
    val output = perceptron.activate(data.input)
    meanSquaredError(output, data.targetOutput)
  }.average()
  println(format("Average MSE: %.5f", average))
}