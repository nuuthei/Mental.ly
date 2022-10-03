package o1.gui

import o1.gui.PicHistory.op.Create
import o1.gui.{Color => O1Color}
import o1.gui.colors.*

import java.awt.Graphics2D
import java.awt.Font
import java.awt.image.BufferedImage

object FontExtensions:

  o1.util.smclInit()

  // Needed for calculating image sizes from font metrics
  private val baseGraphics = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_ARGB).getGraphics

  def textPic(
      text: String,
      color:O1Color = Black,
      fontSize:Int = 60,
      fontFamily: String = "SansSerif"): o1.gui.Pic =

    val font = java.awt.Font(fontFamily, Font.PLAIN, fontSize)

    val fontMetrics = baseGraphics.getFontMetrics(font)
    val maxAscent   = fontMetrics.getMaxAscent
    val maxDescent  = fontMetrics.getMaxAscent
    val totalHeight = fontMetrics.getHeight
    val trueWidth   = fontMetrics.stringWidth(text)

    val image = new BufferedImage(trueWidth, totalHeight, BufferedImage.TYPE_INT_ARGB)
    val g: Graphics2D = image.getGraphics.asInstanceOf[Graphics2D]

    g.setColor(color.toSwingColor)
    g.setFont(font)
    g.drawString(text, 0, maxDescent)

    val possiblePic = for
      bitmap    <- smcl.infrastructure.jvmawt.RichBufferedImage(image).toSMCLBitmap
      anchor     = o1.world.objects.Anchor.Absolute.apply(Pos(0, maxAscent))
      newHistory = PicHistory(Create(method = "Pic.generate", simpleDescription = s"Pic with the text: $text"))
    yield o1.gui.Pic(smclContent = bitmap, anchor, newHistory)

    possiblePic.getOrElse(Pic.emptyCanvas(1, 1 ,Black))

end FontExtensions

