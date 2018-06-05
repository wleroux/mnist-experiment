package ai.network.activationfunction


object IdentityActivationFunction : ActivationFunction {
  override fun invoke(value: Double): Double {
    return value
  }

  override fun derivative(value: Double): Double {
    return 1.0
  }
}