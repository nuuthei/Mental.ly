package client

import Utility.*
import scala.collection.mutable.Buffer


object testUtility extends App:
  val t = Time(123)
  println(t)
  val a = Task(t, "task")
  println(a)

  println(a.breakTask(divisionMethods(0)).mkString("\n"))

object Utility:
  val divisionMethods: Buffer[DivisionMethod] = Buffer.empty
  divisionMethods += DivisionMethod("Pomodoro", "This metdod divides tasks into cycles of 4 work", Pomodoro)

  class DivisionMethod(val name: String, val description: String, val function: Task => Buffer[Activity])

  class Time(val totalTime: Int):
    def toClock: (Int, Int) =
      val hours = totalTime / 60
      val minutes = totalTime - hours * 60
      (hours, minutes)
    override def toString =
      var hourString = ""
      if(totalTime >= 60) hourString = toClock(0) + "h "
      "" + hourString + toClock(1) + "min"

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










