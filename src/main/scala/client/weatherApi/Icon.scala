package client.weatherApi

import APIConnection.*
import o1.Pic

  /** Icon
 *
 *  This object enables the fetching of small weather icons representing the weather state
 *
 *  Official documentation for these icons can be found here: https://openweathermap.org/weather-conditions
 */
object Icon:

  /**
   * Fetches an Icon from the Web API. (Note that you can use any images just like in Flappy)
   * For example the string "01d" will give you an icon for a cloudless sun.
   *
   * @param iconID The ID of the icon as defined in https://openweathermap.org/weather-conditions
   * @return
   */
  def getIcon(iconID: String): Pic =
    apiCallCounter()
    try
      Pic(s"http://openweathermap.org/img/wn/$iconID@2x.png")
    catch
      case _: Throwable => Pic("./icons/missing.png")
