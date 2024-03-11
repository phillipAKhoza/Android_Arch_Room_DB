package com.phillip_dev.knowtes.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.phillip_dev.knowtes.Model.Note
import com.phillip_dev.knowtes.R
import com.phillip_dev.knowtes.View.MainActivity
import com.phillip_dev.knowtes.View.UpdateActivity

class NoteAdapter(private  val activity: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.txt_title)
        val description : TextView = itemView.findViewById(R.id.txt_desc)
        val cardView : CardView = itemView.findViewById(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item,parent,false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var currentNote : Note = notes[position]

        holder.title.text = currentNote.title
        holder.description.text = currentNote.description

        holder.cardView.setOnClickListener {
            val intent = Intent(activity,UpdateActivity::class.java)
            intent.putExtra("currentTitle",currentNote.title)
            intent.putExtra("currentDescription",currentNote.description)
            intent.putExtra("currentId",currentNote.id)

            activity.updateActivityResultLauncher.launch(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNote(myNotes: List<Note>){
        this.notes = myNotes
        notifyDataSetChanged()
    }

    fun getNote(position : Int): Note{
        return notes[position]
    }
}