package com.cajuncode.tasktrak

import android.widget.{Toast, Button, EditText}
import android.os.{AsyncTask, Bundle}
import android.view.View
import android.view.View.OnClickListener
import com.cajuncode.tasktrak.models.User

class LoginActivity extends TypedActivity{

  protected lazy val username:EditText = findView(TR.email)
  protected lazy val password:EditText = findView(TR.password)

  override def onCreate(bundle:Bundle){
    super.onCreate(bundle)
    setContentView(R.layout.login)

    val button:Button = findView(TR.sign_in)
    button.setOnClickListener(new OnClickListener {
      def onClick(v: View) {
          val task:AuthAsyncTask = new AuthAsyncTask()
          task.execute("")
      }
    })
  }
  class AuthAsyncTask extends AsyncTask[AnyRef, Int, Boolean]{
    def doInBackground(params: AnyRef*): Boolean = {
      return User.authenticate(username.getText.toString, password.getText.toString)
    }
    override def onPostExecute(b:Boolean){
      if(b){
        Toast.makeText(LoginActivity.this, "Successful", 10).show()
        LoginActivity.this.finish()
      }else{
        Toast.makeText(LoginActivity.this, "Failed try again", 10).show()

      }
    }

  }
}

