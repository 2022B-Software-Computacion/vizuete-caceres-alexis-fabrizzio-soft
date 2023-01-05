package com.example.afvc_app

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

/*class BListView : AppCompatActivity() {
    //Variables
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var IdItemSeleccionado = 0

    //Funcion para crear list view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        //Click del botón y añadir entrenador
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view)
        //Boton anadir dentro del list view
        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)
            }
        //Registrar en el context menu
        registerForContextMenu(listView)
        }

    //Funcion para crear el context menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del array seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        IdItemSeleccionado = id
    }

    //Funcion para el item seleccionado
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                "${IdItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                // abrirDialogo()
                "${IdItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    //Funcion para anadir Entrenador
    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(
                "Adrian",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()
    }
}*/

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view)

        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)
            }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                //Al aceptar eliminar el registro
            }

        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, //Lunes seleccionado
            false, //Martes NO seleccionado
            false //Miercoles NO seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                    dialog,
                    which,
                    isChecked ->
                "Dio clic en el item ${which}"
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(
                "Gandhy",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()
    }


}