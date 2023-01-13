package com.biz.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.biz.notesapp.database.NotesDatabase
import com.biz.notesapp.model.Notes
import com.biz.notesapp.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes() : LiveData<List<Notes>> = repository.getAllNotes()

    fun getRedDotNotes() : LiveData<List<Notes>> = repository.getRedDotNotes()
    fun getYellowDotNotes() : LiveData<List<Notes>> = repository.getYellowDotNotes()
    fun getGreenDotNotes() : LiveData<List<Notes>> = repository.getGreenDotNotes()

    fun deleteNotes(id : Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}