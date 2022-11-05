package client

import o1.*
import Utility.*
import ImageUtility.*
import scala.collection.mutable.Buffer
import scala.util.Random

//Color presets
val buttonTypeA: ColorSettings = ColorSettings(Color(170, 190, 255), Color(60, 70, 150), Color(172, 182, 250))
val backgroundTypeA: ColorSettings = ColorSettings(Color(255, 255, 255), Color(230, 240, 255), Color(245, 250, 255))
//Pattern presets
val basicPattern = PatternSettings(true, 8)