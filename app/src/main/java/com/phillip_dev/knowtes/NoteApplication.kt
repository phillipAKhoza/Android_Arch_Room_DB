package com.phillip_dev.knowtes

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

}