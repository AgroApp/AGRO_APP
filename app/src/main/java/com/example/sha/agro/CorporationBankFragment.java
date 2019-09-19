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
public class CorporationBankFragment extends Fragment {
    private static final String TAG = "CorporationBank";
    private FirebaseFirestore coDatabase = FirebaseFirestore.getInstance();
    private RecyclerView coMainlist;
    private View corporationbank;
    private CorporationbankAdaptor corporationbankAdaptor;
    private List<CorporationbankView> corporationbankViewList;
    private String My_Lang= "ta";
    private Bundle bundle;

    public CorporationBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        corporationbank = inflater.inflate(R.layout.fragment_corporation_bank, container, false);

        corporationbankViewList = new ArrayList<>();
        corporationbankAdaptor = new CorporationbankAdaptor(corporationbankViewList);
        coMainlist = corporationbank.findViewById(R.id.corporation_recycler);
        coMainlist.setHasFixedSize(true);
        coMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        coMainlist.setAdapter(corporationbankAdaptor);
        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");

        coDatabase.collection("Indian Bank").whereEqualTo("My_Lang",My_Lang).addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        CorporationbankView name = doc.getDocument().toObject(CorporationbankView.class);
                        corporationbankViewList.add(name);

                        corporationbankAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });




        return  corporationbank;
    }

}
