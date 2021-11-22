package com.example.advancedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedrecyclerview.databinding.ActivityMainBinding
import java.util.*

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
        countryList.add("United Kingdom")
        countryList.add("Uganda")
        countryList.add("Brazil")
        countryList.add("South Korea")
        countryList.add("Nigeria")
        countryList.add("Italy")
        countryList.add("Iran")
        countryList.add("Mali")
        countryList.add("Japan")
        countryList.add("Russia")
        countryList.add("Spain")
        countryList.add("South Africa")
        countryList.add("Canada")
        countryList.add("New Zealand")
        countryList.add("Kazakhstan")
        countryList.add("Lybia")
        countryList.add("Saudi Arabia")
        countryList.add("Israel")
        countryList.add("Serbia")
        countryList.add("Moldova")
        countryList.add("Romania")
        countryList.add("Albania")
        countryList.add("Cypress")
        countryList.add("Greece")
        countryList.add("Rwanda")
        countryList.add("Chile")
        countryList.add("Cambodia")
        countryList.add("Guatemala")


        displayList.addAll(countryList)

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),0){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            var startPosition = viewHolder.layoutPosition
            var endPosition = target.layoutPosition

        Collections.swap(displayList, startPosition, endPosition)
            binding.recyclerview.adapter?.notifyItemMoved(startPosition,endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        var item: MenuItem = menu!!.findItem(R.id.action_search)

        if(item != null){
            var searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?) = true

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        var search = newText.lowercase(Locale.getDefault())
                        for(country in countryList){
                            if(country.lowercase(Locale.getDefault()).contains(search)){
                                displayList.add(country)
                            }
                            binding.recyclerview.adapter!!.notifyDataSetChanged()
                        }
                    } else {
                        displayList.apply {
                            clear()
                            addAll(countryList)
                            binding.recyclerview.adapter!!.notifyDataSetChanged()
                        }
                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}