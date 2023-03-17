package com.example.exameniib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts


class ViviendaServicios : AppCompatActivity() {
    lateinit var listViewServicios: ListView
    lateinit var nombreViviendaTextView: TextView

    val firestoreDB = FireStoreDB()

    var idItemSeleccionado = 0
    var servicios: List<Servicio>? = null
    lateinit var nombre_servicios: ArrayList<String>
    lateinit var nombre_vivienda: String
    // Revisar nombre
    lateinit var id_vivienda: String
    private val actualizar_servicio =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { servicioActualizado ->

            if (servicioActualizado.resultCode == Activity.RESULT_OK) {
                val updatedServicio =
                    servicioActualizado.data?.getParcelableExtra<Servicio>("servicioActualizado")

                if (updatedServicio != null) {
                    val index: Int =
                        servicios!!.indexOfFirst { it.id_servicio == updatedServicio.id_servicio }

                    if (index >= 0) {
                        val updatedServicios = servicios!!.toMutableList()
                        updatedServicios[index] = updatedServicio
                        servicios = updatedServicios.toList()
                        nombre_servicios[index] =
                            updatedServicio.nombre_servicio + " " + updatedServicio.fecha_instalacion
                        (listViewServicios.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    } else {
                        val updatedServicios = servicios!!.toMutableList()
                        updatedServicios.add(updatedServicio)
                        servicios = updatedServicios.toList()
                        nombre_servicios.add(updatedServicio.nombre_servicio + " " + updatedServicio.fecha_instalacion)
                        (listViewServicios.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vivienda_servicios)
        val btn_crear_servicio = findViewById<Button>(R.id.id_btn_crear_servicio)
        btn_crear_servicio.setOnClickListener {
            editarCreateServicio(null)
        }
        nombreViviendaTextView = findViewById<TextView>(R.id.id_textview_nombre_vivienda)
        listViewServicios = findViewById<ListView>(R.id.id_listview_servicios)

        id_vivienda = intent.getStringExtra("idVivienda")!!
        nombre_vivienda = intent.getStringExtra("nombreVivienda")!!
        servicios = intent.getParcelableArrayListExtra<Servicio>("servicios")

        nombre_servicios = servicios?.map { it.nombre_servicio + " " + it.fecha_instalacion } as ArrayList<String>

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                nombre_servicios as List<String>
            )

        listViewServicios.adapter = adapter

        nombreViviendaTextView.text = nombre_vivienda
        adapter.notifyDataSetChanged()

        registerForContextMenu(listViewServicios)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_servicios, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.servicio_editar -> {
                editarCreateServicio(servicios!![idItemSeleccionado])
                return true
            }
            R.id.servicio_eliminar -> {
                eliminar_servicio()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun editarCreateServicio(
        servicio: Servicio?
    ) {
        val intent = Intent(this, ServicioAdapter::class.java)
        intent.putExtra("idVivienda", id_vivienda)
        intent.putExtra("servicio", servicio)

        actualizar_servicio.launch(intent)
    }

    fun eliminar_servicio() {

        val servicio_index = idItemSeleccionado
        val id_servicio = servicios!![idItemSeleccionado].id_servicio!!
        firestoreDB.eliminarServicio(
            id_vivienda, id_servicio,
            onSuccess = {
                Toast.makeText(this, "Servicio eliminado con exito", Toast.LENGTH_SHORT)
                    .show()
                servicios = servicios?.filterIndexed { index, _ -> index != servicio_index }
                nombre_servicios.removeAt(servicio_index)
                (listViewServicios.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = { error ->
                Toast.makeText(
                    this,
                    "Error al eliminar el servicio: $error",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("Error al eliminar", error.toString())
            }
        )
    }


}
