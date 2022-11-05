package client

import o1.*
import client.Menu
import o1.gui.FontExtensions.textPic
import Utility.*
import ImageUtility.*
import scala.collection.mutable.Buffer

class ActivityMenu extends Menu:
  //myElements: Vector[GraphicsElement] = Vector.empty
  private val activities: Buffer[Activity] = Buffer.empty
  activities.addOne(Break(Time(15)))
  activities.addOne(Task(Time(25), "work"))

  var myElements = buildActivityUI

  def allElements: Vector[GraphicsElement] = this.myElements

  def buildActivityUI: Vector[GraphicsElement] =
    val elements: Buffer[GraphicsElement] = Buffer.empty

    val startY = 100

    var index = 0
    for(i <- activities) do
      val button = GenerateButton(330, 50, 3, buttonTypeA, basicPattern)
      val text = i.name + " " * (30 - i.name.length) + i.time
      elements.addOne(AppButton(Pos(200, startY + 60 * index), textPic(text, Black, 20).onto(button, Pos(25, 30))))
      index += 1
    elements.toVector

end ActivityMenu
