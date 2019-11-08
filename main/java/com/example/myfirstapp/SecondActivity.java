package com.example.myfirstapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.InvalidationTracker;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class SecondActivity extends Fragment {

    public SecondActivity() {
        // Required empty public constructor
    }
    InvalidationTracker inv;
    NoteDao noteDao;
    NoteDataBase instance;
    private NoteViewModel model;
    TextView finalScore;
    View v;
    ItemAdapter itemAdapter;
    Integer score;
    public ArrayList<Checks> itemList = new ArrayList<>();
    private static final String STATE_LIST = "State Adapter Data";


    Button clr;
    ListView myListView;
    String[] items;
    String[] bushido;
    String[] description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, myListView.getAdapter().getList());
    }
    */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        model = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);


        model.getCurrentScore().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {


                    if (s != null) {
                        finalScore.setText("your current score is" + " " + String.valueOf(s));

                    }
            }
        });

        model.getMyItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<Checks>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Checks> itemss) {

                itemList = itemss;
                itemAdapter.updateItems(itemList ,items, bushido, description);
            }

        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_second, container, false);


        finalScore = view.findViewById(R.id.finalScoreViewNew);
        clr = view.findViewById(R.id.clearButton);

        //finalScore.setText(noteDao.getResultNote());
        //if (model.getResultNote() != null) {
         //  finalScore.setText(model.getResultNote().toString());
        //}

        Resources res = getResources();
        myListView = view.findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items);
        bushido = res.getStringArray(R.array.bushido);
        description = res.getStringArray(R.array.descriptions);



        //finalScore.setText(String.valueOf(score));

         itemAdapter = new ItemAdapter(getActivity(),itemList,items,bushido,description,score ,finalScore
         );

      /* if RecyclerView , model.getCurrentScore().getValue() */
        myListView.setAdapter(itemAdapter);


        if (itemList.size() == 0) {
        for (int i = 0; i < 7; i++) {

        itemList.add(new Checks("no"));

        }
        }


        itemAdapter.notifyDataSetChanged();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String changedName = "Check";
                itemAdapter.getItem(position).setItemName(changedName);
                model.setmyItems(itemList);

                NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_myListViewFragment_to_detailActivityFragment);


            }
        });

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.setCurrentScore(0);
                for (int i = 0; i < 7; i++) {

                    itemList.set(i, new Checks("no"));

                }
                model.setmyItems(itemList);

            }
        });




/*
        Log.d("APPINFO","Retrieving the Database Instance");
        instance = NoteDataBase.getInstance(getActivity());
        Log.d("APPINFO","Retrieving the Database DAO");
        noteDao = instance.noteDao();
        //allNotes = noteDao.getAllNotes();

        Log.d("APPINFO","Mock backup/close");
        instance.backup(getActivity());
        Log.d("APPINFO","Retrieveing the Database Instance after the close");
        instance = NoteDataBase.getInstance(getActivity());
        Log.d("APPINFO","Retrieving Rows from the Database");
        //allNotes = noteDao.getAllNotes();
        //Log.d("MYTABLEINFO","ID = " + allNotes + " VALUE = " + allNotes.getValue());
        Log.d("APPINFO","Closing the database");
        instance.close();
        //allNotes = noteDao.getAllNotes();
        //Log.d("MYTABLEINFO","ID = " + allNotes + " VALUE = " + allNotes.getValue());
*/

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_note_menu ,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save_note:
                saveNote();
                return true;


        }

        return false;
    }

    private void saveNote(){


        String savedscore = finalScore.getText().toString();
        /*
        ArrayList<String> daycheks =  new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            daycheks.add(itemList.get(i).toString());
        }
        */

        //String res = savedscore.replaceAll("[a-z]","");
        //String fin = res.replaceAll(" ","");
        Note note = new Note(0, savedscore , itemList);

            model.insert(note);
            Log.d("APPINFO", "Insertion Complete");

            NoteDataBase.getInstance(getActivity()).checkpoint();


            //NoteDataBase.getInstance(getActivity()).close();




        Toast.makeText(getContext() , "Result saved" , Toast.LENGTH_SHORT).show();

    }

}
