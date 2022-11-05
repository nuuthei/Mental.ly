package client

import o1.*

trait GraphicsElement(private var myPos: Pos, private var myPic: Pic):
  protected var mySize: Double = 1

  /** @return returns this element's picture */
  def pic: Pic = this.myPic.scaleBy(this.mySize)

  /** @return returns this element's current position */
  def pos: Pos = this.myPos

  /** Changes this element's picture to a new one.
   * @param newPic the new picture this element will be set to */
  def changePic(newPic: Pic): Unit = this.myPic = newPic

  /** Changes this element's position by an offset.
   * @param deltaPos the offset this element's position will be changed by */
  def moveBy(deltaPos: Pos): Unit = this.myPos.offset(deltaPos)

  /** Changes this element's position to a new one.
   * @param newPos the position this element will me moved to */
  def moveTo(newPos: Pos): Unit = this.myPos = newPos

  def update(): Unit

end GraphicsElement
