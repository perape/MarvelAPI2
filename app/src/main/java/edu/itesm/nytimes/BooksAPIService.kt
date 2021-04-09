package edu.itesm.nytimes

import retrofit2.Response
import retrofit2.http.GET


interface APIService {
    @GET("hardcover-fiction.json?api-key=xIyegPeHMZ6PSBQ75w5LwoY1AVpulofB")
    suspend fun getBooks() : Response<Results>
}