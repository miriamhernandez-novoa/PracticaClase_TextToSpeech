package com.example.practicaclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    //Text To Speech
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener{ status ->
            if (status != TextToSpeech.ERROR){
                tts.language =  Locale("es_MX")
            }
        })
        //Boton hablar
        btnLeer.setOnClickListener{
            val toSpeak = textRecibir.text.toString()
            if (toSpeak == ""){
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
        btnAlto.setOnClickListener {
            if (tts.isSpeaking){
                tts.stop()
                //tts.shutdown()
            }else{
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onPause() {
        if (tts.isSpeaking){
            tts.stop()
            //tts.shutdown()
        }

        super.onPause()
    }

    override fun onInit(status: Int) {

    }


}

