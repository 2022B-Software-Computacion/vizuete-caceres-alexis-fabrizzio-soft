package com.example.exameniib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ServicioAdapter : AppCompatActivity() {

    val firestoreDB = FireStoreDB()
    lateinit var idVivienda: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicio)

        val nombre_servicio_input = findViewById<EditText>(R.id.id_nombre_servicio_input)
        val fecha_instalacion_input = findViewById<EditText>(R.id.id_fecha_instalacion_input)
        val precio_servicio_input = findViewById<EditText>(R.id.id_precio_servicio_input)
        val crear_servicio_button = findViewById<Button>(R.id.btn_crear_servicio)

        idVivienda = intent.getStringExtra("idVivienda")!!
        val servicio = intent.getParcelableExtra<Servicio>("servicio")

        if (servicio != null){
            nombre_servicio_input.setText(servicio.nombre_servicio)
            fecha_instalacion_input.setText(servicio.fecha_instalacion)
            precio_servicio_input.setText(servicio.precio_servicio.toString())
            crear_servicio_button.setOnClickListener {
                val nombre_servicio = nombre_servicio_input.text.toString()
                val fecha_instalacion = fecha_instalacion_input.text.toString()
                val precio_servicio = precio_servicio_input.text.toString().toInt()
                val newServicio = Servicio(servicio.id_servicio ,nombre_servicio, fecha_instalacion, precio_servicio)
                firestoreDB.actualizarServicio(idVivienda,newServicio,{
                    setResult(Activity.RESULT_OK, Intent().putExtra("servicioActualizado", newServicio))
                    finish()
                }, { exception -> print("Error actualizando servicio")
                })
            }
        }else{
            crear_servicio_button.setOnClickListener {
                val nombre_servicio = nombre_servicio_input.text.toString()
                val fecha_instalacion = fecha_instalacion_input.text.toString()
                val precio_servicio = precio_servicio_input.text.toString().toInt()
                val servicio = Servicio(nombre_servicio, fecha_instalacion, precio_servicio)
                firestoreDB.crearServicio(idVivienda ,servicio,
                    {
                        setResult(Activity.RESULT_OK, Intent().putExtra("servicioActualizado", servicio))
                        finish()
                    }
                    , { exception -> print("Error creando servicio") }
                )
            }
        }
    }
}