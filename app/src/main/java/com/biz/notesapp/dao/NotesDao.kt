package com.biz.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.biz.notesapp.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getRedDotNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getYellowDotNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getGreenDotNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes (notes : Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes (id : Int)

    @Update
    fun updateNotes(notes : Notes)

}