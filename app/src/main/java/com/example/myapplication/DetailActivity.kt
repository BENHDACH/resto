package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.network.Achat
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import java.io.File

class DetailActivity : AppCompatActivity() {

    companion object{
        val extraKey = "extraKey"
    }

    lateinit var currentCategory: Category
    lateinit var binding: ActivityDetailBinding



    //------------>
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: PagerAdapter
    private lateinit var achat : MutableList<Achat>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //---->
        val viewPager : ViewPager2
        //---->
        var compteur = "1"
        var textIngre = "Ingrédients : "
        var imageView : String? = intent.getStringExtra("platImage")

        //supportActionBar?.apply { setLogo(R.drawable.[QUE METTRE ?]) } //R.drawable. ? rien ne s'affiche (j'ai tester quelque proposition) donc pas de chariot non plus...

        binding.titleName.text = intent.getStringExtra("platNom")
        binding.priceTotal.text = intent.getStringExtra("platPrix")+"$"

        for (i in 1.. intent.getIntExtra("countIng", 1)){
            textIngre = textIngre+intent.getStringExtra("platIngre${i}")+","
        }
        binding.ingredientName.text = textIngre

        viewPager = findViewById(binding.pager.id)
        //arrayList.add(binding.imgId)
        //val megaAdapter = PagerAdapter(this,arrayList)
        //viewPager.setAdapter(megaAdapter)



        binding.moinsButton.setOnClickListener{
            if(compteur.toInt()>1){
                compteur = "${compteur.toInt()-1}"
                binding.count.text = compteur
                binding.priceTotal.text = "${intent.getStringExtra("platPrix").toString().toFloat() * compteur.toInt()}"+"$"

            }
        }
        binding.plusButton.setOnClickListener{
            compteur = "${compteur.toInt()+1}"
            binding.count.text = compteur
            binding.priceTotal.text = "${intent.getStringExtra("platPrix").toString().toFloat() * compteur.toInt()}"+"$"


        }
        binding.count.text = compteur

        binding.priceTotal.setOnClickListener{
            Toast.makeText(this,"Je ne peux pas créer de fichier donc voila voila...",Toast.LENGTH_LONG).show()
            addAchat(intent.getStringExtra("platNom").toString(),intent.getStringExtra("platPrix").toString().toInt(),compteur.toInt())
        }

        //----------------------------->


        init()

    }

    private fun init(){

        var imageList = ArrayList<String>()

        for(i in 1..intent.getIntExtra("countImg",1)){
            imageList.add(intent.getStringExtra("platImage${i}").toString())
        }


        // Set adapter to viewPager.
        binding.pager.adapter = PagerAdapter(this, imageList)

    }

    private fun addAchat(nom:String,compte:Int,prix:Int){
        achat = mutableListOf<Achat>()
        //achat.add(Achat("B",1,6))
        achat.add(Achat("$nom",compte,prix))


        val prepa = GsonBuilder().setPrettyPrinting().create()
        val jsonText = prepa.toJson(achat)
        //inutile ne marche pas non plus...
        //File(cacheDir.absolutePath,"D:\\Documents\\M1\\AppMobil\\MyApplication\\listAchat.json").createNewFile()

        //Peut importe le chemin, "listAchat.json" ou "..\\'...'" ou chemin total depuis D: le fichier n'apparais jamais, pire ne mettre que le chemin (1 seul paramètre) me fait crash directement
        File(cacheDir.absolutePath,"D:\\Documents\\M1\\AppMobil\\MyApplication\\listAchat.json").writeText(jsonText)
    }



}

