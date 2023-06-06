package com.mmm.note_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mmm.note_app.Entity.NoteEntity
import com.mmm.note_app.R
import com.mmm.note_app.databinding.NoteItemBinding

class NotesAdapter(updatePin: (NoteEntity) -> Unit) : Adapter<NotesAdapter.NotesHolder>() {

    var updatePin = updatePin

    var notes =  ArrayList<NoteEntity>()

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
                if (pin){
                    imgpin.setImageResource(R.drawable.pin)


                }else{

                    imgpin.setImageResource(R.drawable.unpin)

                }
                imgpin.setOnClickListener {


            updatePin.invoke(notes.get(position))

                }


            }


        }
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
        notifyDataSetChanged()

    }

    fun setNotes(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
    }

}