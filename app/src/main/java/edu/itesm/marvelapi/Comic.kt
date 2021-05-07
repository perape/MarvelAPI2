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

