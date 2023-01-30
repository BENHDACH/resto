package com.example.myapplication

import android.content.Intent
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
            showCategory(Category.STARTER)

        }
        binding.mainButton.setOnClickListener{
            //Log.d("button", "Click sur button plats")
           // Toast.makeText(this, "Plats", Toast.LENGTH_LONG).show()
            showCategory(Category.MAIN)
        }
        binding.finishButton.setOnClickListener{
            showCategory(Category.DESSERT)
        }
    }
    private fun showCategory(category: Category){
        val intent = Intent(this, PlatActivity::class.java)
        intent.putExtra(PlatActivity.extraKey, category)
        startActivity(intent)
    }
}
