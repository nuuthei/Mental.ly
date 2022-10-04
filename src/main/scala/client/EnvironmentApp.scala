package client

import client.weatherApi.CurrentWeather
import client.weatherApi.Utils.*
import client.weatherApi.Forecast
import client.weatherApi.AirPollution
import client.weatherApi.Icon

import o1.*
import math.{max, min}

import scala.collection.immutable.Vector





@main def main() =
  EnvironmentApp.start()

class WeatherModel(private val place: String):

  var now : CurrentWeather.Response = CurrentWeather.getWeatherData(place)
  var iconString = now.weather.head.icon

  def getPic : Pic =
    Icon.getIcon(iconString)

  def getPlace : String =
    this.place

  def update(): Unit =

    now  = CurrentWeather.getWeatherData(place)
    iconString = now.weather.head.icon

  end update

end WeatherModel

var weatherVector: Vector[WeatherModel] = Vector(WeatherModel("Helsinki"), WeatherModel("Tampere"), WeatherModel("Turku"))
var cityPos: Vector[Pos] = Vector(Pos(80, 300), Pos(80, 150), Pos(80, 100))

object EnvironmentApp extends View(weatherVector, 0.1, "Weather"):
  val width = 2000
  val height = 2000
  val viewSize = (width, height)



  val background : Pic = Pic("icons/suomi_kartta.png") // Tähän Suomen kartta

  def makePic =
    var image = background
    image = weatherVector(0).getPic.onto(image, cityPos(0))
    image = weatherVector(1).getPic.onto(image, cityPos(1))
    weatherVector(2).getPic.onto(image, cityPos(2))
  end makePic

  override def onTick() = weatherVector.foreach(_.update())

end EnvironmentApp
