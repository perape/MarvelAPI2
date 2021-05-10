package edu.itesm.marvelapi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import java.io.ByteArrayOutputStream
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    //
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = Firebase.auth
        loginButton.setOnClickListener {iniciarSesion()}
        registerButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun iniciarSesion(){
        if(textEmail.text.isNotEmpty() && textPassword.text.isNotEmpty() ){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                textEmail.text.toString(),
                textPassword.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this.context,"Bienvenido", Toast.LENGTH_LONG).show()
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this.context,"Usuario o contraseña son incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this.context,"No dejes campos en blanco", Toast.LENGTH_LONG).show()
        }

    }


}