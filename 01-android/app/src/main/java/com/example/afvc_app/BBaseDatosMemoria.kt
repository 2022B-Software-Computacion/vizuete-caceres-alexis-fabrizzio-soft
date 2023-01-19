package com.example.afvc_app

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Alexis", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Fabrizzio", "f@f.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Nicole", "n@n.com")
                )
        }
    }
}