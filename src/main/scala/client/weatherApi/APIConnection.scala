package client.weatherApi

import client.weatherApi.Forecast.{InitialResponse => ForecastInitialResponse}
import client.weatherApi.Forecast.{Response        => ForecastResponse}
import client.weatherApi.AirPollution.{Response    => PollutionResponse}
import client.weatherApi.CurrentWeather.{InitialResponse  => WeatherInitialResponse}


import io.circe.parser.decode
import io.circe.*, io.circe.parser.*, io.circe.syntax.*, io.circe.generic.auto.*

import scala.io.Source
import scala.util.{Success, Failure, Using}

/**
 * Handles the API key and connection throttling for the API.
 *
 * You don't need to use this class directly in your program.
 */
object APIConnection:

  /**
   * Fetches and provides the API key which is used by Forecast, CurrentWeather and AirPollution
   */
  lazy val apiKey: String = {
    val apiKeyFile = "apiKey.txt"
    val source = Source.fromFile(apiKeyFile)
    val key = source.getLines().mkString
    source.close()
    key
  }

  /**
   * Calculates the number of calls to the web API
   */
  private var callTimes = 0

  /**
   * This limit is here for testing your program safely. There is a call limit on the web API
   * and this ensures you cannot accidentally get your account blocked while testing. Once
   * we have a working setup, this can be made to throttle the number of calls per minute.
   */
  def apiCallCounter() =
    callTimes += 1
    if callTimes > 50 then
      throw new Exception("Too many calls to API")

  def fetch(url: String): String =
    apiCallCounter()
    val res = Using(Source.fromURL(url)){
      source => source.getLines().mkString
    }

    res match
      case Success(data) => data
      case Failure(e)    =>
        Console.err.println("Connection failed. Remember that the API key takes ~2 hours to activate.")
        throw e

  def decodeForecast(data: String) =
    decode[ForecastInitialResponse](data).toTry match
      case Success(result) => ForecastResponse(result)
      case Failure(error) =>
        Console.err.println("Invalid data received. Remember that the API key takes ~2 hours to activate.")
        error.printStackTrace()
        throw error

  def decodePollution(data: String) =
    decode[PollutionResponse](data).toTry match
      case Success(result) => result
      case Failure(error) =>
        Console.err.println("Invalid data received. Remember that the API key takes ~2 hours to activate.")
        error.printStackTrace()

        throw error

  def decodeCurrentWeather(data: String) =
    decode[WeatherInitialResponse](data).toTry match
      case Success(result) => CurrentWeather.Response(result)
      case Failure(error) =>
        Console.err.println("Invalid data received. Remember that the API key takes ~2 hours to activate.")
        error.printStackTrace()

        throw error
