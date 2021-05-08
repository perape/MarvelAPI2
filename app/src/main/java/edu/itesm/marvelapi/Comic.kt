package edu.itesm.marvelapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Data(
    var data:Datas?
)

data class Datas(
    var results:List<Comicslist>?
)


data class Comicslist(
    val title: String,
    val description: String,
    val thumbnail:Thumbnail
)

data class Thumbnail (
    val path: String,
    val extension: String
)

@Parcelize
data class Usuario(var nombre: String) : Parcelable


