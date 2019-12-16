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
public class VeterinaryHospitalFragment extends Fragment {

    private static final String TAG = "Veterinary Hospital";
    private FirebaseFirestore vethosDatabase = FirebaseFirestore.getInstance();
    private RecyclerView vethosMainlist;
    private View vethospital;
    private VeterinaryHospitalAdaptor veterinaryHospitalAdaptor;
    private List<VeterinaryHospitalView> veterinaryHospitalViewList;

    public VeterinaryHospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vethospital= inflater.inflate(R.layout.fragment_veterinary_hospital, container, false);

        veterinaryHospitalViewList = new ArrayList<>();
        veterinaryHospitalAdaptor = new VeterinaryHospitalAdaptor(veterinaryHospitalViewList);
        vethosMainlist = vethospital.findViewById(R.id.vet_hos_recycler);
        vethosMainlist.setHasFixedSize(true);
        vethosMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        vethosMainlist.setAdapter(veterinaryHospitalAdaptor);

        vethosDatabase.collection("hospitals/veterinary hospital/veterinary hospital").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        VeterinaryHospitalView name = doc.getDocument().toObject(VeterinaryHospitalView.class);
                        veterinaryHospitalViewList.add(name);

                        veterinaryHospitalAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });
        return vethospital;
    }

}
