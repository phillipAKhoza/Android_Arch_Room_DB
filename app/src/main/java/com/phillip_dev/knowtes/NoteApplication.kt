package com.phillip_dev.knowtes

import android.app.Application
import com.phillip_dev.knowtes.Db.NoteDatabase
import com.phillip_dev.knowtes.Repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        NoteDatabase.getDataBase(this, applicationScope)
    }
    val repository by lazy {
        NoteRepository(database.getNoteDao())
    }
}