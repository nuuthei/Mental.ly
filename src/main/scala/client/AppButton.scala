package client

import o1.*

/** An interactive graphics element that executes a function when clicked on */
class AppButton(myPos: Pos, myPic: Pic) extends GraphicsElement(myPos, myPic):

  override def update() =
    if this.isTouched then this.mySize = 0.9 else this.mySize = 1.0

  def isTouched: Boolean = EnvironmentApp.mousePos.distance(myPos) <= (myPic.width / 2.0)

  def clicked() =
    this.mySize = 0.8
    println("I have been clicked!")

end AppButton