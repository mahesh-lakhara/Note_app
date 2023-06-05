package com.mmm.note_app

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mmm.note_app.Adapter.NotesAdapter
import com.mmm.note_app.Database.RoomDB
import com.mmm.note_app.Entity.NoteEntity
import com.mmm.note_app.databinding.ActivityMainBinding
import com.mmm.note_app.databinding.AddDialogBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {


    lateinit var db : RoomDB
    lateinit var adpter : NotesAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.init(this)


     initview()

    }

    private fun initview() {
        binding.add.setOnClickListener {
            addNoteDialog()

        }
        adpter = NotesAdapter(db.note().getNotes())
        binding.noteList.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.noteList.adapter = adpter

    }

    private fun addNoteDialog() {
        var dialog = Dialog(this)
        var bind = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)


        bind.btnsubmit.setOnClickListener {
            var title = bind.edtTitle.text.toString()
            var text = bind.edtText.text.toString()
            var format = SimpleDateFormat("DD/MM/YYYY hh:mm")
            var current = format.format(Date())
            var data = NoteEntity(title, text, current)
            db.note().addNote(data)
            adpter.update(db.note().getNotes())
            dialog.dismiss()


        }

        dialog.show()



    }
}