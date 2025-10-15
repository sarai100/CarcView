package com.example.myapplication

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.jetbrains.annotations.ApiStatus
import java.util.Locale
//prueba 2
//sarai hernandez rios ya hice mi cambiooooo 
//INTENTANDO HACER EL PULL REQUESTS


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var text=findViewById<EditText>(R.id.editTextText).text.toString()
        tts= TextToSpeech(this,this)

        findViewById<Button>(R.id.button).setOnClickListener {
        if(text.isNotEmpty()){
            Toast.makeText(this,"ingresa texto", Toast.LENGTH_SHORT).show()
        }else{
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
        }
        }

    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            tts?.setLanguage(Locale("ES"))
            findViewById<TextView>(R.id.textView).text = "TextToSpeech disponible"
        }else{
            findViewById<TextView>(R.id.textView).text = "TextToSpeech no disponible"
        }
        findViewById<ProgressBar>(R.id.progressBar).visibility=View.GONE
    }


}
