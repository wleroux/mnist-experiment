package mnist


import java.io.RandomAccessFile

abstract class MnistDbFile(name: String, mode: String, magicNumber: Int) : RandomAccessFile(name, mode) {
  private val magicNumber: Int = readInt()
  val count: Int = readInt()

  init {
    if (this.magicNumber != magicNumber)
      throw RuntimeException("This MNIST DB file $name should start with the number $magicNumber.")
  }
}