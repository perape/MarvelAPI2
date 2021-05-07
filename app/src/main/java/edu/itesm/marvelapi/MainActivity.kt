package edu.itesm.marvelapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/current/"
    //private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var results: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = LinearLayoutManager(this)
        Toast.makeText(applicationContext, "OnCreate", Toast.LENGTH_LONG).show()
        getAllData()
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    fun getAllData(){

        val callToService = getRetrofit().create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val responseFromService = callToService.getBooks()
            runOnUiThread {
                results = responseFromService.body() as Results

                if (responseFromService.isSuccessful) {
                    Log.i("Books", results.results?.books.toString())

                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        layoutManager = manager
                        myAdapter = ComicsAdapter(results.results?.books)
                        adapter = myAdapter

                    }

                } else {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}