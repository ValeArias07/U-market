package com.icesi.umarket.consumer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.icesi.umarket.databinding.ActivityConsumerLoginBinding
import com.icesi.umarket.model.User
import com.icesi.umarket.util.Constants


class ConsumerLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsumerLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsumerLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpHyperLink.setOnClickListener {
            startActivity(Intent(this, ConsumerSignupActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.logInUserNameTextField.text.toString()
            val password = binding.logInPasswdTextField.text.toString()

            if(email!="" && password !="") {
                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val currentUser = Firebase.auth.currentUser

                        Firebase.firestore.collection("users").document(currentUser!!.uid).get()
                            .addOnSuccessListener {
                                val user = it.toObject(User::class.java)
                                val intent = Intent(this, ConsumerHomeActivity::class.java).apply {
                                    putExtra(Constants.userObj, Gson().toJson(user))}
                                startActivity(intent)
                                finish()
                            }.addOnFailureListener {
                                Toast.makeText(this.baseContext, it.message, Toast.LENGTH_LONG).show()
                            }
                    }.addOnFailureListener {
                        Toast.makeText(this.baseContext, it.message, Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}

