package com.example.tarea_vizuetealexis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea_vizuetealexis.adapter.ImagesAdapter
import com.example.tarea_vizuetealexis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Uso de binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //Uso de binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    fun initRecyclerView(){
        //Divider Item Decoration
        val manager = GridLayoutManager(this, 3)
        val decoration = DividerItemDecoration(this, manager.orientation)

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerImages)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = ImagesAdapter(ImagesProvider.imagesList)

        //Uso de binding
        binding.recyclerImages.layoutManager = manager
        binding.recyclerImages.adapter = ImagesAdapter(ImagesProvider.imagesList)
        binding.recyclerImages.addItemDecoration(decoration)
    }
}