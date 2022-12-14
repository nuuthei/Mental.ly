package client

import o1.*
import client.ImageUtility.GenerateButton

/** An interactive graphics element that executes a function when clicked on */
class AppButton(myPos: Pos, myPic: Pic) extends GraphicsElement(myPos, myPic):

  var lastState = false

  override def update() =
    //if this.isTouched then this.mySize = 0.9 else this.mySize = 1.0
    if this.isTouched then
      this.mySize = 0.9
      if(!lastState) then
        lastState = true
        App.changes = true
    else
      this.mySize = 1.0
      if(lastState) then
        lastState = false
        App.changes = true

  //def isTouched: Boolean = EnvironmentApp.mousePos.distance(myPos) <= (myPic.width / 2.0)
  def isTouched: Boolean =
    val x = myPos.x - myPic.width / 2
    val y = myPos.y - myPic.height / 2
    val mx = App.mousePos.x
    val my = App.mousePos.y
    mx > x && mx < x + myPic.width && my > y && my < y + myPic.height

  def clicked() =
    this.mySize = 0.8
    println("I have been clicked!")

end AppButton