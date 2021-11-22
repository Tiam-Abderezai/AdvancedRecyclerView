package com.example.advancedrecyclerview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedrecyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private  var countryList = mutableListOf<String>()
    private  var displayList = mutableListOf<String>()
    private lateinit var deletedCountry: String
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

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val startPosition = viewHolder.layoutPosition
            val endPosition = target.layoutPosition

        Collections.swap(displayList, startPosition, endPosition)
            binding.recyclerview.adapter?.notifyItemMoved(startPosition,endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            when(direction){
                ItemTouchHelper.LEFT -> {
                    deletedCountry = displayList[position]
                    displayList.removeAt(position)
                    binding.recyclerview.adapter?.notifyItemRemoved(position)
                    Snackbar.make(binding.recyclerview, "$deletedCountry is deleted", Snackbar.LENGTH_LONG).setAction("Undo", View.OnClickListener {
                        displayList.add(position, deletedCountry)
                        binding.recyclerview.adapter?.notifyItemInserted(position)
                    }).show()
                }

            ItemTouchHelper.RIGHT -> {
                val editText = EditText(this@MainActivity)
                editText.setText(displayList[position])
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.apply {
                    setTitle("Update an Item")
                    setCancelable(true)
                    setView(editText)
                    setNeutralButton("cancel", DialogInterface.OnClickListener { dialog, which ->
                        displayList.apply {
                            clear()
                            addAll(countryList)
                            binding.recyclerview.adapter?.notifyDataSetChanged()
                        }
                    })
                    setPositiveButton("update", DialogInterface.OnClickListener { dialog, which ->
                        displayList.apply {
                            set(position, editText.getText().toString())
                            binding.recyclerview.adapter?.notifyItemChanged(position)
                        }
                    })
                    builder.show()
                }

            }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item: MenuItem = menu!!.findItem(R.id.action_search)

        if(item != null){
            val searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?) = true

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.lowercase(Locale.getDefault())
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