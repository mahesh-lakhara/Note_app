package com.mmm.note_app.Adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupMenu.OnMenuItemClickListener
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mmm.note_app.Entity.NoteEntity
import com.mmm.note_app.R

import com.mmm.note_app.databinding.NoteItemBinding

class NotesAdapter(updatePin: (NoteEntity) -> Unit, var update: (NoteEntity) -> Unit, var delete: (NoteEntity) -> Unit) : Adapter<NotesAdapter.NotesHolder>() {

    var updatePin = updatePin

    var notes =  ArrayList<NoteEntity>()
    lateinit var context : Context

    class NotesHolder(itemView: NoteItemBinding) : ViewHolder(itemView.root){
        var binding = itemView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        context = parent.context
        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesHolder(binding)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, @SuppressLint("RecyclerView") position: Int) {
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

        holder.itemView.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                var menu = PopupMenu(context,holder.itemView)
                menu.menuInflater.inflate(R.menu.menulist,menu.menu)


                menu.setOnMenuItemClickListener(object : OnMenuItemClickListener{
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId == R.id.update){
                            update.invoke(notes.get(position))
                        }
                         if (p0?.itemId == R.id.delete){
                            delete.invoke(notes.get(position))
                        }

                        return true

                    }

                })

                menu.show()
                return false
            }

        })
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
        notifyDataSetChanged()

    }

    fun setNotes(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
    }

}