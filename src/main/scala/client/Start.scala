package client

import client.*
import o1.*
import o1.gui.FontExtensions.textPic


class Start extends Menu:

  private var elements: Vector[GraphicsElement] = Vector(GraphicsText(Pos(50, 100), textPic("Hello there.", Black, 20)))

  def allElements: Vector[GraphicsElement] = this.elements

end Start