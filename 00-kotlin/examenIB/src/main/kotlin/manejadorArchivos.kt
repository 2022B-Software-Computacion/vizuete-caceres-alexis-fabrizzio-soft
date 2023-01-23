import java.io.File
import java.io.InputStream
import java.time.LocalDate

class manejadorArchivos {

    //FUNCION PARA LEER EL ARCHIVO TXT
    //SE LEE EL ARCHIVO DONDE SE USAN TOKENS Y UNA EXPRESION REGEX PARA SABER QUE SE VA A TOMAR
    //FINALMENTE SE GUARDAN EN ARREGLOS PARA USARLOS DE MANERA MAS FACIL
    fun leer():ArrayList<Vivienda>{
        val inputStream: InputStream = File("recursos/archivo.txt").inputStream()
        val lineas = mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                lineas.add(it)
            }
        }

        var listaViviendas = ArrayList<Vivienda>()
        var indiceVivienda = 0

        lineas.forEach{ linea: String->
            val tokens = listOf(*linea.split("\\s*:\\s*".toRegex()).toTypedArray())

            if(tokens[0].equals("Vivienda")){
                val auxVivienda = Vivienda()
                auxVivienda.idVivienda = tokens[2].toInt()
                auxVivienda.nombreDueno = tokens[4]
                auxVivienda.fechaDeCompra = LocalDate.parse(tokens[6])
                auxVivienda.precioVivienda = tokens[8].toDouble()
                auxVivienda.estaDesocupada = tokens[10].toBoolean()
                listaViviendas.add(auxVivienda)
                indiceVivienda = listaViviendas.indexOf(auxVivienda)
            }else{
                if(tokens[0].equals("Servicios") ){
                    val auxServicio = Servicio()
                    auxServicio.idServicio = tokens[2].toInt()
                    auxServicio.nombreServicio = tokens[4]
                    auxServicio.fechaDeRegistro = LocalDate.parse(tokens[6])
                    auxServicio.precioServicio = tokens[8].toDouble()
                    auxServicio.estaActivo = tokens[10].toBoolean()
                    listaViviendas[indiceVivienda].listaServicios.add(auxServicio)
                }
            }
        }
        return listaViviendas
    }

    //FUNCION PARA ESCRIBIR EL ARCHIVO TXT
    //SE IDENTIFICA EL ARCHIVO POR EL PATH
    //SE HACE UN APPENDE DE LAA INFORMACION GUARDADO EN LOS ARREGLOS
    fun escribir(texto:ArrayList<Vivienda>){

        val archivo = File("recursos/archivo.txt")
        archivo.writeText("")
        texto.forEach { vivienda: Vivienda ->
            archivo.appendText("Vivienda: ")
            archivo.appendText("Id Vivienda:" + vivienda.idVivienda
                                + " :Nombre dueño:" + vivienda.nombreDueno
                                +" :Fecha de compra:"+vivienda.fechaDeCompra
                                +" :Precio de la Vivienda:"+vivienda.precioVivienda
                                +" :Esta Desocupada?:"+vivienda.estaDesocupada
                                +"\n")

            vivienda.listaServicios.forEach { servicio: Servicio ->
                archivo.appendText("Servicios: ")
                archivo.appendText("Id Servicio:" + servicio.idServicio
                                +" :Nombre Servicio:"+servicio.nombreServicio
                                +" :Fecha de registro:"+servicio.fechaDeRegistro
                                +" :Precio Servicio:"+servicio.precioServicio
                                +" :Activo?:"+servicio.estaActivo
                                +"\n")
            }
        }
    }
}