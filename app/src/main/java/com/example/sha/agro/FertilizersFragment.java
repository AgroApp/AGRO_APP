package com.example.sha.agro;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class FertilizersFragment extends Fragment {

    //private DatabaseReference mDatabase;
    private FirebaseFirestore mDatabase;
    private View fertilizers;
    public FertilizersFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
      fertilizers = inflater.inflate(R.layout.fragment_fertilizers, container, false);
        mDatabase = FirebaseFirestore.getInstance();



        return fertilizers;
    }

}
