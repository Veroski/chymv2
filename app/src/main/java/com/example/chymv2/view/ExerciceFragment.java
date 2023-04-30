package com.example.chymv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chymv2.R;
import com.example.chymv2.adapters.ExerciceListAdapter;
import com.example.chymv2.model.ListExercice;
import com.example.chymv2.sources.InitializeData;
import com.example.chymv2.viewmodel.ExercicesViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciceFragment extends Fragment implements SearchView.OnQueryTextListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ExerciceListAdapter exerciceListAdapter;
    private ExercicesViewModel exercicesViewModel;
    private androidx.appcompat.widget.SearchView svSearch;
    private InitializeData data;

    public ExerciceFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment exerciceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciceFragment newInstance(String param1, String param2) {
        ExerciceFragment fragment = new ExerciceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_exercice, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        data = new InitializeData(getContext());
        exercicesViewModel = new ExercicesViewModel(getContext());
        initlistaEjercicios(view);
        initListenerExercices();
    }

    private void initlistaEjercicios(View view) {
        exerciceListAdapter = new ExerciceListAdapter(exercicesViewModel.getExercices().getValue(), getContext(), new ExerciceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListExercice item) {
                moveToDescription(item);
            }
        });
        exerciceListAdapter.notifyDataSetChanged();
        svSearch = view.findViewById(R.id.svSearch);

        recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(exerciceListAdapter);
    }

    private void initListenerExercices(){
        svSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        exerciceListAdapter.filter(s);
        exerciceListAdapter.notifyDataSetChanged();
        return false;
    }
    public void moveToDescription(ListExercice item){
        Intent intent = new Intent(getContext(), ExerciceDescriptionActivity.class);
        intent.putExtra("ListExercice", item);
        startActivity(intent);
    }
}