package com.example.jokes

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var URL="https://api.icndb.com/jokes/random"
    var okHttpClient: OkHttpClient = OkHttpClient()
    var jokesCount=0
    lateinit var joke:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadJokesButton.setOnClickListener{loadJokes()}
    }

    fun openWebActivity (view: View) {
        val openWebActivity= Intent (this, webActivity::class.java)
        startActivity(openWebActivity)
    }

    fun loadJokes () {
        joke=""
        jokesCount=countJokes.text.toString().toInt()
        if (jokesCount<1 || jokesCount>1000) {welomeText.text="Enter count in 1 until 1000, or Chuk Norris will be upset"
            jokeText.text=""
        } else {
         for (i in  1..jokesCount) {
             welomeText.text=""
             runOnUiThread {
                 progressBar.visibility = View.VISIBLE
             }
             val request: Request = Request.Builder().url(URL).build()
             okHttpClient.newCall(request).enqueue(object: Callback {
                 override fun onFailure(call: Call?, e: IOException?) {
                 }

                 override fun onResponse(call: Call?, response: Response?) {
                     val json = response?.body()?.string()
                     val txt = (JSONObject(json).getJSONObject("value").get("joke")).toString()
                     joke="${joke}${txt}\n\n"
                     runOnUiThread {
                         progressBar.visibility = View.GONE
                         jokeText.text = joke
                     }
                 }
             })

         }
         }
        }

}

