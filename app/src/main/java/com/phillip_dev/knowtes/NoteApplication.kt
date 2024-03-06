package com.phillip_dev.knowtes

import android.app.Application
import com.phillip_dev.knowtes.Db.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        NoteDatabase.getDataBase(this)
    }
}