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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

class ComicsAdapter(private val data: List<Comicslist>?) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: Comicslist) {
            val title = view.findViewById<TextView>(R.id.tvTitle)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val description = view.findViewById<TextView>(R.id.tvDescription)
            val button = view.findViewById<Button>(R.id.button)
            val database = Firebase.database
            val myRef = database.getReference("Base")



            title.text = property.title
            //validar
            if (property.description == null) {
                description.text ="Not description"
            } else {
                description.text = property.description.substring(0, 100)
            }
            val link: String=property.thumbnail.path.plus(".jpg")

                Glide.with(view.context)
                .load(link)
                .circleCrop()
                .into(imageView)


            button.setOnClickListener {
                if (property.description == null) {
                    val description2 ="Not description"
                    val compra = Comicslist2(
                        property.title,
                        description2,
                        link.toString()
                    )
                    val id =myRef.push().key
                    myRef.child(id!!).setValue(compra)
                    Toast.makeText(itemView.getContext(), "se ha agregado al carrito", Toast.LENGTH_LONG).show()
                    //agregarcomic(property.title,description2,property.thumbnail,myRef)
                } else {
                    //agregarcomic(property.title,property.description,property.thumbnail,myRef)

                    val id =myRef.push().key
                    val compra = Comicslist2(
                        property.title,
                        property.description,
                        link.toString(),
                        id.toString()
                    )
                    myRef.child(id!!).setValue(compra)
                    Toast.makeText(itemView.getContext(), "se ha agregado al carrito", Toast.LENGTH_LONG)
                }
            }

        }
       public fun agregarcomic(title:String,description:String,image:Thumbnail,myRef:DatabaseReference) {
           // Toast.makeText(itemView.getContext(), "$title", Toast.LENGTH_LONG).show();

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

