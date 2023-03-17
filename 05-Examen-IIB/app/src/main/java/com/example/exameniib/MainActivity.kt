package com.example.exameniib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val firestoreDB = FireStoreDB()
    var idItemSeleccionado = 0
    lateinit var viviendas: List<Vivienda>
    lateinit var nombre_viviendas: ArrayList<String>
    lateinit var viviendaListView: ListView

    private val actualizar_vivienda =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val vivienda_actualizada =
                    result.data?.getParcelableExtra<Vivienda>("viviendaActualizada")

                if (vivienda_actualizada != null) {
                    val index: Int =
                        viviendas.indexOfFirst { it.id_vivienda == vivienda_actualizada.id_vivienda }

                    if (index >= 0) {
                        val servicios_actualizados = viviendas.toMutableList()
                        servicios_actualizados[index] = vivienda_actualizada
                        viviendas = servicios_actualizados.toList()

                        nombre_viviendas[index] = vivienda_actualizada.nombre_vivienda
                        Log.e("TAG", nombre_viviendas.toString())

                        (viviendaListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    } else {
                        val servicios_actualizados = viviendas.toMutableList()
                        servicios_actualizados.add(vivienda_actualizada)
                        viviendas = servicios_actualizados.toList()
                        nombre_viviendas.add(vivienda_actualizada.nombre_vivienda)
                        (viviendaListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    }
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.viviendaListView = findViewById<ListView>(R.id.id_listview_vivienda)
        getViviendas()
        registerForContextMenu(viviendaListView)
        val btn_crear_viviendas: Button = findViewById<Button>(R.id.btn_crear_vivienda)
        btn_crear_viviendas.setOnClickListener {
            actualizar_vivienda(null)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_vivienda, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.vivienda_editar -> {
                actualizar_vivienda(viviendas[idItemSeleccionado])
                return true
            }
            R.id.vivienda_eliminar -> {
                eliminar_vivienda()
                return true
            }
            R.id.vivienda_servicios -> {
                getServicios()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun actualizar_vivienda(
        vivienda: Vivienda?
    ) {
        val intent = Intent(this, ViviendaAdapter::class.java)
        intent.putExtra("vivienda", vivienda)
        actualizar_vivienda.launch(intent)
    }

    fun eliminar_vivienda() {
        val vivienda_index = idItemSeleccionado
        val id_vivienda = viviendas[vivienda_index].id_vivienda

        firestoreDB.eliminarVivienda(id_vivienda!!,
            onSuccess = {
                Toast.makeText(this, "Vivienda eliminada con exito", Toast.LENGTH_SHORT)
                    .show()
                viviendas = viviendas.filterIndexed { index, _ -> index != vivienda_index }
                nombre_viviendas.removeAt(vivienda_index)
                (viviendaListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            },
            onFailure = { error -> print("Error eliminando vivienda") }
        )

    }

    fun getServicios() {
        val servicio = viviendas[idItemSeleccionado]

        val intent = Intent(this, ViviendaServicios::class.java)
        intent.putExtra("idVivienda", servicio.id_vivienda)
        intent.putExtra("nombreVivienda", servicio.nombre_vivienda)
        intent.putParcelableArrayListExtra(
            "servicios",
            ArrayList(servicio.servicios)
        )

        startActivity(intent)
    }

    fun getViviendas() {
        firestoreDB.getViviendas(
            onSuccess = { viviendas ->
                this.viviendas = viviendas
                nombre_viviendas = viviendas.map { it.nombre_vivienda } as ArrayList<String>
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombre_viviendas)
                viviendaListView.adapter = adapter
                adapter.notifyDataSetChanged()
            },
            onFailure = { error ->
                print("Error retornando viviendas")
            }
        )
    }
}