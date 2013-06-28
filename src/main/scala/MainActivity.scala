package com.cajuncode.tasktrak

import _root_.android.app.Activity
import _root_.android.os.Bundle
import android.view.{View, MenuItem, MenuInflater, Menu}
import android.content.Intent
import android.widget.{AdapterView, ArrayAdapter, ListView}
import com.cajuncode.tasktrak.models.{User, Task}
import android.widget.AdapterView.OnItemClickListener

class MainActivity extends Activity with TypedActivity {

  var taskList:ListView = null

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
    User.context = getApplicationContext()
    taskList = findView(TR.taskListView)
    val adapter:ArrayAdapter[Task] = new ArrayAdapter[Task](this, R.layout.taskrow,R.id.row_title, Task.getAll())
    taskList.setAdapter(adapter)
    taskList.setOnItemClickListener(new OnItemClickListener{
      def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long){
        val task:Task = taskList.getAdapter.getItem(position).asInstanceOf[Task]
        openTask(task)

      }
    })
    //findView(TR.textview).setText("hello, world!")
  }
  override def onCreateOptionsMenu(menu:Menu):Boolean =  {
    val inflater:MenuInflater  = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;

  }

  override def onOptionsItemSelected(item:MenuItem):Boolean ={
    if (item.getItemId == R.id.open_pref){
      val intent:Intent = new Intent(this, classOf[TaskPrefActivity])
      startActivity(intent)
    }
    if (item.getItemId == R.id.add_task){
      openTask(null)
    }
    if (item.getItemId == R.id.open_login){
      val intent:Intent = new Intent(this, classOf[LoginActivity])
      startActivity(intent)
    }
    return true;
  }

  def openTask(task:Task) {
    val intent:Intent = new Intent(this, classOf[AddTask])
    if(task != null){
      intent.putExtra("TaskID", task.getId)
    }
    startActivity(intent)
  }

  override def onResume(){
    super.onResume()
    val adapter:ArrayAdapter[Task] = taskList.getAdapter().asInstanceOf[ArrayAdapter[Task]]
    adapter.clear()
    adapter.addAll(Task.getAll())
  }
}
