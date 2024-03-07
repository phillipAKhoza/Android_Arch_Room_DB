package com.phillip_dev.knowtes.Adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.phillip_dev.knowtes.R

class NoteAdapter {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.txt_title)
        val description : TextView = itemView.findViewById(R.id.txt_desc)
    }
}