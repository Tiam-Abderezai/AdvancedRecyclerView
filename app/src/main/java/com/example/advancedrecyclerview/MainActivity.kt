package com.example.advancedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private  var countryList = mutableListOf<String>()
    private  var displayList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemAdapter(displayList)
            }
        }
        countryList.add("United States of America")
        countryList.add("France")
        countryList.add("Egypt")
        countryList.add("Vietnam")
        countryList.add("Germany")
        countryList.add("Angola")
        countryList.add("Australia")
        countryList.add("Mexico")
        displayList.addAll(countryList)
    }


}