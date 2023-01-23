import java.time.LocalDate
import java.util.ArrayList

class Vivienda {
    var idVivienda: Int
    var nombreDueno:String
    var fechaDeCompra = LocalDate.parse("2000-01-06")
    var precioVivienda: Double
    var estaDesocupada: Boolean
    var listaServicios = arrayListOf<Servicio>()
    init {
        this.idVivienda = 0
        this.nombreDueno = ""
        this.fechaDeCompra
        this.precioVivienda = 0.0
        this.estaDesocupada = false
    }
    //Funcion para crear una vivienda con datos desde teclado
    fun crearVivienda() {
        println("Ingrese el id de la Vivienda: ")
        val id = readln().toInt()
        this.idVivienda = id
        println("Ingrese el nombre del Dueño: ")
        val nombre = readln()
        this.nombreDueno = nombre
        println("Ingresa la fecha de compra (aaaa-mm-dd): ")
        val fecha = LocalDate.parse(readln())
        this.fechaDeCompra = fecha
        println("Ingresa el precio de la Vivienda: ")
        val precio = readln().toDouble()
        this.precioVivienda = precio
        println("¿La Vivienda esta desocupada?: S o N")
        val aux = readln()
        if(aux.equals("S")){
            this.estaDesocupada = true
        }else {
            (aux.equals("N"))
            this.estaDesocupada = false
        }
    }
    //Funcion para buscar una vivienda
    fun buscarVivienda(idVivienda:Int, listaViviendas: ArrayList<Vivienda>):Vivienda{
        var viviendaEncontrada = Vivienda()
        listaViviendas.forEach{
                vivienda:Vivienda ->
            if(vivienda.idVivienda.equals(idVivienda)){
                viviendaEncontrada =  vivienda
            }
        }
        return viviendaEncontrada
    }
    //Funcion para actualizar la vivienda
    fun actualizarVivienda(idVivienda:Int, listaViviendas: ArrayList<Vivienda>):ArrayList<Vivienda>{

        //Buscar vivienda para actualizar
        var vivienda = Vivienda()
        var nuevaVivienda = vivienda.buscarVivienda(idVivienda, listaViviendas)
        var indiceVivienda = (vivienda.buscarVivienda(idVivienda, listaViviendas).idVivienda) - 1
        println(nuevaVivienda.toString())
        //Actualizar
        do {
            println("1. Nombre Dueñó")
            println("2. Fecha Compra")
            println("3. Precio Vivienda")
            println("4. Esta Desocupada?")
            println("5. Finalizar")
            val respuesta = readln().toInt()
            //Cambios
            when(respuesta) {
                1 -> {
                    println("Ingrese el nombre del Dueño: ")
                    val nombre = readln()
                    nuevaVivienda.nombreDueno = nombre
                    listaViviendas[indiceVivienda] = nuevaVivienda
                }
                2 -> {
                    println("Ingresa la fecha de compra (aaaa-mm-dd): ")
                    val fecha = LocalDate.parse(readln())
                    nuevaVivienda.fechaDeCompra = fecha
                    listaViviendas[indiceVivienda] = nuevaVivienda
                }
                3 -> {
                    println("Ingresa el precio de la Vivienda: ")
                    val precio = readln().toDouble()
                    nuevaVivienda.precioVivienda = precio
                    listaViviendas[indiceVivienda] = nuevaVivienda
                }
                4 -> {
                    println("¿La Vivienda esta desocupada?: S o N")
                    val aux = readln()
                    if(aux.equals("S")){
                        nuevaVivienda.estaDesocupada = true
                    }else {
                        (aux.equals("N"))
                        nuevaVivienda.estaDesocupada = false
                    }
                    listaViviendas[indiceVivienda] = nuevaVivienda
                }
            }
        }while (respuesta!=5);
        return listaViviendas
    }
    //Eliminar Vivienda
    fun eliminarVivienda(idVivienda:Int, listaViviendas: ArrayList<Vivienda>):ArrayList<Vivienda>{
        var vivienda = Vivienda()
        var nuevaVivienda = vivienda.buscarVivienda(idVivienda, listaViviendas)
        var indiceVivienda = (vivienda.buscarVivienda(idVivienda, listaViviendas).idVivienda) - 1
        listaViviendas.removeAt(indiceVivienda)
        return listaViviendas
    }

    //Funcion toString
    override fun toString(): String {
        return "VIVIENDA " +
                "\nId Vivienda: $idVivienda " +
                "\nNombre Dueno:'$nombreDueno' " +
                "\nFecha De Compra: $fechaDeCompra" +
                "\nPrecio: $precioVivienda " +
                "\nEstado:$estaDesocupada " +
                "\nSERVICIOS: $listaServicios"
    }
}


