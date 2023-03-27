package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfitpart1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val consumptions = mutableListOf<Item>()
    private lateinit var consumptionsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_Light)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        consumptionsRecyclerView = findViewById(R.id.consumptions)
        val itemAdapter = ItemAdapter(this, consumptions)
        consumptionsRecyclerView.adapter = itemAdapter

        lifecycleScope.launch {
            (application as ItemApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { mappedList ->
                    consumptions.addAll(listOf(mappedList))
                    itemAdapter.notifyDataSetChanged()
                }
            }
        }

        consumptionsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            consumptionsRecyclerView.addItemDecoration(dividerItemDecoration)

        }
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener{
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}