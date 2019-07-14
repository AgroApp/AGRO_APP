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
public class PharmacyFragment extends Fragment {

    private static final String TAG = "Pharmacy";
    private FirebaseFirestore pharDatabase = FirebaseFirestore.getInstance();
    private RecyclerView pharMainlist;
    private View pharmacy;
    private PharmacyAdaptor pharmacyAdaptor;
    private List<PharmacyView> pharmacyViewList;

    public PharmacyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pharmacy = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        pharmacyViewList = new ArrayList<>();
        pharmacyAdaptor = new PharmacyAdaptor(pharmacyViewList);
        pharMainlist = pharmacy.findViewById(R.id.phar_recycler);
        pharMainlist.setHasFixedSize(true);
        pharMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        pharMainlist.setAdapter(pharmacyAdaptor);

        pharDatabase.collection("pharmacy/pharmacy/pharmacy").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        PharmacyView name = doc.getDocument().toObject(PharmacyView.class);
                        pharmacyViewList.add(name);

                        pharmacyAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });
        return pharmacy;
    }

}
