package edu.itesm.marvelapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ShoppingAdapter(private val data: MutableList<Comicslist2>?) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: Comicslist2) {
            val title = view.findViewById<TextView>(R.id.tvTitle_2)
            val imageView = view.findViewById<ImageView>(R.id.imageView_2)
            val description = view.findViewById<TextView>(R.id.tvDescription_2)
            val button = view.findViewById<Button>(R.id.button_2)

            title.text = property.title
            Log.i("Books",  property.title.toString())
            description.text = property.description.toString().substring(0, 100)

            Glide.with(view.context)
                .load(property.link)
                //.circleCrop()
                .into(imageView)


            button.setOnClickListener {   borrardatos(property.llave.toString())   }

        }
        fun borrardatos(llave:String){
            val usuario = Firebase.auth.currentUser
            val database = Firebase.database
            val myRef = database.getReference("Base")
            Log.i("Books",  usuario.toString())
            if (llave != null) {
                myRef.child(llave).removeValue()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_shopping_renglon, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])


    }


}