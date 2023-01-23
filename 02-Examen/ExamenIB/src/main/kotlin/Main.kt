fun main(args: Array<String>) {
    var archivo = manejadorArchivo()

    do {
        println("SISTEMA DE GESTION DE VIVIENDA Y SERVICIOS")
        println("MODULO VIVIENDA")
        println("1. Crear Vivienda")
        println("2. Buscar Vivienda")
        println("3. Actualizar Vivienda")
        println("4. Eliminar Vivienda")
        println("5. Ingresar al modulo de Servicios")
        println("6. Salir")

        val respuestaSistema = readln().toInt()
        var listaViviendas = archivo.leer()

        when (respuestaSistema) {
            1 -> {//Crear vivienda
                val auxVivienda = Vivienda()
                auxVivienda.crearVivienda()
                listaViviendas.add(auxVivienda)
                archivo.escribir(listaViviendas)
                println("¡Vivienda creada con EXITO!\n")
            }
            2->{//Buscar vivienda
                println("ID de la vivienda: ")
                val idVivienda = readln().toInt()
                val auxVivienda = Vivienda().buscarVivienda(idVivienda, listaViviendas)
                println(auxVivienda.toString())
                println("¡Vivienda encontrada con EXITO!\n")
            }
            3->{//Actualizar vivienda
                println("ID de la vivienda: ")
                val idVivienda = readln().toInt()
                val auxListViviendas = Vivienda().actualizarVivienda(idVivienda, listaViviendas)
                archivo.escribir(listaViviendas)
                println("¡Vivienda actualizada con EXITO!\n")
            }

            4->{//Eliminar vivienda
                println("ID de la vivienda: ")
                val idVivienda = readln().toInt()
                val auxListViviendas = Vivienda().eliminarVivienda(idVivienda, listaViviendas)
                archivo.escribir(listaViviendas)
                println("¡Vivienda eliminada con EXITO!\n")
            }
            5->{//Modulo SERVICIOS
                println("ID de la vivienda: ")
                val idVivienda = readln().toInt()
                val auxVivienda = Vivienda().buscarVivienda(idVivienda, listaViviendas)
                val indiceVivienda = (auxVivienda.idVivienda)-1

                do{
                    println("\nMODULO SERVICIO")
                    println("1. Crear Servicio")
                    println("2. Buscar Servicio")
                    println("3. Actualizar Servicio")
                    println("4. Eliminar Servicio")
                    println("5. Salir")
                    val respuestaServicios = readln().toInt()

                    listaViviendas = archivo.leer()

                    when(respuestaServicios){
                        1->{ //Crear Servicio
                            val auxServicio = Servicio()
                            auxServicio.crearServicio()
                            listaViviendas[indiceVivienda].listaServicios.add(auxServicio)
                            archivo.escribir(listaViviendas)
                            println("¡Servicio creado con EXITO!\n")
                        }
                        2->{ //Buscar Servicio
                            println("ID del Servicio: ")
                            val idServicio = readln().toInt()
                            val auxServicio = Servicio().buscarServicio(idServicio,
                                listaViviendas[indiceVivienda].listaServicios)
                            println(auxServicio.toString())
                            println("¡Servicio encontrado con EXITO!\n")
                        }
                        3->{//Actualizar Servicio
                            println("ID del Servicio: ")
                            val idServicio = readln().toInt()
                            val auxServicio = Servicio().actualizarServicio(idServicio,
                                listaViviendas[indiceVivienda].listaServicios)
                            archivo.escribir(listaViviendas)
                            println("¡Servicio actualizado con EXITO!\n")
                        }
                        4->{//Eliminar Servicio
                            println("ID del Servicio: ")
                            val idServicio = readln().toInt()
                            val auxServicio = Servicio().eliminarServicio(idServicio,
                                listaViviendas[indiceVivienda].listaServicios)
                            archivo.escribir(listaViviendas)
                            println("¡Servicio eliminado con EXITO!\n")
                        }
                    }
                }while (respuestaServicios!=5)
            }
        }
    } while (respuestaSistema != 6)
}