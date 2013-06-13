package com.cajuncode.tasktrak.models

import com.activeandroid.Model
import com.activeandroid.annotation.{Column, Table}
import com.activeandroid.query.Select
import java.util

/**
 * Created with IntelliJ IDEA.
 * User: alley
 * Date: 6/1/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */

@Table(name = "Tasks")
class Task extends Model{
  @Column(name = "title")
  var title:String = ""

  @Column(name = "note", notNull = true)
  var note:String = ""

  @Column(name = "complete")
  var complete:Boolean = false

  override def toString():String = this.title

}
object Task {
  def getAll():util.List[Task] ={
    return new Select().from(classOf[Task]).execute()
  }
  def load(task_id:Long):Task = {
    return new Select().from(classOf[Task]).where("Id = ?",task_id.asInstanceOf[java.lang.Long] ).executeSingle().asInstanceOf[Task]
  }
}