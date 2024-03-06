package com.phillip_dev.knowtes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.phillip_dev.knowtes.Model.Note
import com.phillip_dev.knowtes.Repository.NoteRepository


class NoteViewModel(private  val repository: NoteRepository): ViewModel() {

    val allNote : LiveData<List<Note>> = repository.allNotes.asLiveData()

}