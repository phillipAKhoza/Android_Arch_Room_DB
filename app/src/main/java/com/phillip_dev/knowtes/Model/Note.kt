package com.phillip_dev.knowtes.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("note_table")
data class Note(val title: String, val description: String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int =0
}