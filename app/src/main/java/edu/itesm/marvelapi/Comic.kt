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
    var title: String,
    var description: String,
    var thumbnail:Thumbnail,
)

data class Thumbnail (
    var path: String,
    var extension: String
)

@Parcelize
data class Usuario(var nombre: String) : Parcelable

@Parcelize
data class Comicslist2(
    val title: String?=null,
    val description: String?=null,
    val link:String?=null,
    val llave:String?=null
) : Parcelable