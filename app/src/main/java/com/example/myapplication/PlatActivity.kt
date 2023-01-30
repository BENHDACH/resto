package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityPlatBinding

enum class Category { STARTER , MAIN , DESSERT }
class PlatActivity : AppCompatActivity() {

    companion object{
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityPlatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getSerializableExtra(extraKey) as? Category

        supportActionBar?.title = categoryName(category ?: Category.STARTER) //Si cat est null alors Starter par def



       // Toast.makeText(this,intent.getStringExtra("NameMain"), Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "MenuActivity onStart")

    }

    private fun categoryName(category : Category): String{
        return when(category){
            Category.STARTER -> getString(R.string.starter)//On recupere le titre de nos bouton selon la category
            Category.MAIN -> getString(R.string.main)
            Category.DESSERT -> getString(R.string.finish)
        }
    }

    override fun onDestroy() {
        Log.i("onDestroy","ByeBye")
        super.onDestroy()
    }
}