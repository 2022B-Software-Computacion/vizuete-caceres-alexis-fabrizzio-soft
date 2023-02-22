package com.example.tarea_vizuetealexis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tarea_vizuetealexis.adapter.ImagesAdapter
import com.example.tarea_vizuetealexis.databinding.ActivityMainBinding
import com.example.tarea_vizuetealexis.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    //Uso de binding
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)

        //Uso de binding
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

    }

    fun initRecyclerView(){

        //RECYCLERVIEW 2
        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val decoration = DividerItemDecoration(this, manager.orientation)

        //Uso de binding
        binding.recyclerImagesSearch1.layoutManager = manager
        binding.recyclerImagesSearch1.adapter = ImagesAdapter(ImagesProvider.imagesList)
        binding.recyclerImagesSearch1.addItemDecoration(decoration)


        //RECYCLERVIEW 2
        val manager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val decoration2 = DividerItemDecoration(this, manager2.orientation)

        //Uso de binding
        binding.recyclerImagesSearch2.layoutManager = manager2
        binding.recyclerImagesSearch2.adapter = ImagesAdapter(ImagesProvider.imagesList)
        binding.recyclerImagesSearch2.addItemDecoration(decoration2)

        //RECYCLERVIEW 3
        val manager3 = GridLayoutManager(this, 2)
        val decoration3 = DividerItemDecoration(this, manager3.orientation)

        //Uso de binding
        binding.recyclerImagesSearch3.layoutManager = manager3
        binding.recyclerImagesSearch3.adapter = ImagesAdapter(ImagesProvider.imagesList)
        binding.recyclerImagesSearch3.addItemDecoration(decoration3)

        //RECYCLERVIEW 4
        val manager4 = GridLayoutManager(this, 2)
        val decoration4 = DividerItemDecoration(this, manager4.orientation)

        //Uso de binding
        binding.recyclerImagesSearch4.layoutManager = manager4
        binding.recyclerImagesSearch4.adapter = ImagesAdapter(ImagesProvider.imagesList)
        binding.recyclerImagesSearch4.addItemDecoration(decoration4)



    }
}