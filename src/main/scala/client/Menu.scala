package client

import o1.*

trait Menu:
end Menu

class OpeningMenu extends Menu:
  private var myElements = Vector[GraphicsElement](AppButton(Pos(100,100), Pic("client/appIcons/testButton.png")))

  def allElements: Vector[GraphicsElement] = this.myElements


end OpeningMenu