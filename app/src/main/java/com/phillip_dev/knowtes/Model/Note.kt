package com.phillip_dev.knowtes.Model

import androidx.room.Entity

@Entity("note_table")
data class Note(val title: String, val description: String) {
}