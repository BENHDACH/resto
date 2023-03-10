package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CellCustomBinding
import com.example.myapplication.network.Ingredient
import com.example.myapplication.network.Plate
import com.squareup.picasso.Picasso

class CustomerAdapter(val items: List<Plate>, val clickListener: (String,String,String,List<Ingredient>, Plate,List<String>) -> Unit) :RecyclerView.Adapter<CustomerAdapter.CellViewHolder>() {
    class CellViewHolder(binding: CellCustomBinding): RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.textView2//Id du text dans cell_custom.xml pour (1,2,3 au début)
        val imageView = binding.imageView2
        val priceTextView = binding.priceTextView
        val root = binding.root
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val plate = items[position]
        holder.textView.text = plate.name
        holder.priceTextView.text = plate.prices.first().price + " $"
        Picasso.get().load(getThumbnail(plate)).into(holder.imageView)
        holder.root.setOnClickListener {
            Log.e("click", "click on ${plate.images}")
            clickListener(plate.name, plate.prices.first().price, plate.images.toString(), plate.ingredient, plate, plate.images)
        }
    }


    private fun getThumbnail(plate: Plate): String? {
        return if (plate.images.isNotEmpty() && plate.images.firstOrNull()?.isNotEmpty() == true){
            plate.images.firstOrNull()
        } else {
            Log.e("LAm","Voici")
            null
        }
    }

}
