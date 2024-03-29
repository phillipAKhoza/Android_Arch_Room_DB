package com.phillip_dev.knowtes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.phillip_dev.knowtes.Model.Note
import com.phillip_dev.knowtes.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class NoteViewModel(private  val repository: NoteRepository): ViewModel() {

    val allNote : LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun addNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.addNote(note)
    }

    fun updateNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun deleteAllNotes()= viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNote()
    }


}
class  NoteViewModelFactory(private  var repository: NoteRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown View Model")
        }
    }
}