package client

import client.Start
import o1.*
import o1.gui.FontExtensions.textPic
import client.StartButton

trait Menu:
  var myElements: Vector[GraphicsElement]
  def allElements: Vector[GraphicsElement]
  App.changes = true
end Menu

class OpeningMenu extends Menu:
  var myElements = Vector[GraphicsElement](AppButton(Pos(100,100), Pic("client/appIcons/testButton.png")))
  def allElements: Vector[GraphicsElement] = this.myElements
end OpeningMenu

class Start extends Menu:
  var myElements: Vector[GraphicsElement] = Vector(StartButton, GraphicsText(Pos(125, 100), textPic("Hello there.", Black, 20)), GraphicsText(Pos(80, 150), textPic("Press 'Start' to continue.", Black, 20)))
  def allElements: Vector[GraphicsElement] = this.myElements
end Start