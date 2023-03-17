package com.example.exameniib

import com.google.firebase.firestore.FirebaseFirestore

class FireStoreDB {

    private val db = FirebaseFirestore.getInstance()

    private val viviendasCollection = db.collection("viviendas")

    fun crearVivienda(
        vivienda: Vivienda,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viviendasCollection.add(vivienda)
            .addOnSuccessListener { documentReference ->
                val servicioRef = documentReference.collection("servicios")
                for (servicio in vivienda.servicios) {
                    servicioRef.add(servicio)
                        .addOnSuccessListener {
                            servicio.id_servicio = it.id
                        }
                        .addOnFailureListener { onFailure(it) }
                }
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun actualizarVivienda(
        idVivienda: String,
        vivienda: Vivienda,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viviendasCollection.document(idVivienda).set(vivienda)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun eliminarVivienda(
        idVivienda: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viviendasCollection.document(idVivienda).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun crearServicio(
        idVivienda: String,
        servicio: Servicio,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val estudiantesRef = viviendasCollection.document(idVivienda).collection("servicios")
        estudiantesRef.add(servicio)
            .addOnSuccessListener {
                servicio.id_servicio = it.id
                onSuccess()
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun actualizarServicio(
        idVivienda: String,
        servicio: Servicio,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val estudiantesRef = viviendasCollection.document(idVivienda).collection("servicios")
        estudiantesRef.document(servicio.id_servicio!!).set(servicio)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun eliminarServicio(
        idVivienda: String,
        idServicio: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val estudianteCollection =
            viviendasCollection.document(idVivienda).collection("servicios").document(idServicio)
        estudianteCollection.get()
            .addOnSuccessListener { serivicioDoc ->
                if (serivicioDoc.exists()) {
                    estudianteCollection.delete()
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onFailure(it) }
                }
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun getViviendas(onSuccess: (List<Vivienda>) -> Unit, onFailure: (Exception) -> Unit) {
        viviendasCollection.get()
            .addOnSuccessListener { result ->
                val viviendas = mutableListOf<Vivienda>()
                for (document in result) {
                    val vivienda = document.toObject(Vivienda::class.java)
                    vivienda.id_vivienda = document.id
                    viviendas.add(vivienda)
                    getServicios(vivienda.id_vivienda!!, onSuccess = {
                        vivienda.servicios = it
                    }, onFailure = {})
                }
                onSuccess(viviendas)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getServicios(
            idVivienda: String,
        onSuccess: (List<Servicio>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val serviciosCollection = viviendasCollection.document(idVivienda).collection("servicios")

        serviciosCollection.get()
            .addOnSuccessListener { result ->
                val servicios = mutableListOf<Servicio>()
                for (document in result) {
                    val servicio = document.toObject(Servicio::class.java)
                    servicio.id_servicio = document.id
                    servicios.add(servicio)
                }
                onSuccess(servicios)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}