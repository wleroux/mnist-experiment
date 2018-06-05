package ai.network.activationfunction


object StepActivationFunction : ActivationFunction {
  override fun invoke(value: Double): Double {
    return when {
      value <= 0.0 -> 0.0
      else -> 1.0
    }
  }

  override fun derivative(value: Double): Double {
    return when {
      value <= 0.0 -> 0.0
      else -> 0.0
    }
  }
}