package client

import o1.*
import o1.gui.*
import client.ImageUtility.GenerateButton
import client.ImageUtility.ColorSettings
import client.ImageUtility.PatternSettings
import client.GraphicsText
import o1.gui.FontExtensions.textPic
import client.App
import client.Menu
import client.OpeningMenu
import client.Start

val pos = Pos(200, 300)
val pic = textPic("Start", White, 40)

object StartButton extends AppButton(pos, GenerateButton(200, 150, 10, buttonTypeA, basicPattern).place(pic, Pos(50,50))):

  override def clicked() =
    this.mySize = 0.8
    println("Change menu view from start to main menu.")
    App.currentMenu = App.menus(2)

end StartButton