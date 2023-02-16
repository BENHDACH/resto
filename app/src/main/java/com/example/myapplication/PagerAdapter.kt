package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CellCustomBinding
import com.example.myapplication.databinding.CellPagerBinding
import com.example.myapplication.network.Plate
import com.squareup.picasso.Picasso

class PagerAdapter (private val context: Context,
private val imageUrls: MutableList<String>
) : RecyclerView.Adapter<PagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = CellPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val url = imageUrls[position]

        holder.bind(url)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    class ViewPagerHolder(private var itemHolderBinding: CellPagerBinding) :
        RecyclerView.ViewHolder(itemHolderBinding.root) {
        fun bind(url : String) {
            //Soucis certain ne sont pas null mais leur site n'est plus accessible donc aucune image...
            if(url==""){
                Picasso.get().load("https://cdn-icons-png.flaticon.com/512/5115/5115845.png").into(itemHolderBinding.pagerViewImg)
            }
            else{
                Picasso.get().load(url).into(itemHolderBinding.pagerViewImg)
            }

            //itemHolderBinding.root.setBackgroundColor(Color.parseColor(color))


        }
    }


}


/*
(private val context: Context, private val imageUrls: List<String>) : RecyclerView.Adapter<PagerAdapter.ImageViewHolder>() {
class ImageViewHolder(binding: CellPagerBinding,itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Je suppose que sa peut être ici
    val imageView: ImageView = binding.pagerView
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    //Je ne vois pas le problème ici non plus
    val view = LayoutInflater.from(context).inflate(R.layout.cell_pager, parent, false)
    val binding = CellPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ImageViewHolder(binding,view)
}

override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
   //Probleme de shutdown n'est pas dans onBindViewHolder (tester et mit en //)
   Picasso.get().load(imageUrls[position]).into(holder.imageView)

}

override fun getItemCount(): Int {
    //Probleme peut être ici en mettant return(1) je passe toujours au moins 4 fois dans cette function... peut être juste pour check (?)

    return (imageUrls.size)
}

}

*/