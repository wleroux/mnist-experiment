package ai.network.activationfunction

interface ActivationFunction {
  operator fun invoke(value: Double): Double
  fun derivative(value: Double): Double
}

