package com.example.sha.agro;


import android.os.Bundle;
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

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResearchCentreFragment extends Fragment {

    private static final String TAG = "Research centre";
    private FirebaseFirestore resDatabase = FirebaseFirestore.getInstance();
    private RecyclerView resMainlist;
    private View Research;
    private ResearchCentreAdaptor researchCentreAdaptor;
    private List<ResearchCentreView> researchCentreViewList;

    public ResearchCentreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Research= inflater.inflate(R.layout.fragment_research_centre, container, false);

        researchCentreViewList = new ArrayList<>();
        researchCentreAdaptor = new ResearchCentreAdaptor(researchCentreViewList);
        resMainlist = Research.findViewById(R.id.res_recycler);
        resMainlist.setHasFixedSize(true);
        resMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        resMainlist.setAdapter(researchCentreAdaptor);

        resDatabase.collection("Research Centre").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        ResearchCentreView name = doc.getDocument().toObject(ResearchCentreView.class);
                        researchCentreViewList.add(name);

                        researchCentreAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });

        return Research;
    }

}
