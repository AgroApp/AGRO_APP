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
public class IndianBankFragment extends Fragment {

    private static final String TAG = "indianBank";
    private FirebaseFirestore inDatabase = FirebaseFirestore.getInstance();
    private RecyclerView inMainlist;
    private View indianbank;
    private IndianbankAdaptor indianbankAdaptor;
    private List<IndianbankView> indianbankViewList;
    private String My_Lang= "ta";
    private Bundle bundle;


    public IndianBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        indianbank= inflater.inflate(R.layout.fragment_indian_bank, container, false);

        indianbankViewList = new ArrayList<>();
        indianbankAdaptor = new IndianbankAdaptor(indianbankViewList);
        inMainlist = indianbank.findViewById(R.id.indian_recycler);
        inMainlist.setHasFixedSize(true);
        inMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        inMainlist.setAdapter(indianbankAdaptor);
        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");

        inDatabase.collection("Indian Bank").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        IndianbankView name = doc.getDocument().toObject(IndianbankView.class);
                        indianbankViewList.add(name);

                        indianbankAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });


        return indianbank;
    }

}
