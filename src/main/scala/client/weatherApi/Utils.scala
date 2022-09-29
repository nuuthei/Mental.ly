package client.weatherApi

import java.text.SimpleDateFormat
import java.util.Date

/** Utils
 *
 *  This object contains a few useful things, mainly the functions
 *  for transforming unix time into more useful formats.
 */
object Utils:

  case class Coord(lat: Double, lon: Double)
  
  case class Clouds(all: Int)
  
  case class Weather(id: Int, main: String, description: String, icon: String)

  def getDay(dt: Long) =
    val date = new Date(dt*1000L)
    val sdf  = new SimpleDateFormat("d.M.y")
    sdf.format(date)

  def getHour(dt: Long) =
    val date = new java.util.Date(dt*1000L)
    val sdf = new SimpleDateFormat("H.00")
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"))
    sdf.format(date)
    
  def getDayName(dt: Long) =
    val date = new Date(dt*1000L)
    val sdf  = new SimpleDateFormat("E")
    sdf.format(date)

end Utils