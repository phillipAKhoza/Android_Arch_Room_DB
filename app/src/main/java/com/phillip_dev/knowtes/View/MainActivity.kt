package com.phillip_dev.knowtes.View

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phillip_dev.knowtes.Adapters.NoteAdapter
import com.phillip_dev.knowtes.Model.Note
import com.phillip_dev.knowtes.NoteApplication
import com.phillip_dev.knowtes.R
import com.phillip_dev.knowtes.ViewModel.NoteViewModel
import com.phillip_dev.knowtes.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var updateActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =  LinearLayoutManager(this)
        val noteAdapter = NoteAdapter(this)
        recyclerView.adapter = noteAdapter

        registerActivityResultLauncher()


        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

        noteViewModel = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class.java]

        noteViewModel.allNote.observe(this, Observer {

//            update ui
            noteAdapter.setNote(it)

        })

        ItemTouchHelper(object  : ItemTouchHelper.SimpleCallback(
            0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.deleteNote(noteAdapter.getNote(viewHolder.adapterPosition))
                Toast.makeText(applicationContext,"Note deleted", Toast.LENGTH_LONG).show()
            }

        }).attachToRecyclerView(recyclerView)
    }
     fun registerActivityResultLauncher(){

        addActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                val resultCode = it.resultCode
                val data = it.data

                if(resultCode == RESULT_OK && data != null){
                    val noteTitle: String = data.getStringExtra("title").toString()
                    val noteDescription: String = data.getStringExtra("description").toString()

                    val note = Note(noteTitle,noteDescription)

                    noteViewModel.addNote(note)
                }
            }
        )
        updateActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                val resultCode = it.resultCode
                val data = it.data

                if(resultCode == RESULT_OK && data != null){
                        val updateTitle: String = data.getStringExtra("updatedTitle").toString()
                        val updateDescription: String = data.getStringExtra("updatedDescription").toString()
                        val noteId: Int = data.getIntExtra("id",-1).toInt()

                    val note = Note(updateTitle,updateDescription)
                    note.id = noteId

                    noteViewModel.updateNote(note)
                }
            }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_note_item ->{
                val intent = Intent(this,AddNoteActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.delete_all_item ->{
                showDeleteMessage()
            }
        }
        return  true
    }

    private fun showDeleteMessage(){
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Delete All Notes")
        dialogMessage.setMessage("Do you want to delete all notes? swipe left or right to delete one note")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            noteViewModel.deleteAllNotes()
        })

        dialogMessage.create().show()
    }
}

