package com.biz.notesapp.repository

import androidx.lifecycle.LiveData
import com.biz.notesapp.dao.NotesDao
import com.biz.notesapp.model.Notes

class NotesRepository(val dao : NotesDao) {

    fun getAllNotes() : LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getRedDotNotes() : LiveData<List<Notes>>{
        return dao.getRedDotNotes()
    }

    fun getYellowDotNotes() : LiveData<List<Notes>>{
        return dao.getYellowDotNotes()
    }

    fun getGreenDotNotes() : LiveData<List<Notes>>{
        return dao.getGreenDotNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id : Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}