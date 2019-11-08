package com.example.myfirstapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.myfirstapp.Repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteViewModel model;
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    public String NotesResult;
    public List<Checks> NotesChecks;


    private MutableLiveData<ArrayList<Checks>> arritem = new MutableLiveData<>();
    private MutableLiveData<Integer> currentScore = new MutableLiveData<>();
    private ArrayList<Integer> arrr = new ArrayList<>();
    private int sum = 0;

    public LiveData<Integer> getCurrentScore(){

        return currentScore;
    }

   // String string = getResultNote();

    public void setCurrentScore(Integer finito) {

        if (finito == 0) {
            sum = finito;
            currentScore.setValue(sum);
            arrr.clear();
        }
        else {
            arrr.add(finito);
            for (int i = 0; i < arrr.size(); i++) {

                sum += arrr.get(i);
            }
            currentScore.setValue(sum);
            sum = 0;
        }

    }

    public LiveData<ArrayList<Checks>> getMyItems(){

        return arritem;
    }

    public void setmyItems(ArrayList<Checks> finito) {
        arritem.setValue(finito);

    }

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
        //NotesResult = repository.getNotesResult();
    }

    public void insert(Note note) {

        repository.insert(note);

    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public String getResultNote() {
       NotesResult = repository.getNotesResult();
        return NotesResult; }

    public List<Checks> getChecksNotes() {
        NotesChecks = repository.getNotesChecks();
        return NotesChecks; }

}
