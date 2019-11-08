package com.example.myfirstapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();


    @Query("SELECT res_col FROM note_table ORDER BY res_col DESC LIMIT 1;")
    String getResultNote();

    @TypeConverters({Converters.class})
    @Query("SELECT checks_col FROM note_table ORDER BY checks_col DESC LIMIT 1;")
    List<Checks> getChecksNotes();

}
