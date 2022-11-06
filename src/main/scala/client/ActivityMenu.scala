package client

import o1.*
import client.Menu
import o1.gui.FontExtensions.textPic
import Utility.*
import ImageUtility.*
import scala.collection.mutable.Buffer
import client.ActivityButton
import DayUtility.*

object ActivityMenu extends Menu:
  //myElements: Vector[GraphicsElement] = Vector.empty
  private var activities: Buffer[Activity] = Buffer.empty
  activities.addOne(Sleep(Time(480)))
  activities.addOne(Break(Time(120)))
  activities.addOne(Work(Time(240), "university"))
  activities.addOne(Break(Time(60)))
  activities.addOne(Task(Time(180), "programming"))
  activities.addOne(Food(Time(60)))
  activities.addOne(Task(Time(180), "math"))
  activities.addOne(Free(Time(240), "free time"))

  println(checkRecommendation(activities.toVector))

  def getActivities = this.activities

  def mutateActivities(toDrop: Int, toInclude: Vector[Activity]) =
    val head: Vector[Activity] = this.activities.take(toDrop).toVector
    val tail: Vector[Activity] = this.activities.takeRight(this.activities.length).toVector
    val combined: Vector[Activity] = head ++ toInclude ++ tail
    this.activities = this.activities.empty
    this.activities = combined.toBuffer
    this.myElements = this.myElements.empty
    this.myElements = this.buildActivityUI



  var myElements = buildActivityUI

  def allElements: Vector[GraphicsElement] = this.myElements

  def buildActivityUI: Vector[GraphicsElement] =
    App.changes = true
    
    val elements: Buffer[GraphicsElement] = Buffer.empty

    val startY = 100

    var index = 0
    for(i <- activities) do
      val button = GenerateButton(330, 50, 3, i.colorSettings, basicPattern)
      val text = i.name + " " * (30 - i.name.length) + i.time
      elements.addOne(ActivityButton(Pos(200, startY + 60 * index), (textPic(text, Black, 20).onto(button, Pos(25, 30))), i))
      index += 1
    elements.toVector

end ActivityMenu
