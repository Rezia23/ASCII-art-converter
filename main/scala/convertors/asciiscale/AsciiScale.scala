package convertors.asciiscale


/**
 * ASCII conversion scale
 * */
object AsciiScale {
  /**
   * Return ascii char for given integer grayscale value
   * */
  def getChar(grayScale: Int): Char = {
    val grayRamp = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`\\'. ';"
    val rampLength = grayRamp.length;
    grayRamp(((rampLength - 1) * grayScale / 255).toInt)
  }
}
