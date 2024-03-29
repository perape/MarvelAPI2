package edu.itesm.marvelapi


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_ny_book.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var data: Data

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
        imageButton2.setOnClickListener{
            val intent = Intent(this, Shopping_list::class.java)
            startActivity(intent)
        }
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
                data = responseFromService.body() as Data

                if (responseFromService.isSuccessful) {
                    Log.i("Books",  data.data?.results.toString())
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        layoutManager = manager
                        myAdapter = ComicsAdapter(data.data?.results)
                        adapter = myAdapter


                    }

                } else {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}