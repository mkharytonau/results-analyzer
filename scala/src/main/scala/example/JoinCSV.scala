package example

import java.io.PrintWriter
import scala.concurrent.duration.FiniteDuration
import scala.io.Source
import java.nio.file.{Files, OpenOption, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import java.time.Duration

object JoinCSV extends App {
  val source1 = read(
    "/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race1.csv"
  )
  val source2 = read(
    "/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race2.csv"
  )
  val source3 = read(
    "/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race3.csv"
  )
  val out = Paths.get(
    "/Users/kharivitalij/Projects/results-analyzer/Zaslavl_Multitriathlon_2023/race.csv"
  )

  val formatted = source1 flatMap { s1 =>
    s1 match {
      case _ :: _ :: bib1 :: _ :: _ :: _ :: _ :: _ :: _ :: Nil =>
        val s2 = source2.find {
          case _ :: _ :: bib2 :: _ :: _ :: _ :: _ :: _ :: _ :: Nil =>
            bib1 == bib2
        }
        val s3 = source3.find {
          case _ :: _ :: bib3 :: _ :: _ :: _ :: _ :: _ :: _ :: Nil =>
            bib1 == bib3
        }
        for {
          s2 <- s2
          s3 <- s3
        } yield (s1 ++ s2.drop(3) ++ s3.drop(3)).mkString(",")
    }
  }

  println(formatted.mkString("\n"))
  write(formatted.mkString("\n"))

  def read(filename: String): List[List[String]] =
    Source.fromFile(filename).mkString.split("\n").toList.map(_.split(",").toList)

  def write(str: String): Unit = Files.write(
    out,
    str.getBytes(StandardCharsets.UTF_8),
    StandardOpenOption.CREATE
  )
}
