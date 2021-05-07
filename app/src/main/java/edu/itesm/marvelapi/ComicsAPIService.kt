package edu.itesm.marvelapi

import retrofit2.Response
import retrofit2.http.GET
import java.math.BigInteger
import java.security.MessageDigest

interface APIService {
    @GET("hardcover-fiction.json?api-key=xIyegPeHMZ6PSBQ75w5LwoY1AVpulofB")
    //Temporal API Key
    //@GET("comics?ts=thesoer&apikey=001ac6c73378bbfff488a36141458af2&hash=72e5ed53d1398abb831c3ceec263f18b")

    //My API KEY
    //@GET("comics?ts=thesoer&apikey=1983a0e8667149082629e40b19ba830c&hash=58ab8d0500dddde55e180df729005b40")
    suspend fun getBooks() : Response<Results>
}
