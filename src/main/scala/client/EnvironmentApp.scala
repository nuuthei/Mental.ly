package client

import client.weatherApi.CurrentWeather
import client.weatherApi.Utils.*
import client.weatherApi.Forecast
import client.weatherApi.AirPollution
import client.weatherApi.Icon

import o1.*
import math.{max, min}

import scala.collection.immutable.Vector

import o1.gui.FontExtensions



@main def main() =
  EnvironmentApp.start()

class WeatherModel(private val place: String):

  var now : CurrentWeather.Response = CurrentWeather.getWeatherData(place)
  var temp = now.main.temp
  var iconString = now.weather.head.icon

  def getPic : Pic =
    Icon.getIcon(iconString)

  def getPlace : String =
    this.place

  def update(): Unit =
    now  = CurrentWeather.getWeatherData(place)
    iconString = now.weather.head.icon

end WeatherModel

// Sisältää kaupunkien säätiedot WeatherModel-olioissa.
var weatherVector: Vector[WeatherModel] = Vector(
  WeatherModel("Helsinki"),
  WeatherModel("Tampere"),
  WeatherModel("Turku"),
  WeatherModel("Oulu"),
  WeatherModel("Rovaniemi"),
  WeatherModel("Kuopio"),
  WeatherModel("Inari")
)

// Sisältää kaupunkien sijainnit kuvassa. Indeksit ovat kaupungeille samat kuin kaupunkiluettelossa.
var cityPos: Vector[Pos] = Vector(
  Pos(100, 450), // Helsinki
  Pos(90, 370), // Tampere
  Pos(30, 440), // Turku
  Pos(110, 230), // Oulu
  Pos(120, 170), // Rovaniemi
  Pos(160, 320), // Kuopio
  Pos(145, 70)) // Inari

object EnvironmentApp extends View(weatherVector, 0.1, "Suomen sää"):
  val width = 2000
  val height = 2000
  val viewSize = (width, height)

  val background : Pic = Pic("icons/suomi_kartta.png")

  // Tekstin siirto alaoikealle suhteessa sääikoneihin.
  val TextOffsetX: Int = 20
  val TextOffsetY: Int = 20

  def makePic =
    var image = background
    var i: Int = 0

    // Sijoita kartalle säätiedot ikoneineen.
    while i < weatherVector.length do
      val city: String = weatherVector(i).getPlace
      val temp: String = weatherVector(i).temp.round.toString()

      // Sijoitetaan teksti ja lämpötila tähän pisteeseen.
      val position: Pos = Pos(cityPos(i).x + this.TextOffsetX, cityPos(i).y + this.TextOffsetY)

      image = weatherVector(i).getPic.onto(image, cityPos(i))
      image = FontExtensions.textPic(city + ", " + temp + "°C", Black, 12).onto(image, position)
      i += 1
    image
  end makePic

  override def onTick() = weatherVector.foreach(_.update())

end EnvironmentApp