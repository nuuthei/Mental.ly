package client


import client.Utility.*
import o1.*
import math.{max, min}

import scala.collection.immutable.Vector

import o1.gui.FontExtensions



@main def main() =
  EnvironmentApp.start()

object AppModel:

  val something = Break(Time(100))

end AppModel

object EnvironmentApp extends View(AppModel, 0.1, "App"):

  val background : Pic = rectangle(400, 400, White)

  private def placePic(placePic: Pic, picOnto: Pic, pos: Pos): Pic =
    picOnto.place(placePic, pos)

  private def placePic(pic: Pic, pos: Pos): Pic =
    background.place(pic, pos)

  private def makeStringPic(someActivity: Activity): Pic =
    FontExtensions.textPic(someActivity.toString)

  def makePic =
    var image = background.place(makeStringPic(AppModel.something), Pos(100, 100))
    image
  end makePic

  override def onTick() = ???

end EnvironmentApp