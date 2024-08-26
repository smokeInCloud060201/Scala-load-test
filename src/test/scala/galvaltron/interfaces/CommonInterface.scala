package galvaltron.interfaces

import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser._

import java.io.InputStream
import scala.io.Source

object CommonInterface {
  implicit val myInspectionDecoder: Decoder[MyInspection] = deriveDecoder
  implicit val memberInspectionDecoder: Decoder[MemberInspection] = deriveDecoder
  implicit val memberDecoder: Decoder[Member] = deriveDecoder
  implicit val stopWorkDecoder: Decoder[StopWork] = deriveDecoder
  implicit val inspectionsDecoder: Decoder[Inspections] = deriveDecoder
  implicit val chartDecoder: Decoder[Chart] = deriveDecoder
  implicit val dataDecoder: Decoder[Data] = deriveDecoder

  def loadJsonFromFile(fileName: String): Either[io.circe.Error, List[Data]] = {
    val stream: InputStream = getClass.getClassLoader.getResourceAsStream(fileName)
    if (stream == null) {
      Left(io.circe.DecodingFailure(s"File $fileName not found", List.empty))
    } else {
      val source = Source.fromInputStream(stream)
      val jsonString = try source.mkString finally source.close()
      parse(jsonString).flatMap(_.as[List[Data]])
    }
  }
}

