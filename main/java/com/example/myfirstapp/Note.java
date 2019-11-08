package com.example.myfirstapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;

@Entity(tableName = "note_table")
public class Note {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_col")
    private int id;

    @TypeConverters({Converters.class})
    @ColumnInfo(name = "checks_col")
    private ArrayList<Checks> checks = new ArrayList<>();

    @ColumnInfo(name = "res_col")
    private String result;



    public Note(int id , String result, ArrayList<Checks> checks) {
        this.id = id;
        this.checks = checks;
        this.result = result;
    }

    @Ignore
    public Note() {

    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public void setResult(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @TypeConverters({Converters.class})
    public void setChecks(ArrayList<Checks> checks){
        this.checks = checks;
    }

    @TypeConverters({Converters.class})
    public ArrayList<Checks> getChecks() {
        return checks;
    }


}

