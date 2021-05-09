package edu.itesm.marvelapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

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
    }

    fun iniciarSesion(){
        if(textEmail.text.isNotEmpty() && textPassword.text.isNotEmpty() ){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                textEmail.text.toString(),
                textPassword.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this.context,"Bienvenido", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this.context,"Usuario o contraseña son incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this.context,"No dejes campos en blanco", Toast.LENGTH_LONG).show()
        }

    }

}