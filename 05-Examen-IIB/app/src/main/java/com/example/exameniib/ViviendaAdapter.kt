package com.example.exameniib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ViviendaAdapter : AppCompatActivity() {

    val firestoreDB = FireStoreDB()
    lateinit var idVivienda: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vivienda)

        val nombre_vivienda_input = findViewById<EditText>(R.id.id_nombre_vivienda_input)
        val precio_vivienda_input = findViewById<EditText>(R.id.id_precio_vivienda_input)
        val fecha_compra_input = findViewById<EditText>(R.id.id_fecha_compra_input)

        val crear_vivienda_button = findViewById<Button>(R.id.btn_crear_vivienda)

        val vivienda = intent.getParcelableExtra<Vivienda>("vivienda")

        if (vivienda != null) {
            idVivienda = vivienda.id_vivienda!!
            nombre_vivienda_input.setText(vivienda.nombre_vivienda)
            precio_vivienda_input.setText(vivienda.precio_vivienda.toString())
            fecha_compra_input.setText(vivienda.fecha_compra)
            crear_vivienda_button.setOnClickListener {
                val nombre_vivienda = nombre_vivienda_input.text.toString()
                val precio_vivienda = precio_vivienda_input.text.toString().toInt()
                val fecha_compra = fecha_compra_input.text.toString()
                val newVivienda = Vivienda(vivienda.id_vivienda, nombre_vivienda, precio_vivienda, fecha_compra)
                firestoreDB.actualizarVivienda(idVivienda, newVivienda, {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().putExtra("viviendaActualizada", newVivienda)
                    )
                    finish()
                }, { exception -> print("Error actualizando vivienda") }
                )
            }
        } else {
            crear_vivienda_button.setOnClickListener {
                val nombre_vivienda = nombre_vivienda_input.text.toString()
                val precio_vivienda = precio_vivienda_input.text.toString().toInt()
                val fecha_compra = fecha_compra_input.text.toString()
                val vivienda = Vivienda(nombre_vivienda, precio_vivienda, fecha_compra, emptyList())

                firestoreDB.crearVivienda(vivienda,
                    {
                        vivienda.id_vivienda = it
                        setResult(
                            Activity.RESULT_OK,
                            Intent().putExtra("viviendaActualizada", vivienda)
                        )
                        finish()
                    }, { exception -> print("Error creando la vivienda") }
                )
            }
        }
    }
}