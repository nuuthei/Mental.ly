package client

import o1.*
import Utility.*
import client.ActivityMenu


class ActivityButton(val myPos: Pos, val myPic: Pic, val task: Activity) extends AppButton(myPos, myPic):
  override def clicked(): Unit =
    var pomodoroActivities: Buffer[Activity] = Buffer()
    task match {
      case task1: Task => pomodoroActivities = Pomodoro(task1)
      case _ => pomodoroActivities = Buffer()
    }
    if pomodoroActivities.nonEmpty then
      ActivityMenu.mutateActivities(ActivityMenu.getActivities.indexOf(task), pomodoroActivities.toVector)
end ActivityButton