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
public class PesticideFragment extends Fragment {

    private static final String TAG = "pesticide";
    private FirebaseFirestore pDatabase = FirebaseFirestore.getInstance();
    private RecyclerView pMainlist;
    private View pesticide;
    private PesticideAdaptor pesticideAdaptor;
    private List<PesticideView> pesticideViewList;
    private Bundle bundle;
    private String My_Lang= "en";

    public PesticideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        pesticide= inflater.inflate(R.layout.fragment_pesticide, container, false);

        pesticideViewList = new ArrayList<>();
        pesticideAdaptor = new PesticideAdaptor(pesticideViewList);
        pMainlist = pesticide.findViewById(R.id.pesti_recycler);
        pMainlist.setHasFixedSize(true);
        pMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        pMainlist.setAdapter(pesticideAdaptor);


        pDatabase.collection("Shops/pesticide shop/pesticide shop").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        PesticideView name = doc.getDocument().toObject(PesticideView.class);
                        pesticideViewList.add(name);

                      pesticideAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });




        return pesticide;
    }

}
