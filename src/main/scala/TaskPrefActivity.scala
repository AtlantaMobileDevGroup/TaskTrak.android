package com.cajuncode.tasktrak
import android.preference.PreferenceActivity
import android.os.Bundle

/**
 * Created with IntelliJ IDEA.
 * User: alley
 * Date: 5/30/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
class TaskPrefActivity extends PreferenceActivity {
  override def onCreate(savedInstanceState:Bundle){
    super.onCreate(savedInstanceState)
     addPreferencesFromResource(R.xml.task_preferences)

  }
}
