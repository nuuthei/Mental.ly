import  Utility.*

object testUtility extends App:
  val t = Time(200)
  println(t)
  val a = Break(t)
  println(a)


object Utility:
  class Time(val totalTime: Int):
    def toClock: (Int, Int) =
      val hours = totalTime / 60
      val minutes = totalTime - hours * 60
      (hours, minutes)
    override def toString =
      "" + toClock(0) + "h " + toClock(1) + "min"

  //25 min työtä, 5 min tauko, toistuu 4 kertaa, neljäs tauko on 15-30 min, tämän jälkeen toistuminen jatkuu
  trait Activity(name: String, time: Time):
    override def toString =
      "" + name + " | " + time

  class Break(time: Time) extends Activity("break", time)


