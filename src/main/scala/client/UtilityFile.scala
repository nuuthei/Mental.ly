package client

import Utility.*
import scala.collection.mutable.Buffer
import scala.util.Random

object testUtility extends App:
  val t = Time(123)
  println(t)
  val a = Task(t, "task")
  println(a)

  println(a.breakTask(divisionMethods(0)).mkString("\n"))
  println(GetCurrentTime.toString(0))
  println(Time(123).toString(0))
  println(GetGreeting)

object Utility:
  val divisionMethods: Buffer[DivisionMethod] = Buffer.empty
  val greetingBuffers: Buffer[TimeGreeting] = Buffer.empty
  //Division methods
  divisionMethods += DivisionMethod("Pomodoro", "This metdod divides tasks into cycles of 4 work", Pomodoro)
  //Greeting buffers
  greetingBuffers += TimeGreeting(Time(360), Time(540), Buffer("Good morning!"))
  greetingBuffers += TimeGreeting(Time(540), Time(720), Buffer("Good day!"))
  greetingBuffers += TimeGreeting(Time(720), Time(1080), Buffer("Good afternoon!"))
  greetingBuffers += TimeGreeting(Time(1080), Time(1320), Buffer("Good evening!"))
  greetingBuffers += TimeGreeting(Time(1320), Time(2000), Buffer("Good Night... \nMaybe you should go to bed"))
  greetingBuffers += TimeGreeting(Time(0), Time(360), Buffer("Good Night... \nMaybe you should go to bed"))

  class DivisionMethod(val name: String, val description: String, val function: Task => Buffer[Activity])

  class TimeGreeting(val start: Time, val end: Time, val greetings: Buffer[String])

  class Time(val totalTime: Int):
    def toClock: (Int, Int) =
      val hours = totalTime / 60
      val minutes = totalTime - hours * 60
      (hours, minutes)
    override def toString =
      var hourString = ""
      if(totalTime >= 60) hourString = toClock(0) + "h "
      "" + hourString + toClock(1) + "min"
    def toString(style: Int) =
      if(style == 0) then
        val h: String = "" + toClock(0)
        val m: String = "" + toClock(1)
        "" + "0" * (2 - h.length) + h + ":" + "0" * (2 - m.length) + m
      else
       "error"
    def <(time: Time) = this.totalTime < time.totalTime
    def >(time: Time) = this.totalTime > time.totalTime
    def <=(time: Time) = this.totalTime <= time.totalTime
    def >=(time: Time) = this.totalTime >= time.totalTime

  //25 min työtä, 5 min tauko, toistuu 4 kertaa, neljäs tauko on 15-30 min, tämän jälkeen toistuminen jatkuu
  trait Activity(val name: String, val time: Time):
    override def toString =
      "" + name + " | " + time

  class Break(time: Time) extends Activity("break", time)

  class Task(time: Time, name: String) extends Activity(name, time):
    //pomodoro
    def breakTask(method: DivisionMethod): Buffer[Activity] =
      method.function(this)

  def Pomodoro(task: Task): Buffer[Activity] =
    //initialize
    val taskCycle = Task(Time(25), task.name)
    val breakCycle = Break(Time(5))
    val longBreak = Break(Time(20))
    val taskBuffer : Buffer[Activity] = Buffer.empty
    //loop
    var done = false
    var timeLeft = task.time.totalTime
    var lastTask = false
    var taskCount = 0
    while(!done) do
      if(lastTask && timeLeft > 0) then
        if(taskCount > 3) then
          taskBuffer.addOne(longBreak)
          taskCount = 0
        else
          taskBuffer.addOne(breakCycle)
        lastTask = false
      else if(timeLeft >= taskCycle.time.totalTime) then
        taskCount += 1
        taskBuffer.addOne(taskCycle)
        timeLeft -= taskCycle.time.totalTime
        lastTask = true
      else
        if(timeLeft > 0) then taskBuffer.addOne(Task(Time(timeLeft), task.name))
        done = true

    taskBuffer

  //Additional utility
  def GetCurrentTime: Time =
    val t = java.time.LocalTime.now()
    Time(t.getHour * 60 + t.getMinute)

  def GetGreeting: String =
    val time = GetCurrentTime
    val possible: Buffer[String] = Buffer.empty
    greetingBuffers.filter(a => a.start <= time && a.end > time).foreach(b => possible.addAll(b.greetings))
    possible(Random.between(0, possible.length))










