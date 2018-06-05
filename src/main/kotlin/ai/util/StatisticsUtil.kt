package ai.util


fun meanSquaredError(actual: DoubleArray, target: DoubleArray): Double {
  val sum = target.indices.sumByDouble { Math.pow(target[it] - actual[it], 2.0) }
  return sum / target.size.toDouble()
}