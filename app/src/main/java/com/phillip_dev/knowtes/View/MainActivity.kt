package com.phillip_dev.knowtes.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phillip_dev.knowtes.Adapters.NoteAdapter
import com.phillip_dev.knowtes.NoteApplication
import com.phillip_dev.knowtes.R
import com.phillip_dev.knowtes.ViewModel.NoteViewModel
import com.phillip_dev.knowtes.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =  LinearLayoutManager(this)
        val noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter
        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]

        noteViewModel.allNote.observe(this, Observer {

//            update ui
            noteAdapter.setNote(it)

        })
    }
}