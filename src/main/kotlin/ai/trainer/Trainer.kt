package ai.trainer

import ai.network.*


class Trainer(val perceptron: Perceptron) {
  private val errorSignal = mutableMapOf<Node, Double>()
  fun train(trainData: List<TrainData>, loops: Int = 1000, batchSize: Int = trainData.size) {
    for (i in 0 until loops) {
      trainData.shuffled().take(batchSize).forEach {
        train(it.input, it.targetOutput, 0.3)
      }
    }
  }

  fun train(input: DoubleArray, targetOutput: DoubleArray, rate: Double) {
    calculate(input)
    propagateError(targetOutput)
    updateWeights(rate)
  }

  private fun calculate(input: DoubleArray): DoubleArray {
    return perceptron.activate(input)
  }

  private fun propagateError(target: DoubleArray) {
    errorSignal.clear()
    perceptron.layers.last().neurons.forEachIndexed { index, neuron ->
      errorSignal[neuron] = (neuron.value - target[index]) * neuron.derivative
    }
    perceptron.layers.reversed().drop(1).forEach { layer ->
      layer.neurons.forEach { neuron ->
        errorSignal[neuron] = neuron.outbound.sumByDouble { connection ->
          connection.weight * errorSignal[connection.target]!!
        } * neuron.derivative
      }
    }
  }

  private fun updateWeights(rate: Double) {
    perceptron.layers.forEach { layer ->
      layer.neurons.forEach { neuron ->
        val delta = -rate * errorSignal[neuron]!!
        neuron.bias += delta
        neuron.inbound.forEach { connection ->
          connection.weight += delta * connection.source.value
        }
      }
    }
  }
}