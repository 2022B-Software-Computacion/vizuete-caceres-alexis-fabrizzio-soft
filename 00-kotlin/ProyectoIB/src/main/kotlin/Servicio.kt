import java.time.LocalDate
import java.util.ArrayList

class Servicio {
    var idServicio: Int
    var nombreServicio:String
    var fechaDeRegistro = LocalDate.parse("2000-01-06")
    var precioServicio: Double
    var estaActivo: Boolean

    init {
        this.idServicio = 0
        this.nombreServicio = ""
        this.fechaDeRegistro
        this.precioServicio = 0.0
        this.estaActivo = false
    }
    //Funcion para crear una vivienda con datos desde teclado
    fun crearServicio() {
        println("Ingrese el id del Servicio: ")
        val id = readln().toInt()
        this.idServicio = id
        println("Ingrese el nombre del Servicio: ")
        val nombre = readln()
        this.nombreServicio = nombre
        println("Ingresa la fecha de registro (aaaa-mm-dd): ")
        val fecha = LocalDate.parse(readln())
        this.fechaDeRegistro = fecha
        println("Ingresa el precio del Servicio: ")
        val precio = readln().toDouble()
        this.precioServicio = precio
        println("¿El servicio esta activo?: S o N")
        val aux = readln()
        if(aux.equals("S")){
            this.estaActivo = true
        }else {
            (aux.equals("N"))
            this.estaActivo = false
        }
    }
    //Funcion para buscar una vivienda
    fun buscarServicio(idServicio:Int, listaServicios: ArrayList<Servicio>):Servicio{
        var servicioEncontrado = Servicio()
        listaServicios.forEach{
                servicio:Servicio ->
            if(servicio.idServicio.equals(idServicio)){
                servicioEncontrado =  servicio
            }
        }
        return servicioEncontrado
    }
    //Funcion para actualizar la vivienda
    fun actualizarServicio(idServicio:Int, listaServicios: ArrayList<Servicio>): ArrayList<Servicio> {
        //Buscar vivienda para actualizar
        var servicio = Servicio()
        var nuevoServicio = servicio.buscarServicio(idServicio, listaServicios)
        var indiceServicio = (servicio.buscarServicio(idServicio, listaServicios).idServicio) - 1
        println(nuevoServicio.toString())
        //Actualizar
        do {
            println("1. Nombre Servicio")
            println("2. Fecha de registro")
            println("3. Precio del Servicio")
            println("4. Está activo?")
            println("5. Finalizar")
            val respuesta = readln().toInt()
            //Cambios
            when(respuesta) {
                1 -> {
                    println("Ingrese el nombre del Servicio: ")
                    val nombre = readln()
                    nuevoServicio.nombreServicio = nombre
                    listaServicios[indiceServicio] = nuevoServicio
                }
                2 -> {
                    println("Ingresa la fecha de registro (aaaa-mm-dd): ")
                    val fecha = LocalDate.parse(readln())
                    nuevoServicio.fechaDeRegistro = fecha
                    listaServicios[indiceServicio] = nuevoServicio
                }
                3 -> {
                    println("Ingresa el precio del Servicio: ")
                    val precio = readln().toDouble()
                    nuevoServicio.precioServicio = precio
                    listaServicios[indiceServicio] = nuevoServicio
                }
                4 -> {
                    println("¿El servicio esta activo?: S o N")
                    val aux = readln()
                    if (aux.equals("S")) {
                        nuevoServicio.estaActivo = true
                    } else {
                        (aux.equals("N"))
                        nuevoServicio.estaActivo = false
                    }
                    listaServicios[indiceServicio] = nuevoServicio
                }
            }
        }while (respuesta!=5);
        return listaServicios
    }
    //Eliminar Vivienda
    fun eliminarServicio(idServicio:Int, listaServicios: ArrayList<Servicio>): ArrayList<Servicio> {
        //Buscar vivienda para eliminar
        var servicio = Servicio()
        //var nuevoServicio = servicio.buscarServicio(idServicio, listaServicios)
        var indiceServicio = (servicio.buscarServicio(idServicio, listaServicios).idServicio) - 1

        listaServicios.removeAt(indiceServicio)

        return listaServicios
    }

    //Funcion toString
    override fun toString(): String {
        return "Servicio(idServicio=$idServicio, nombreServicio='$nombreServicio', fechaDeRegistro=$fechaDeRegistro, precioServicio=$precioServicio, estaActivo=$estaActivo)"
    }
}