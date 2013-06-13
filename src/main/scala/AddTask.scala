package com.cajuncode.tasktrak

import android.os.Bundle
import android.view.{MenuItem, MenuInflater, Menu}
import android.content.Intent
import android.widget.{Toast, CheckBox, EditText}
import com.cajuncode.tasktrak.models.Task
import android.util.Log


/**
 * Created with IntelliJ IDEA.
 * User: alley
 * Date: 6/1/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
class AddTask extends TypedActivity{

  var title:EditText = null //findView(TR.task_title)
  var compete:CheckBox = null //findView(TR.complete)
  var note:EditText = null //findView(TR.task_notes)
  var task:Task = null

  override def onCreate(bundle:Bundle){
    super.onCreate(bundle)
    setContentView(R.layout.add_task)
    title = findView(TR.task_title)
    compete = findView(TR.complete)
    note = findView(TR.task_notes)
    val ext:Bundle = getIntent.getExtras
    if(ext != null){
      val task_id = ext.getLong("TaskID")
      task = Task.load(task_id)
      title.setText(task.title)
      compete.setChecked(task.complete)
      note.setText(task.note)

    }
  }
  override def onCreateOptionsMenu(menu:Menu):Boolean = {
    val inflater:MenuInflater  = getMenuInflater();
    inflater.inflate(R.menu.add_task_menu, menu);
    return true
  }

  override def onOptionsItemSelected(item:MenuItem):Boolean ={
    if (item.getItemId == R.id.save_task){

      if (task == null)
        task = new Task
      task.complete = compete.isChecked
      task.title = title.getText.toString
      task.note = note.getText.toString
      task.save()
      Log.i("AddTask", "Task Saved")
      val toast = Toast.makeText(this, "Task Saved", 10)
      toast.show()
      this.finish()

    }
    if (item.getItemId == R.id.delete_task){
      if(task != null){
        task.delete()
        this.finish()
      }

    }
    return true
  }
}
