package com.example.madprojectnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var descriptionEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var navlog: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.editTextTextPersonName)
        locationEditText = findViewById(R.id.editTextTextPersonLocation)
        categorySpinner = findViewById(R.id.spinnerCategory)
        descriptionEditText = findViewById(R.id.editTextTextPersonName4)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        submitButton = findViewById(R.id.button)
        navlog = findViewById(R.id.button6)

        db = DBHelper(this)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val location = locationEditText.text.toString()
            val category = categorySpinner.selectedItem.toString()
            val description = descriptionEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val savedata = db.insertdata(name, location, category, description, email, password)

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(location) || TextUtils.isEmpty(category) || TextUtils.isEmpty(description) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                if (savedata == true) {
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, login::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }

        navlog.setOnClickListener {
            val intent = Intent(applicationContext, login::class.java)
            startActivity(intent)
        }
    }
}
