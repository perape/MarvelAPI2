package edu.itesm.marvelapi

import retrofit2.Response
import retrofit2.http.GET
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp

interface APIService {

    //My API KEY
    @GET("comics?ts=12&apikey=f1d267375f9bca5cbe9cc8f69e1a8033&hash=d1a13cf26af305130841983eacc83a3f")
    suspend fun getBooks() : Response<Data>
}
