package client.weatherApi

import APIConnection.*

import client.weatherApi.Utils.*
import client.weatherApi.CurrentWeather

/** AirPollution
 *
 *  This object enables the fetching of air pollution data from the API.
 *  Function getAirPollution fetches the Response object
 *  
 *  Official documentation for this specific API can be found here: https://openweathermap.org/api/air-pollution
 */
object AirPollution {

  /**
   * API Response
   *
   * @param coord Location of the observation
   * @param list List of pollutant observations
   */
  case class Response(coord: Coord, list: Array[PollutionData])

  /**
   * Observation Data
   * @param dt Date and Time in Unix format
   * @param main Main air quality index
   * @param components Pollutant concentrations
   */
  case class PollutionData(
                 dt: Int,
                 main: Main,
                 components: Components
                 )

  /**
   * Container for air quality index.
   *
   * @param aqi Air Quality Index. Possible values: 1, 2, 3, 4, 5. Where 1 = Good, 2 = Fair, 3 = Moderate, 4 = Poor, 5 = Very Poor.
   */
  case class Main(aqi: Int)


  /**
   * Particles and gas concentrations as µg/m^3
   * @param co Carbon monoxide
   * @param no Nitrogen monoxide
   * @param no2 Nitrogen Dioxide
   * @param o3 Ozone
   * @param so2 Sulphur dioxide
   * @param pm2_5 Fine particles matter
   * @param pm10 Coarse particles matter
   * @param nh3 Ammonia
   */
  case class Components(
                       co: Double,
                       no: Double,
                       no2: Double,
                       o3: Double,
                       so2: Double,
                       pm2_5: Double,
                       pm10: Double,
                       nh3: Double
                       )


  def getAirPollutionData(coord: Coord): Response =
    println(s"http://api.openweathermap.org/data/2.5/air_pollution?lat=${coord.lat}&lon=${coord.lon}&appid=${apiKey}")
    val data = APIConnection.fetch(s"http://api.openweathermap.org/data/2.5/air_pollution?lat=${coord.lat}&lon=${coord.lon}&appid=${apiKey}")
    APIConnection.decodePollution(data)

  def getAirPollutionData(name: String): Response =
    val res = CurrentWeather.getWeatherData(name)
    getAirPollutionData(res.coord)

}