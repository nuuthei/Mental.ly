package client


import client.Utility.*
import o1.*
import math.{max, min}
import scala.collection.immutable.Vector
import o1.gui.FontExtensions
import client.Menu
import client.Start
import client.OpeningMenu



@main def main() =
  EnvironmentApp.start()

object AppModel:

  val something = Break(Time(100))


end AppModel


object EnvironmentApp extends View(AppModel, 12, "Mental.ly"):

  val menus: Vector[Menu] = Vector(new Start, new OpeningMenu, new ActivityMenu)

  var mousePos = Pos(0,0)

  var currentMenu: Menu = new Start
  //val background : Pic = rectangle(405, 720, LightCyan)
  val background : Pic = ImageUtility.GenerateButton(405, 720, 3, backgroundTypeA, ImageUtility.PatternSettings(true, 15))
  
  private def placePic(placePic: Pic, picOnto: Pic, pos: Pos): Pic =
    picOnto.place(placePic, pos)

  private def placePic(pic: Pic, pos: Pos): Pic =
    background.place(pic, pos)

  private def makeStringPic(someActivity: Activity): Pic =
    FontExtensions.textPic(someActivity.toString)

  def makePic =
    var image = background
    currentMenu.allElements.foreach(a => image = image.place(a.pic, a.pos))
    image
  end makePic

  override def onMouseMove(newMousePos: Pos) =
    this.mousePos = newMousePos

  override def onClick(clickPos: Pos) =
    currentMenu.allElements.foreach(a =>
      a match
        case elem: AppButton if elem.isTouched => elem.clicked()
        case _ => DoNothing
    )

  override def onTick() =
    this.currentMenu.allElements.foreach(_.update())

end EnvironmentApp