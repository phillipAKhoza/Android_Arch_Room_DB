package com.phillip_dev.knowtes.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.phillip_dev.knowtes.R

class UpdateActivity : AppCompatActivity() {
    lateinit var txtTitle : EditText
    lateinit var txtDescription : EditText
    lateinit var btnCancel : Button
    lateinit var btnSave : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar?.title ="Update Note"
        txtTitle = findViewById(R.id.edit_txt_title_update)
        txtDescription = findViewById(R.id.edit_txt_description_update)
        btnCancel = findViewById(R.id.btn_cancel_update)
        btnSave = findViewById(R.id.btn_save_update)

        btnCancel.setOnClickListener {

            finish()
        }
        btnSave.setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote(){

    }

    fun getAndSetData(){
        val currentTitle = intent.getStringExtra("currentTitle").toString()
        val currentDescription = intent.getStringExtra("currentDescription").toString()
        val currentId = intent.getIntExtra("currentId",-1)

        txtTitle.setText(currentTitle)
        txtDescription.setText(currentDescription)
    }
}
