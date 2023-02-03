package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityPlatBinding
import com.example.myapplication.network.MenuResult
import com.example.myapplication.network.NetworkConstants
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import org.json.JSONObject

enum class Category { STARTER , MAIN , DESSERT }
class PlatActivity : AppCompatActivity() {

    companion object{
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityPlatBinding
    lateinit var currentCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatBinding.inflate(layoutInflater)
        setContentView(binding.root)//charge le bon layout (celui ici), note: definir avec R va juste cherche le layout pas activer le binding avec lui (root fait les deux)

        val category = intent.getSerializableExtra(extraKey) as? Category //C'est un category optionnel !

        currentCategory = category ?: Category.STARTER //On donne une val par def

        supportActionBar?.title = categoryName() //Si cat est null alors Starter par def


        makeRequest()
       // Toast.makeText(this,intent.getStringExtra("NameMain"), Toast.LENGTH_LONG).show()
    }

    private fun makeRequest(){
        val queue = Volley.newRequestQueue(this)
        val params = JSONObject()
        params.put(NetworkConstants.idShopKey, 1)
        val request = JsonObjectRequest(
            Method.POST,
            NetworkConstants.url,
            params,
            { result ->
                Log.d("request", result.toString(2))
                parseData(result.toString())
            },
            { error ->
                Log.e("request", error.toString())
            }
        )
        queue.add(request)
        //showData()
    }

    private fun parseData(data: String){
        val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
        val category = result.data.first { it.name == categoryFilterKey() }
        showDatas(category)
    }




    private fun showDatas(category: com.example.myapplication.network.Category){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //val imageUrl = "https://images.bfmtv.com/UDAdpp33jU96JAWSB1v2R8KbfUg=/0x0:1196x1192/600x0/images/-458880.jpg"
        //Picasso.get().load(imageUrl).into(binding.imageTest)
        binding.recyclerView.adapter = CustomerAdapter(category.items) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        } // acolade à la fin pareil que dans la parenthèse car une seul valeur de toute façon...
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "MenuActivity onStart")

    }

    private fun categoryName(): String{
        return when(currentCategory){
            Category.STARTER -> getString(R.string.starter)//On recupere le titre de nos bouton selon la category
            Category.MAIN -> getString(R.string.main)
            Category.DESSERT -> getString(R.string.finish)
        }
    }

    private fun categoryFilterKey():String{
        return when(currentCategory) {
            Category.STARTER -> "Entrées"
            Category.MAIN -> "Plats"
            Category.DESSERT -> "Desserts"
        }

    }

    override fun onDestroy() {
        Log.i("onDestroy","ByeBye")
        super.onDestroy()
    }
}