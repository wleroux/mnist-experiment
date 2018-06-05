package ai.network.activationfunction


object ReluActivationFunction : ActivationFunction {
  override fun invoke(value: Double): Double {
    return when {
      value < 0.0 -> 0.0
      else -> value
    }
  }

  override fun derivative(value: Double): Double {
    return when {
      value < 0.0 -> 0.0
      else -> 1.0
    }
  }
}