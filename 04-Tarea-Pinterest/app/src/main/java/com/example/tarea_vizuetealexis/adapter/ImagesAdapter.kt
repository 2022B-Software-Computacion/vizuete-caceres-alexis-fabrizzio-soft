package com.example.tarea_vizuetealexis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea_vizuetealexis.Images
import com.example.tarea_vizuetealexis.R

class ImagesAdapter (private val imagesList:List<Images>) : RecyclerView.Adapter<ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImagesViewHolder(layoutInflater.inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = imagesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = imagesList.size

}