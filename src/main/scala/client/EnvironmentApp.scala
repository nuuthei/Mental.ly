package client

import o1.*
import math.{max, min}

import scala.collection.immutable.Vector

import o1.gui.FontExtensions



@main def main() =
  EnvironmentApp.start()

object AppModel:

end AppModel

object EnvironmentApp extends View(AppModel, 0.1, "App"):
  val width = 2000
  val height = 2000
  val viewSize = (width, height)

  val background : Pic = rectangle(400, 400, White)

  def makePic =
    var image = background
    image
  end makePic

  override def onTick() = ???

end EnvironmentApp