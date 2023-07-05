package example

import java.io.PrintWriter
import scala.concurrent.duration.FiniteDuration
import scala.io.Source
import java.nio.file.{Files, OpenOption, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets

object Brest extends App {
  val source = Source.fromFile("/Users/kharivitalij/Projects/results-analyzer/Brest_2023/sporttiming_raw.csv")
  val out = Paths.get("/Users/kharivitalij/Projects/results-analyzer/Brest_2023/sporttiming.csv")

  try {
    val formatted = source.mkString.split("\n") map { line =>
      val list = line.split(",").toList
      require(list.size == 35)
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