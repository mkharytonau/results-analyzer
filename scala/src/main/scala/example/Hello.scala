package example

import java.io.PrintWriter
import scala.concurrent.duration.FiniteDuration
import scala.io.Source
import java.nio.file.{Files, OpenOption, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import java.time.Duration

object Hello extends App {
  val source = Source.fromFile("/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race3.csv")
  val out = Paths.get("/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race3_2.csv")

  try {
    val formatted = source.mkString.split("\n") map { line =>
      line.split(",").toList match {
        case place :: name :: bib :: total :: swim :: t1 :: bike :: t2 :: run :: Nil => s"$place,$name,$bib,${formatDuration(total)},${formatDuration(swim)},${formatDuration(t1)},${formatDuration(bike)},${formatDuration(t2)},${formatDuration(run)}"
      }
    }

    println(formatted.mkString("\n"))
    write(formatted.mkString("\n"))
  }
  finally {
    source.close()
  }


  def write(str: String): Unit = Files.write(out, str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)

  def formatDuration2(str: String): String = {
    val duration = str.replaceAll("\"", "").split(":").toList match {
      case minutes :: seconds :: Nil => s"00:$minutes:$seconds"
      case hours :: minutes :: seconds :: Nil => s"$hours:$minutes:$seconds"
      case _ => "00:00:00"
    }

    duration
  }

  def formatDuration(str: String): String = {
    val duration = Duration.ofMillis(str.toLong)
    String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart())
  }

}