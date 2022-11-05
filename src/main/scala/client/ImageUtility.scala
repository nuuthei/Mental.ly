package client

import o1.*
import Utility.*
import ImageUtility.*
import scala.collection.mutable.Buffer
import scala.util.Random

object testImageUtility extends App:
  //val s = ColorSettings(Color(170, 190, 255), Color(60, 70, 150), Color(150, 175, 240), Color(175, 185, 240))
  val s = ColorSettings(Color(170, 190, 255), Color(60, 70, 150), Color(172, 182, 250))
  val s2 = PatternSettings(true, 4)
  val p = GenerateButton(150, 50, 4, s, s2)
  show(p)

//This contains useful tools for creating and modifying images for the graphics system
object ImageUtility:
  //color setting defines a generated object's colors
  //main - primary filler color
  //edge - used for the edges of the generated image
  //pattern - color of the generated patterb
  class ColorSettings(val main: Color, val edge: Color, val pattern: Color)
  //settings for pattern generation
  //disabled by setting enabled to false
  //size determines the size of the generated squares
  class PatternSettings(val enabled: Boolean, val size: Int)

  //this method generates a Pic object with the specified settings
  //it is quite computationally expensive, so save and reuse the generated images!
  def GenerateButton(width: Int, height: Int, edgeWidth: Int, settings: ColorSettings, patternSettings: PatternSettings): Pic =
    val colors = Array.fill(width * height)(Color(0, 0, 0))
    var texture = false
    for(y <- 0 to height - 1) do
      texture = !texture
      for(x <- 0 to width - 1) do
        val index = x + y * width
        colors(index) = CalculateColor(x, y, index, settings, patternSettings)
        if(x < edgeWidth || x > width - 1 - edgeWidth || y < edgeWidth || y > height - 1 - edgeWidth) then
          colors(index) = settings.edge
    Pic.fromColors(width, height, colors)

  def CalculateColor(x: Int, y: Int, index: Int, settings: ColorSettings, patternSettings: PatternSettings): Color =
    var color = settings.main
    if(patternSettings.enabled) then
      if(((x / patternSettings.size) % 2 == 0 && (y / patternSettings.size) % 2 == 0) || (((x / patternSettings.size) + 1) % 2 == 0 && ((y / patternSettings.size) + 1) % 2 == 0)) color = settings.pattern

    color

