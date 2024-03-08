package com.phillip_dev.knowtes.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.phillip_dev.knowtes.R

class AddNoteActivity : AppCompatActivity() {

    lateinit var txtTitle : EditText
    lateinit var txtDescription : EditText
    lateinit var btnCancel : Button
    lateinit var btnSave : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        txtTitle = findViewById(R.id.edit_txt_title)
        txtDescription = findViewById(R.id.edit_txt_description)
        btnCancel = findViewById(R.id.btn_cancel)
        btnSave = findViewById(R.id.btn_save)

        btnCancel.setOnClickListener {

            finish()
        }
        btnSave.setOnClickListener {
                saveNote()
        }
    }

    fun saveNote(){

        val title : String = txtTitle.text.toString()
        val description: String = txtDescription.text.toString()

        val intent = Intent()
        intent.putExtra("title",title)
        intent.putExtra("description",description)
        setResult(RESULT_OK,intent)

    }
}