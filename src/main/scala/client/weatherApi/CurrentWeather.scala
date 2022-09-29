package client.weatherApi

import APIConnection.*
import client.weatherApi.Utils.*

/** CurrentWeather
 *
 *  This object enables the fetching of the current weather status.
 *  Function getWeatherData fetches the Response object from API
 *
 *  Official documentation for this API can be found here: https://openweathermap.org/current
 *
 *  InitialResponse enables the use of default values since the circe library
 *  doesn't support that yet in Scala 3.
 */
object CurrentWeather:

  /**
   * Gets the current weather data from the API
   * @param coord Coordinates for which to fetch the data
   * @return A response object containing the weather data
   */
  def getWeatherData(coord: Coord): Response =
    val url = s"https://api.openweathermap.org/data/2.5/weather?lat=${coord.lat}&lon=${coord.lon}&appid=${apiKey}&units=metric"
    val data = APIConnection.fetch(url)
    APIConnection.decodeCurrentWeather(data)

  /**
   * Gets the current weather data from the API
   * @param name Name of the place for which to fetch the data
   * @return A response object containing the weather data
   */
  def getWeatherData(name: String): Response =
    val url = s"https://api.openweathermap.org/data/2.5/weather?q=$name&appid=${apiKey}&units=metric"
    val data = APIConnection.fetch(url)
    APIConnection.decodeCurrentWeather(data)

  /**
   * Contains all the weather data as described in https://openweathermap.org/current
   * @param initial
   */

  case class Response(private val initial: InitialResponse):
    val coord = initial.coord
    val weather = initial.weather
    val base = initial.base
    val main = initial.main
    val visibility = initial.visibility
    val wind = initial.wind
    val clouds = initial.clouds
    val dt = initial.dt
    val timezone = initial.timezone
    val id = initial.id
    val name = initial.name
    val cod = initial.cod
    val sys = initial.sys
    val snow = initial.snow match
      case Some(s) => s
      case None => Snow()
    val rain = initial.rain match
      case Some(r) => r
      case None => Rain()

  /**
   * Temperature, pressure and humidity values
   * @param temp temperature
   * @param feels_like human received temperature
   * @param temp_min minimum temperature at the moment
   * @param temp_max maximum temperature at the moment
   * @param pressure atmospheric pressure
   * @param humidity humidity
   */

  case class Main(
                   temp: Double,
                   feels_like: Double,
                   temp_min: Double,
                   temp_max: Double,
                   pressure: Int,
                   humidity: Int,
                 )

  /**
   * Wind
   * @param speed speed
   * @param deg direction
   */
  case class Wind(speed: Double, deg: Int)

  /**
   * Rain (note that the parameter for one hour precipitation has to be given in backticks)
   * @param `1h`
   */
  case class Rain(`1h`: Double = 0.0)

  /**
   * Snow (note that the parameter for one hour snowfall has to be given in backticks)
   * @param `1h`
   */
  case class Snow(`1h`: Double = 0.0)

  /**
   * Country code and times of sunrise and sunset. The first two parameters are API internal and not usable.
   * @param `type`
   * @param id
   * @param country
   * @param sunrise
   * @param sunset
   */
  case class Sys(
                  `type`: Int,
                  id: Int,
                  country: String,
                  sunrise: Int,
                  sunset: Int
                )


  /**
   * This is a raw response which is only needed internally. Check the Response object instead
   */
  case class InitialResponse(
                       coord: Coord,
                       weather: Array[Weather],
                       base: String,
                       main: Main,
                       visibility: Int,
                       wind: Wind,
                       clouds: Clouds,
                       dt: Long,
                       timezone: Int,
                       id: Int,
                       name: String,
                       cod: Int,
                       sys: Sys,
                       snow: Option[Snow],
                       rain: Option[Rain]
                     )

end CurrentWeather
