package com.example.tarea_vizuetealexis.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tarea_vizuetealexis.Images
import com.example.tarea_vizuetealexis.databinding.ItemImageBinding

class ImagesViewHolder (view:View) : RecyclerView.ViewHolder(view){

    //Uso de binding
    val binding = ItemImageBinding.bind(view)

    fun render(imageModel: Images){
        //Uso de binding
        binding.tvImageName.text = imageModel.name
        binding.tvImagePublisher.text = imageModel.publisher
        Glide.with(binding.ivImage.context).load(imageModel.photo).into(binding.ivImage)
        //Uso de lambdas
        itemView.setOnClickListener {
            Toast.makeText(
                binding.ivImage.context,
                imageModel.name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}