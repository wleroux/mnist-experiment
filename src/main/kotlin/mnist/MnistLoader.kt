package mnist

import ai.trainer.TrainData
import java.nio.file.Paths
import kotlin.coroutines.experimental.buildSequence


fun mnistTrainData(): Sequence<TrainData> = buildSequence {
  val images = MnistImageFile(Paths.get("resources", "trainImage.idx3-ubyte").toAbsolutePath().toString(), "rw")
  val labels = MnistLabelFile(Paths.get("resources", "trainLabel.idx1-ubyte").toAbsolutePath().toString(), "rw")
  (0 until images.count).forEach {
    val input = DoubleArray(images.rows * images.columns)
    for (j in 0 until images.rows * images.columns) {
      input[j] = images.read().toDouble() / 256.toDouble()
    }

    val target = DoubleArray(10)
    target[labels.readLabel()] = 1.0

    yield(TrainData(input, target))
  }
}