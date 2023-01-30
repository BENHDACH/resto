package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.myapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonsListener()
    }

    private fun buttonsListener() {
        binding.starterButton.setOnClickListener{
            Log.d("button", "Click sur button entree")
            Toast.makeText(this, "Entree", Toast.LENGTH_LONG).show()

        }
        binding.mainButton.setOnClickListener{
            Log.d("button", "Click sur button plats")
            Toast.makeText(this, "Plats", Toast.LENGTH_LONG).show()
        }
        binding.finishButton.setOnClickListener{
            Log.d("button", "Click sur button dessert")
            Toast.makeText(this, "Dessert", Toast.LENGTH_LONG).show()
        }
    }
}
