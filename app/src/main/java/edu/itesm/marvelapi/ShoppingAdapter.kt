package edu.itesm.marvelapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class ShoppingAdapter(private val data: MutableList<Comicslist>?) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: Comicslist) {
            val title = view.findViewById<TextView>(R.id.tvTitle_2)
            val imageView = view.findViewById<ImageView>(R.id.imageView_2)
            val description = view.findViewById<TextView>(R.id.tvDescription_2)
            val button = view.findViewById<Button>(R.id.button_2)
            val database = Firebase.database
            val myRef = database.getReference("Base")


            title.text = property.title
            //validar
            if (property.description == null) {
                description.text ="Not description"
            } else {
                description.text = property.description.substring(0, 100)
            }

            Glide.with(view.context)
                .load(property.thumbnail.path.plus(".jpg"))
                .circleCrop()
                .into(imageView)


            button.setOnClickListener {   borrardatos()   }

        }
        fun borrardatos(){
            val database = Firebase.database
            val myRef = database.getReference("Base")
            val idComic = myRef.push().key
            if (idComic != null) {
                myRef.child("Base").child(idComic).removeValue()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.list_ny_book, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])


    }


}