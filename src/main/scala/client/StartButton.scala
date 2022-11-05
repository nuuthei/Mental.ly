package client

import o1.*
import o1.gui.*
import client.ImageUtility.GenerateButton
import client.ImageUtility.ColorSettings
import client.ImageUtility.PatternSettings
import client.GraphicsText
import o1.gui.FontExtensions.textPic
import client.EnvironmentApp
import client.Menu
import client.OpeningMenu
import client.Start

val pos = Pos(200, 300)

object StartButton extends AppButton(pos, GenerateButton(200, 150, 10, ColorSettings(Blue, Cyan, LightPink), PatternSettings(false, 10)).place(textPic("Start", White, 40), Pos(50,50))):

  override def update() =
    if this.isTouched then this.mySize = 0.9 else this.mySize = 1.0

  override def isTouched: Boolean = EnvironmentApp.mousePos.distance(pos) <= (GenerateButton(100, 50, 10, ColorSettings(Blue, Cyan, LightPink), PatternSettings(true, 10)).width / 2.0)

  override def clicked() =
    this.mySize = 0.8
    println("Change menu view from start to main menu.")
    EnvironmentApp.currentMenu = EnvironmentApp.menus(1)

end StartButton