package com.example.notesappbm.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappbm.database.Note
import com.example.notesappbm.database.RoomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
// AndroidViewModel = Context of all app not just activity
class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val db: RoomDBHelper = RoomDBHelper.getInstance(app)

    fun upsertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) { db.noteDao.upsertNote(note) }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) { db.noteDao.deleteNote(note) }
    }

    fun getNotes() = db.noteDao.getAllNotes()

}