package com.example.yurokbotik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<Task>()
    private lateinit var adapter: RecyclerAdapter
    private val dbHelper = DBHelper (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val q = findViewById<EditText>(R.id.zxc123)

        list.addAll(dbHelper.getALLTasks())

        adapter = RecyclerAdapter(list) {
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            list.add(q.text.toString())
            adapter.notifyItemInserted(list.lastIndex)
        }
    }
}