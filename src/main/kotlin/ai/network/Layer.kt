package ai.network

import ai.network.ConnectionType.*
import ai.util.random

class Layer(layerSize: Int) {
  val neurons = Array(layerSize, { Node() })

  fun connect(layer: Layer, type: ConnectionType = ALL_TO_ALL) {
    when (type) {
      ALL_TO_ALL -> {
        this.neurons.forEach { n ->
          layer.neurons.forEach { on ->
            n.connect(on, Double.random(-1.0, 1.0))
          }
        }
      }
      ALL_TO_ELSE -> {
        this.neurons.forEach { n ->
          layer.neurons.forEach { on ->
            if (n != on) {
              n.connect(on, Double.random(-1.0, 1.0))
            }
          }
        }
      }
      ONE_TO_ONE -> {
        this.neurons.forEachIndexed { index, n ->
          n.connect(layer.neurons[index], Double.random(-1.0, 1.0))
        }
      }
    }
  }

  fun activate(input: DoubleArray): DoubleArray {
    return input.mapIndexed { index, value ->
      neurons[index].activate(value)
    }.toDoubleArray()
  }
  fun activate(): DoubleArray {
    return neurons.map { it.activate() }.toDoubleArray()
  }
}