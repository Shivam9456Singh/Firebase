package com.martinestudio.foodorder

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUpButton = findViewById<Button>(R.id.btnSignup)
        val enterName = findViewById<TextInputEditText>(R.id.enterName)
        val enterEmail = findViewById<TextInputEditText>(R.id.enterEmail)
        val enterPassword = findViewById<TextInputEditText>(R.id.enterPassword)

        signUpButton.setOnClickListener {
            //get data from the user
            val name  = enterName.text.toString()
            val email = enterEmail.text.toString()
            val password = enterPassword.text.toString()

            val user = User(name,email,password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(email).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User Registered Successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed to Register",Toast.LENGTH_SHORT).show()
            }

        }

    }
}