package client

import client.weatherApi.CurrentWeather
import client.weatherApi.Utils.*
import client.weatherApi.Forecast
import client.weatherApi.AirPollution

import o1.*
import math.{max, min}





@main def main() =
  EnvironmentApp.start()

class WeatherModel(place: String):

  var now : CurrentWeather.Response = CurrentWeather.getWeatherData(place)

  def update(): Unit =
    now = CurrentWeather.getWeatherData(place)

  def humidity: Int = now.main.humidity


val weather = WeatherModel("Madrid")

object EnvironmentApp extends View(weather, 0.1, "Weather"):
  val width = 500
  val height = 500
  val viewSize = (width, height)

  val uglySun = Pic("icons/ugly-test-sun.png")
  val background = rectangle(width, height, White)
  def makePic = uglySun.onto(rectangle(width, min(height, weather.humidity * 5), Red)).onto(background)

  override def onTick() = weather.update()

end EnvironmentApp
