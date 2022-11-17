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
    private val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val q = findViewById<EditText>(R.id.zxc123)

        list.addAll(dbHelper.getALLTasks())

        adapter = RecyclerAdapter(list) {
            // TODO: удаление из БД

            dbHelper.deleteTask(list[it].id)

            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.button)
        buttonAdd.setOnClickListener {
            // TODO: добавление в БД
            val editText = findViewById<EditText>(R.id.zxc123)
            val s = editText.text.toString()
            editText.text.clear()
            val id = dbHelper.addTask(s)
            list.add(Task(id, s))
            adapter.notifyItemInserted(list.lastIndex)
        }

    }

}