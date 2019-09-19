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
public class CanaraBanFragment extends Fragment {

    private static final String TAG = "canarabank";
    private FirebaseFirestore canDatabase = FirebaseFirestore.getInstance();
    private RecyclerView canMainlist;
    private View canara;
    private CanarabankAdaptor canarabankAdaptor;
    private List<CanarabankView> canarabankViewList;
    private String My_Lang= "ta";
    private Bundle bundle;
    public CanaraBanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        canara= inflater.inflate(R.layout.fragment_canara_ban, container, false);

        canarabankViewList = new ArrayList<>();
        canarabankAdaptor = new CanarabankAdaptor(canarabankViewList);
        canMainlist = canara.findViewById(R.id.canara_recycler);
        canMainlist.setHasFixedSize(true);
        canMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        canMainlist.setAdapter(canarabankAdaptor);
        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");


        canDatabase.collection("Canara Bank").whereEqualTo("My_Lang",My_Lang).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        CanarabankView name = doc.getDocument().toObject(CanarabankView.class);
                        canarabankViewList.add(name);

                        canarabankAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });


        return canara;
    }

}
