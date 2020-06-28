package com.mp.speechtotext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * this activity is created on 28-6-20 by Mrinmoy
 * **/

class MainActivity : AppCompatActivity() {

    private val REQ_CODE=325
    private val TAG="MainActivityTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivMic.setOnClickListener {
            val intent= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            try {
                startActivityForResult(intent,REQ_CODE)
            }catch (e:ActivityNotFoundException){
                Toast.makeText(this,"bro your phone is fucked up",Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onCreate: log-> $e")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                    tvShowText.text=result[0] // Results are returned in descending order of speech recognizer confidence.so position zero should be the most accurate text.
                    println(" all strings -> ${listOf(result)}")
                }
            }
        }
    }
}