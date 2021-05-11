package edu.itesm.marvelapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import edu.itesm.marvelapi.databinding.ActivityMainBinding
import edu.itesm.marvelapi.databinding.ActivityShoppingListBinding
import edu.itesm.marvelapi.databinding.FragmentRegisterBinding


class Shopping_list : AppCompatActivity() {
    private lateinit var bind: ActivityShoppingListBinding
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind =ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(bind.root)
        cargarDatos()

    }
    private fun cargarDatos() {
        var reference: DatabaseReference
        var database: FirebaseDatabase
        database = FirebaseDatabase.getInstance()
        //val usuario = Firebase.auth.currentUser
        reference = database.getReference("Base")
        bind.shoppingList.apply{
            reference.addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var comiclist = ArrayList<Comicslist2>()
                    for (compra in snapshot.children){
                        var objeto = compra.getValue(Comicslist2::class.java)
                        comiclist.add(objeto as Comicslist2)

                    }
                        adapter = ShoppingAdapter(comiclist)

                        layoutManager = LinearLayoutManager(this@Shopping_list)

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Error al cargar los datos", Toast.LENGTH_LONG).show()
                }
            })

        }
    }

}