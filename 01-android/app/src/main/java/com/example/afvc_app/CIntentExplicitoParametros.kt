package com.example.afvc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)

        val entrenador = intent.getParcelableExtra<BEntrenador>(
            "entrenadorPrincipal"
        )

        boton.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta() {
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Fabrizzio")
        intentDevolverParametros.putExtra("edadModificada", 22)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}