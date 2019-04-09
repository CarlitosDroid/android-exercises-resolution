package com.example.android_exercises_resolution

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_exercises_resolution.utils.isValid
import com.example.android_exercises_resolution.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bValidateEmail.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        if (etEmail.isValid({ email -> email.isValidEmail() }, getString(R.string.invalid_email))) {
            Toast.makeText(this, getString(R.string.successful_validation), Toast.LENGTH_SHORT).show()
        }
    }
}
