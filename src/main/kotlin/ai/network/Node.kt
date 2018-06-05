package ai.network

import ai.network.activationfunction.*
import ai.util.random


class Node {
  var value: Double = 0.0
  var bias: Double = Double.random(-1.0, 1.0)
  var activationFunction: ActivationFunction = SigmoidActivationFunction

  // Connections
  val inbound: MutableList<Connection> = mutableListOf()
  val outbound: MutableList<Connection> = mutableListOf()

  // Derivative
  val derivative: Double get() = activationFunction.derivative(value)

  fun connect(targetNode: Node, weight: Double): Connection {
    val connection = Connection(this, weight, targetNode)
    outbound += connection
    targetNode.inbound += connection
    return connection
  }

  fun activate(): Double {
    value = activationFunction(bias + inbound.sumByDouble { it.source.value * it.weight })
    return value
  }

  fun activate(value: Double): Double {
    this.value = value
    return this.value
  }
}