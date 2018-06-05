package ai.network.activationfunction


object SigmoidActivationFunction: ActivationFunction {
  override fun invoke(value: Double): Double {
    return 1.0 / (1.0 + Math.exp(-value))
  }

  override fun derivative(value: Double): Double {
    return value * (1 - value)
  }
}