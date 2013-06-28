package com.cajuncode.tasktrak.models

import android.content.{SharedPreferences, Context}
import org.apache.http.client.{ClientProtocolException, HttpClient}
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.{HttpResponse, NameValuePair}
import java.util
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import java.io.{IOException, InputStreamReader, BufferedReader}

object User {
   val EMAIL_KEY:String = "TASK_TRAK_EMAIL"
   val TOKEN_KEY:String = "TASK_TRAK_TOKEN"
   val API_KEY:String = "com.cajuncode.tasktrak"
   val BASE_URL:String = "http://10.0.2.2:3000"
   var context:Context = null

  def getPreferences():SharedPreferences = context.getSharedPreferences(API_KEY, Context.MODE_PRIVATE)


  def email():String = getPreferences().getString(EMAIL_KEY, null)
  def email_=(value:String) = getPreferences().edit().putString(EMAIL_KEY, value).commit()

  def token():String = getPreferences().getString(TOKEN_KEY, null)
  def token_=(value:String) = getPreferences().edit().putString(TOKEN_KEY, value).commit()

  def authenticate(email:String, password:String):Boolean ={
    var success:Boolean = false

    val client:HttpClient = new DefaultHttpClient()
    val post:HttpPost = new HttpPost(BASE_URL + "/users/authenticate")
    val params:util.ArrayList[NameValuePair] = new util.ArrayList[NameValuePair](2)
    params.add(new BasicNameValuePair("username", email))
    params.add(new BasicNameValuePair("password", password))
    post.setEntity(new UrlEncodedFormEntity(params))

    User.email_=(email)

    try{
      val response:HttpResponse = client.execute(post)
      val reader:BufferedReader = new BufferedReader(new InputStreamReader(response.getEntity.getContent))
      User.token_=( reader.readLine())
    }catch{
      case cpe:ClientProtocolException => {}
      case ios:IOException => {}
    }

    return token() != null

  }



}
