package com.phillip_dev.knowtes.Db

import android.content.Context
import androidx.annotation.RestrictTo.Scope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.phillip_dev.knowtes.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{
        private  var INSTANCE : NoteDatabase? = null

        fun getDataBase(context: Context, scope: CoroutineScope) : NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                   context.applicationContext ,NoteDatabase::class.java,"note_database"
                )
                    .addCallback(NoteDatabaseCallBack(scope))
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }

    private class  NoteDatabaseCallBack(private  val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let {
                scope.launch {
                    val noteDao = it.getNoteDao()
                }
            }
        }
    }

}