package edu.itesm.marvelapi

data class Results(
    var results:Books?
)

data class Books(
    var books:List<Book>?
)

data class Book(val rank: Int,
                val title: String,
                val description: String,
                val book_image: String)

/*data class Result(
    val title:String,
    val description:String,
    val prices:List<Price>,
    val thumbnail:Thumbnail
)
data class Price (
    val type: String,
    val price: String
)
data class Thumbnail (
    val path: String,
    val extension: String
)*/



/*
results
    title : String
    description : String
    prices:[
        type : String
        price : float]
    //For extension use some type of concatenation with path + . extension
    thumbnail:[
        path : String
        extension : String]

 */

