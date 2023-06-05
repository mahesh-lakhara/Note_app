package com.mmm.note_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mmm.note_app.Entity.NoteEntity
import com.mmm.note_app.databinding.NoteItemBinding

class NotesAdapter(notes: List<NoteEntity>) : Adapter<NotesAdapter.NotesHolder>() {

    var notes = notes

    class NotesHolder(itemView: NoteItemBinding) : ViewHolder(itemView.root){
        var binding = itemView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesHolder(binding)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.binding.apply {
            txtTitle.isSelected = true
            notes.get(position).apply {

                txtTitle.text = title
                txtText.text = text
            }


        }
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes
        notifyDataSetChanged()

    }

}