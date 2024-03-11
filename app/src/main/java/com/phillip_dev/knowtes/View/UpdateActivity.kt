package com.phillip_dev.knowtes.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.phillip_dev.knowtes.R

class UpdateActivity : AppCompatActivity() {
    lateinit var txtTitle : EditText
    lateinit var txtDescription : EditText
    lateinit var btnCancel : Button
    lateinit var btnSave : Button
    private var currentId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.title ="Update Note"
        txtTitle = findViewById(R.id.edit_txt_title_update)
        txtDescription = findViewById(R.id.edit_txt_description_update)
        btnCancel = findViewById(R.id.btn_cancel_update)
        btnSave = findViewById(R.id.btn_save_update)

        getAndSetData()

        btnCancel.setOnClickListener {

            finish()
        }
        btnSave.setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote(){
        val updatedTitle = txtTitle.text.toString()
        val updatedDescription = txtDescription.text.toString()

        val intent = Intent()
        intent.putExtra("updatedTitle",updatedTitle)
        intent.putExtra("updatedDescription",updatedDescription)
        if (currentId != -1){
            intent.putExtra("id",currentId)
            setResult(RESULT_OK,intent)
            finish()
        }else{
            Toast.makeText(applicationContext,"Note ID not found", Toast.LENGTH_LONG).show()
        }
    }

    fun getAndSetData(){
        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDescription = intent.getStringExtra("currentDescription")
        currentId = intent.getIntExtra("currentId",-1)

        txtTitle.setText(currentTitle)
        txtDescription.setText(currentDescription)
    }
}
