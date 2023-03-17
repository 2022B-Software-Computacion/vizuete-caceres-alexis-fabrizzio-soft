package com.example.exameniib

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude

data class Servicio(
    @Exclude @JvmField var id_servicio: String?,
    val nombre_servicio: String = "",
    val fecha_instalacion: String = "",
    val precio_servicio: Int = 0,
): Parcelable {

    constructor(
        nombre_servicio: String,
        fecha_instalacion: String,
        precio_servicio: Int
    ) : this(
        null,
        nombre_servicio,
        fecha_instalacion,
        precio_servicio
    )
    constructor() : this(null,
                        "",
                        "",
                            0)

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_servicio)
        parcel.writeString(nombre_servicio)
        parcel.writeString(fecha_instalacion)
        parcel.writeInt(precio_servicio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Servicio> {
        override fun createFromParcel(parcel: Parcel): Servicio {
            return Servicio(parcel)
        }

        override fun newArray(size: Int): Array<Servicio?> {
            return arrayOfNulls(size)
        }
    }

}
