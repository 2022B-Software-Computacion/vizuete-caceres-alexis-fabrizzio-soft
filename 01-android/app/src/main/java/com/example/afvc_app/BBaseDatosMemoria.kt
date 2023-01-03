package com.example.afvc_app

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Alexis", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Fabrizzio", "f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Nicole", "n@n.com")
                )
        }
    }
}