package client

import o1.*
import Utility.*
import ImageUtility.*
import scala.collection.mutable.Buffer
import scala.util.Random

class Day(var activities: Buffer[Activity]):
  val activityTypes: Vector[Activity] = Vector(Sleep(Time(0)), Work(Time(0), "work"), Task(Time(0), "task"), Free(Time(0), "free"))

  for(i <- activities) do
    i match
      case a: Sleep => activityTypes()