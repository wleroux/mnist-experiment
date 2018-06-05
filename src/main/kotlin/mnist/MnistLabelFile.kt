package mnist

/**
 * MNIST database label file.
 */
class MnistLabelFile(name: String, mode: String) : MnistDbFile(name, mode, 2049) {
  fun readLabel(): Int {
    return readUnsignedByte()
  }
}