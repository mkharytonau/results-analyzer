package example

import java.io.PrintWriter
import scala.concurrent.duration.FiniteDuration
import scala.io.Source
import java.nio.file.{Files, OpenOption, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

object MinskTriathlon extends App {

  sealed trait Cell
  final case class Info(value: String) extends Cell {
    override def toString = value
  }
  final case class Time(value: Option[String], row: Int, col: Int)
      extends Cell {
    override def toString = value.getOrElse("")
  }

  val source = Source.fromFile(
    "/Users/kharivitalij/Projects/results-analyzer/Minsk_Triathlon_2023/results_Polovinka_raw.csv"
  )
  val out = Paths.get(
    "/Users/kharivitalij/Projects/results-analyzer/Minsk_Triathlon_2023/results_Polovinka.csv"
  )

  try {
    val header :: results = source.mkString.split("\n").toList
    val parsed: List[(List[Cell], List[(Cell, Cell, Cell)])] =
      results.zipWithIndex map { case (line, row) =>
        val list = line.split(";").toList
        require(list.size == 39)
        val parsed = list.zipWithIndex.map {
          case (duration, col) if col >= 8 && duration.contains(":") =>
            Time(Some(formatDuration(duration)), row, col)
          case ("", col) if col >= 8   => Time(None, row, col)
          case (other, col) if col < 8 => Info(other)
        }
        parsed -> parsed.zip(parsed.tail).zip(parsed.tail.tail).map {
          case ((one, two), three) => (one, two, three)
        }
      }

    val resultCells = parsed.map(_._1)
    val splits = parsed.flatMap(_._2)
    val uniqMissedIndexes = splits.collect { case (_, Time(None, _, col), _) =>
      col
    }.toSet
    val missedIndexesToRatios = uniqMissedIndexes.map { missedCol =>
      missedCol -> splits.collect {
        case (
              Time(Some(one), _, _),
              Time(Some(two), _, col),
              Time(Some(three), _, _)
            ) if col == missedCol =>
          (one, two, three)
      }
    }.toMap

    val missedIndexesRatios = missedIndexesToRatios.mapValues(values =>
      values.map { case (oneStr, twoStr, threeStr) =>
        val one = timeStrToSeconds(oneStr)
        val two = timeStrToSeconds(twoStr)
        val three = timeStrToSeconds(threeStr)
        if (three > one) (two - one).toDouble / (three - one)
        else two.toDouble / three
      }.sum / values.size
    )

    val filled = resultCells.map {
      _.map { cell =>
        cell match {
          case Info(value)            => value
          case Time(Some(time), _, _) => time
          case Time(None, row, col) =>
            val (oneStr, threeStr) = {
              splits.collect {
                case (
                      Time(Some(one), _, _),
                      Time(None, `row`, `col`),
                      Time(Some(three), _, _)
                    ) =>
                  (one, three)
              }.head
            }
            val one = timeStrToSeconds(oneStr)
            val three = timeStrToSeconds(threeStr)
            val ratio = missedIndexesRatios.get(col).get
            val calculated =
              if (three > one) one + (three - one) * ratio 
              else three * ratio

            secondsToTimeStr(calculated.toLong)
        }
      }.mkString(";")
    }
    
    write(header + "\n" + filled.mkString("\n"))
  } finally {
    source.close()
  }

  def timeStrToSeconds(timeStr: String): Long = {
    val hours :: minutes :: seconds :: Nil = timeStr.split(":").toList
    hours.toLong * 60 * 60 + minutes.toLong * 60 + seconds.toLong
  }

  def secondsToTimeStr(seconds: Long): String = {
    val hours = seconds / 3600
    val minutes = (seconds - hours * 3600) / 60
    val seconds0 = seconds - hours * 3600 - minutes * 60
    s"$hours:$minutes:$seconds0"
  }

  def write(str: String): Unit = Files.write(
    out,
    str.getBytes(StandardCharsets.UTF_8),
    StandardOpenOption.CREATE
  )

  def formatDuration(str: String): String = {
    val duration = str.replaceAll("\"", "").split(":").toList match {
      case minutes :: seconds :: Nil          => s"00:$minutes:$seconds"
      case hours :: minutes :: seconds :: Nil => s"$hours:$minutes:$seconds"
      case other =>
        throw new RuntimeException(
          s"Expected: minutes:seconds or hours:minutes:seconds, got: $other"
        )
    }

    duration
  }

}
