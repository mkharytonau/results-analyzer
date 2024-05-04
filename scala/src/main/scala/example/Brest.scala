package example

import java.io.PrintWriter
import scala.concurrent.duration.FiniteDuration
import scala.io.Source
import java.nio.file.{Files, OpenOption, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets

object Brest extends App {
  val source = Source.fromFile("/Users/nik.v.kharitonov/Projects/results-analyzer/2024/Drogichin_Duathlon/sporttiming.csv")
  val out = Paths.get("/Users/nik.v.kharitonov/Projects/results-analyzer/2024/Drogichin_Duathlon/sporttiming_formatted.csv")

  try {
    val formatted = source.mkString.split("\n") map { line =>
      val list = line.split(",").toList
      println(list)
      require(list.size == 17)
      val formatted = list.map {
        case duration if duration.contains(":") => formatDuration(duration)
        case other => other
      }

      formatted.mkString(",")
    }

    println(formatted.mkString("\n"))
    write(formatted.mkString("\n"))
  }
  finally {
    source.close()
  }


  def write(str: String): Unit = Files.write(out, str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)

  def formatDuration(str: String): String = {
    val duration = str.replaceAll("\"", "").split(":").toList match {
      case minutes :: seconds :: Nil => s"00:$minutes:$seconds"
      case hours :: minutes :: seconds :: Nil => s"$hours:$minutes:$seconds"
      case _ => "00:00:00"
    }

    duration
  }

}