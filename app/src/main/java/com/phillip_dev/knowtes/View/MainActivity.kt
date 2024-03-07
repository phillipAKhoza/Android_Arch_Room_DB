package com.phillip_dev.knowtes.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.phillip_dev.knowtes.NoteApplication
import com.phillip_dev.knowtes.R
import com.phillip_dev.knowtes.ViewModel.NoteViewModel
import com.phillip_dev.knowtes.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
    }
}