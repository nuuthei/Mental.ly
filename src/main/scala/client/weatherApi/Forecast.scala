package client.weatherApi

import APIConnection.*
import client.weatherApi.Utils.*

/** Forecast
 *
 *  This object enables the fetching of forecast data.
 *  Funtion getForecastData fetches the Response object from API
 *
 *  Official documentation for this API can be found here: https://openweathermap.org/forecast5
 *
 *  InitialResponse enables the use of default values since the circe library
 *  doesn't support that yet in Scala 3.
 */
object Forecast :

  def getForecastData(coord: Coord) =
    val url = s"https://api.openweathermap.org/data/2.5/forecast?lat=${coord.lat}&lon=${coord.lon}&appid=${apiKey}&units=metric"
    val data = APIConnection.fetch(url)
    APIConnection.decodeForecast(data)

  def getForecastData(name: String) =
    val url = s"https://api.openweathermap.org/data/2.5/forecast?q=$name&appid=${apiKey}&units=metric"
    val data = APIConnection.fetch(url)
    APIConnection.decodeForecast(data)

  /**
   * Forecast Response
   * The key fields of interest is the "list", which contains the data requested as an array of Forecast objects.
   */

  case class Response(initial: InitialResponse):
    val cod = initial.cod
    val message = initial.message
    val cnt = initial.cnt
    val city = initial.city
    val list = initial.list.map(Forecast(_))

  /**
   * The main forecast data
   * @see https://openweathermap.org/current
   */

  case class Forecast(initial: InitialForecast):
    val dt      = initial.dt
    val main    = initial.main
    val weather = initial.weather
    val clouds  = initial.clouds
    val wind    = initial.wind.gust match
      case Some(g) => Wind(initial.wind.speed, initial.wind.deg, g)
      case None => Wind(initial.wind.speed, initial.wind.deg)
    val visibility = initial.visibility
    val sys     = initial.sys
    val dt_txt  = initial.dt_txt
    val rain    = initial.rain match
      case Some(r) => r
      case None => Rain()
    val snow    = initial.snow match
      case Some(s) => s
      case None => Snow()

  case class City(
                 id: Int,
                 name: String,
                 coord: Coord,
                 country: String,
                 population: Int,
                 timezone: Int,
                 sunrise: Int,
                 sunset: Int
                 )

  /**
   * Rain Forecast(note that the parameter for three hour precipitation has to be given in backticks)
   * @param `1h`
   */

  case class Rain(`3h`: Double = 0.0)

  /**
   * Snow Forecast(note that the parameter for three hour snowfall has to be given in backticks)
   * @param `1h`
   */

  case class Snow(`3h`: Double = 0.0)

  case class Main(
                 temp: Double,
                 feels_like: Double,
                 temp_min: Double,
                 temp_max: Double,
                 pressure: Int,
                 sea_level: Int,
                 grnd_level: Int,
                 humidity: Int,
                 temp_kf: Double
                 )


  /**
   * Wind forecast
   * @param speed speed
   * @param deg direction
   * @param gust gust speed
   */
  case class Wind(speed: Double, deg: Int, gust: Double = 0.0)
  case class Sys(pod: String)

  /**
   * Only used internally.
   */
  case class InitialResponse(
                     cod: Int,
                     message: Int,
                     cnt: Int,
                     list: Array[InitialForecast],
                     city: City
                     )

  /**
   * Only used internally
   */
  case class InitialForecast(
                     dt: Long,
                     main: Main,
                     weather: Array[Weather],
                     clouds: Clouds,
                     wind: InitialWind,
                     visibility: Int,
                     sys: Sys,
                     dt_txt: String,
                     rain: Option[Rain],
                     snow: Option[Snow]
                     )

  // Only used internally
  case class InitialWind(speed: Double, deg: Int, gust: Option[Double])


end Forecast
