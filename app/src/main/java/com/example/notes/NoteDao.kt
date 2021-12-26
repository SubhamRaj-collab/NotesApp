package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)  //suspend is a coroutine in android,
                                    // which takes less time w.r.t normal task of inserting and deleting.

    @Query("Select * from NotesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}