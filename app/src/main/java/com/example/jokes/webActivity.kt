package com.example.jokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView


class webActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var webSettings:WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webView=findViewById(R.id.webView)

        webSettings=webView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        webView.loadUrl("http://www.icndb.com/api/")


    }
    fun openJokeActivity (view: View) {
        val openJokeActivity= Intent (this, MainActivity::class.java)
        startActivity(openJokeActivity)


    }

}
