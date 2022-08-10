package helpers

import java.io.File
import java.util.UUID
import scala.io.Source

/*
 * Used code is from BI-OOP lab.
 */
trait TestWithFiles {
  val testFolder = "src/test/scala/testFiles/"

  def ensureCreated(filePath: String): Unit = {
    val file = new File(filePath)

    ensureDeleted(filePath)

    if (!file.createNewFile())
      throw new Exception("Could not create " + filePath)
  }

  def ensureDeleted(filePath: String): Unit = {
    val file = new File(filePath)
    if (file.exists())
      if (!file.delete())
        throw new Exception("Could not delete " + filePath)
  }

  def getTestFile: String = testFolder + UUID.randomUUID().toString + ".txt"

  def assertFileContent(filePath: String, content: String): Unit = {
    val source = Source.fromFile(filePath)
    val text = source.mkString

    source.close()

    assert(text == content)
  }
}
