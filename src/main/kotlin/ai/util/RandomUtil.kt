package ai.util

fun (Double.Companion).random(lowerBound: Double, upperBound: Double): Double {
  return Math.random() * (upperBound - lowerBound) + lowerBound
}

fun indexOfHighestValue(values: DoubleArray): Int {
  var index = 0
  for (i in 1 until values.size) {
    if (values[i] > values[index]) {
      index = i
    }
  }
  return index
}
