package edu.itesm.marvelapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var nuevoUsuario: Usuario
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = Firebase.auth
        database = Firebase.database.reference
        nuevoUsuario = Usuario("")
        createButton.setOnClickListener {crearUsuario() }
    }

    private fun usuarioCreado(){
        nuevoUsuario.nombre =  nameText.text.toString()
        database.child("users").setValue(nuevoUsuario.nombre)
    }

    fun crearUsuario(){
        if (passwordText.text.toString() == password2Text.text.toString()){

            if (emailText.text.isNotEmpty() && passwordText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailText.text.toString(),
                    passwordText.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        usuarioCreado()
                        nameText.text.clear()
                        emailText.text.clear()
                        passwordText.text.clear()
                        password2Text.text.clear()
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener{
                    Toast.makeText(this.context,it.toString(), Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this.context,"No dejes campos vacios", Toast.LENGTH_LONG).show()}
        }else{
            Toast.makeText(this.context,"Contraseñas no coinciden", Toast.LENGTH_LONG).show() }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
}