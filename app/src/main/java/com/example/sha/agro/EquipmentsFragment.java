package com.example.sha.agro;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentsFragment extends Fragment {
    private static final String TAG = "Equipments";

    private View equipments;

    private FirebaseFirestore eDatabase = FirebaseFirestore.getInstance();
    private RecyclerView eMainlist;
    private List<EquipmentsView> equipmentsViewList;
    private EquipmentsAdapter listAdapter;


    public EquipmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        equipments= inflater.inflate(R.layout.fragment_equipments, container, false);
        equipmentsViewList = new ArrayList<>();
        eMainlist = equipments.findViewById(R.id.equi_recycler);
        listAdapter = new EquipmentsAdapter(equipmentsViewList);
        eMainlist = equipments.findViewById(R.id.equi_recycler);
        eMainlist.setHasFixedSize(true);
        eMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        eMainlist.setAdapter(listAdapter);

          eDatabase.collection("Schemes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
              public void onEvent( QuerySnapshot queryDocumentSnapshots,  FirebaseFirestoreException e) {
               if (e != null) {
                  Log.d(TAG, "Error : " + e.getMessage());
              }

               for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                   if (doc.getType() == DocumentChange.Type.ADDED) {

                       EquipmentsView name = doc.getDocument().toObject(EquipmentsView.class);
                       equipmentsViewList.add(name);


                       listAdapter.notifyDataSetChanged();
                   }
               }

             }
          });
        return equipments;
    }

}
