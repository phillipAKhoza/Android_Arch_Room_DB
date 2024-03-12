package com.phillip_dev.knowtes.View

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.phillip_dev.knowtes.Notification.NotificationReceiver
import com.phillip_dev.knowtes.R
import java.util.Calendar

class AddNoteActivity : AppCompatActivity() {

    lateinit var txtTitle : EditText
    lateinit var txtDescription : EditText
    lateinit var btnCancel : Button
    lateinit var btnSave : Button
    lateinit var btnSetReminder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.title ="Add Note"
        txtTitle = findViewById(R.id.edit_txt_title)
        txtDescription = findViewById(R.id.edit_txt_description)
        btnCancel = findViewById(R.id.btn_cancel)
        btnSave = findViewById(R.id.btn_save)
        btnSetReminder = findViewById(R.id.btn_setRem)

        val canceler = Calendar.getInstance()
        val currentHour = canceler.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = canceler.get(Calendar.MINUTE)

        btnSetReminder.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(currentHour)
                .setMinute(currentMinutes)
                .setTitleText("Set Note Reminder")
                .build()

            timePicker.show(supportFragmentManager,"1")

            timePicker.addOnPositiveButtonClickListener {
                canceler.set(Calendar.HOUR_OF_DAY,timePicker.hour)
                canceler.set(Calendar.MINUTE,timePicker.minute)
                canceler.set(Calendar.SECOND,0)
                canceler.set(Calendar.MILLISECOND,0)

                val intent = Intent(applicationContext,NotificationReceiver::class.java)

                val pendingIntent = if(Build.VERSION.SDK_INT >= 23){
                    PendingIntent.getBroadcast(applicationContext,1,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }else{
                    PendingIntent.getBroadcast(applicationContext,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

                }
            }
        }

        btnCancel.setOnClickListener {

            finish()
        }
        btnSave.setOnClickListener {
                saveNote()
        }
    }

    private fun saveNote(){

        val title : String = txtTitle.text.toString()
        val description: String = txtDescription.text.toString()

        val intent = Intent()
        intent.putExtra("title",title)
        intent.putExtra("description",description)
        setResult(RESULT_OK,intent)
        finish()
    }
}