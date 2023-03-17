package com.example.exameniib

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class Vivienda(
    @Exclude @JvmField var id_vivienda: String?,
    val nombre_vivienda: String,
    val precio_vivienda: Int,
    val fecha_compra: String,
    @Exclude @JvmField var servicios: List<Servicio> = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createTypedArrayList(Servicio)!!
    )

    constructor(
        nombre_vivienda: String,
        precio_vivienda: Int,
        fecha_compra: String,
        servicios: List<Servicio>
    ) : this(
        null,
        nombre_vivienda,
        precio_vivienda,
        fecha_compra,
        servicios
    )

    constructor() : this(null,
                        "",
                        0,
                        "",
                        emptyList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_vivienda)
        parcel.writeString(nombre_vivienda)
        parcel.writeInt(precio_vivienda)
        parcel.writeString(fecha_compra)
        parcel.writeTypedList(servicios)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vivienda> {
        override fun createFromParcel(parcel: Parcel): Vivienda {
            return Vivienda(parcel)
        }

        override fun newArray(size: Int): Array<Vivienda?> {
            return arrayOfNulls(size)
        }
    }
}