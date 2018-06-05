package mnist


/**
 * MNIST database image file. Contains additional header information for the
 * number of rows and columns per each entry.
 */
class MnistImageFile(name: String, mode: String) : MnistDbFile(name, mode, 2051) {
  val rows: Int = readInt()
  val columns: Int = readInt()
}