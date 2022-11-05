package client
import o1.gui.FontExtensions.*
import o1.*

class GraphicsText(val myPos: Pos, val myPic: Pic) extends GraphicsElement(myPos, myPic):
  override def update(): Unit = DoNothing
end GraphicsText
