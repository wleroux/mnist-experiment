package ai.network.activationfunction

import java.lang.Math.pow
import kotlin.math.E


object TanhActivationFunction : ActivationFunction {
  override fun invoke(value: Double): Double {
    return 2.0 / (1.0 + pow(E, -2.0 * value)) - 1
  }

  override fun derivative(value: Double): Double {
    return 1 - pow(invoke(value), 2.0)
  }
}