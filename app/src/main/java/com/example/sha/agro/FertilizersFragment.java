package com.example.sha.agro;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FertilizersFragment extends Fragment {

    private FirebaseFirestore mDatabase;
    private RecyclerView mMainlist;
    private View fertilizers;
    private FertilizerFragmentAdapter listAdapter;
    private List<FertilizersView> fertilizerViewList;



    public FertilizersFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
      fertilizers = inflater.inflate(R.layout.fragment_fertilizers, container, false);
        fertilizerViewList = new ArrayList<>();
        listAdapter = new FertilizerFragmentAdapter(fertilizerViewList);
        mMainlist= (RecyclerView) fertilizers.findViewById(R.id.recycler);
        mMainlist.setHasFixedSize(true);
        mMainlist.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainlist.setAdapter(listAdapter);
        mDatabase = FirebaseFirestore.getInstance();

      mDatabase.collection("Schemes").addSnapshotListener(new EventListener<QuerySnapshot>() {


    @Override
    public void onEvent( QuerySnapshot queryDocumentSnapshots,  FirebaseFirestoreException e) {
        if(e != null){
            Log.d(TAG,"Error : " + e.getMessage());
        }

        for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
            if(doc.getType() == DocumentChange.Type.ADDED){
                FertilizersView fertilizerView =doc.getDocument().toObject(FertilizersView.class) ;
                fertilizerViewList.add(fertilizerView);

                listAdapter.notifyDataSetChanged();
            }
        }
    }
});

        return fertilizers;
    }

}
