package client

import o1.*
import Utility.*
import DayUtility.*
import ImageUtility.*
import scala.collection.mutable.Buffer
import scala.util.Random

object DayUtility:
  trait Recommendation(val name: String):
    def compare: Boolean
  class SleepRecommendation(val input: Time) extends Recommendation("Sleep recommendation"):
    def compare = input >= Time(480)
  class FreeRecommendation(val input: Time) extends Recommendation("Free time recommendation"):
    def compare = input >= Time(240)
  class WorkRecommendation(val work: Time, val break: Time) extends Recommendation("Work/break recommendation"):
    def compare = Time(break.totalTime * 5) > work

  def checkRecommendation(activities: Vector[Activity]): Buffer[Boolean] =
    val durations = calculateDurations(activities)
    Buffer(SleepRecommendation(durations(0).time).compare, FreeRecommendation(durations(4).time).compare, WorkRecommendation(durations(2).time + durations(3).time, durations(1).time).compare)

  def calculateDurations(activities: Vector[Activity]): Array[Activity] =
    val activityTypes: Array[Activity] = Array(Sleep(Time(0)), Break(Time(0)), Work(Time(0), "work"), Task(Time(0), "task"), Free(Time(0), "free"))
    var index = 0
    for(i <- activityTypes) do
      activities.filter(a => a.getClass == i.getClass).foreach(b => activityTypes(index) = combineActivities(activityTypes(index), b))
      index += 1
    activityTypes
