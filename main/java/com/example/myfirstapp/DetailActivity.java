package com.example.myfirstapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailActivity extends Fragment implements View.OnClickListener {

    ArrayList<Integer> selection = new ArrayList<>();
    TextView score_text;
    public int final_taskSelection = 0;
    public NoteViewModel model;


    public DetailActivity() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(NoteViewModel.class);

        model.getCurrentScore().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {

                //score_text.setText(s);

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.activity_detail, container, false);

        score_text = view.findViewById(R.id.scoreView);
        score_text.setEnabled(false);
        Button btt = view.findViewById(R.id.butt_score);
        CheckBox running = view.findViewById(R.id.task_run);
        running.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox myhobby = view.findViewById(R.id.task_hobby);
        myhobby.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox myfam = view.findViewById(R.id.task_fam);
        myfam.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox eating = view.findViewById(R.id.task_eat);
        eating.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox works = view.findViewById(R.id.task_work);
        works.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox self = view.findViewById(R.id.task_self);
        self.setOnCheckedChangeListener(myCheckboxListener);
        CheckBox drm = view.findViewById(R.id.task_dream);
        drm.setOnCheckedChangeListener(myCheckboxListener);
        btt.setOnClickListener(this);


        //Intent in = getIntent();
        //int index = in.getIntExtra("com.example.INDEX",-1);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butt_score:


                for (Integer Selections : selection) {

                    final_taskSelection += Selections;
                }



                score_text.setText("Score is confirmed");
                score_text.setEnabled(true);

                model.setCurrentScore(final_taskSelection);

                break;

        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*You should inflate your layout in onCreateView but shouldn't
    initialize other views using findViewById in onCreateView.

    Because sometimes view is not properly initialized.
    So always use findViewById in onViewCreated(when view is fully created)
    and it also passes the view as parameter.

    onViewCreated is a make sure that view is fully created.*/
    }


    private CompoundButton.OnCheckedChangeListener myCheckboxListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //boolean checked = ((CheckBox) view).isChecked();
                    switch (buttonView.getId()) {
                        case R.id.task_run:
                            if (isChecked) {
                                selection.add(4);
                            } else {
                                selection.remove(Integer.valueOf(4));
                            }
                            break;

                        case R.id.task_work:
                            if (isChecked) {
                                selection.add(5);
                            } else {
                                selection.remove(Integer.valueOf(5));
                            }
                            break;

                        case R.id.task_dream:
                            if (isChecked) {
                                selection.add(2);
                            } else {
                                selection.remove(Integer.valueOf(2));
                            }
                            break;

                        case R.id.task_fam:
                            if (isChecked) {
                                selection.add(1);
                            } else {
                                selection.remove(Integer.valueOf(1));
                            }
                            break;

                        case R.id.task_hobby:
                            if (isChecked) {
                                selection.add(3);
                            } else {
                                selection.remove(Integer.valueOf(3));
                            }
                            break;

                        case R.id.task_self:
                            if (isChecked) {
                                selection.add(7);
                            } else {
                                selection.remove(Integer.valueOf(7));
                            }
                            break;

                        case R.id.task_eat:
                            if (isChecked) {
                                selection.add(6);
                            } else {
                                selection.remove(Integer.valueOf(6));
                            }
                            break;

                    }
                }
            };

}
