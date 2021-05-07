package edu.itesm.marvelapi

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("hardcover-fiction.json?api-key=xIyegPeHMZ6PSBQ75w5LwoY1AVpulofB")
    //@GET("comics?orderBy=title&apikey=1983a0e8667149082629e40b19ba830c")
    suspend fun getBooks() : Response<Results>
}