package com.example.sha.agro;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;

import javax.annotation.Nullable;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FertilizersFragment extends Fragment {
    private static final String TAG = "fertilizer";
    private FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
    private RecyclerView mMainlist;
    private View fertilizers;
    private FertilizerAdaptor fertilizerAdaptor;
    private List<FertilizersView> fertilizerViewList;
    private Bundle bundle;
    private String My_Lang= "ta";

    public FertilizersFragment() {
        // Required empty public constructor

    }
  //  @Override
  //  public void onCreate (Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
   //     My_Lang = (String) bundle.getSerializable("My_Lang");
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        fertilizers = inflater.inflate(R.layout.fragment_fertilizers, container, false);

        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");


        fertilizerViewList = new ArrayList<>();
        fertilizerAdaptor = new FertilizerAdaptor(fertilizerViewList);
        mMainlist = fertilizers.findViewById(R.id.recycler);
        mMainlist.setHasFixedSize(true);
        mMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mMainlist.setAdapter(fertilizerAdaptor);

if(My_Lang == "en") {


    mDatabase.collection("Fertilizer").addSnapshotListener(new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                Log.d(TAG, getString(R.string.error) + e.getMessage());
            }


            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                if (doc.getType() == DocumentChange.Type.ADDED) {

                    FertilizersView name = doc.getDocument().toObject(FertilizersView.class);
                    fertilizerViewList.add(name);

                    fertilizerAdaptor.notifyDataSetChanged();
                }
            }

        }
    });
}
else if(My_Lang=="ta")
{
    mDatabase.collection("Shops/pesticide shop/pesticide shop").addSnapshotListener(new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                Log.d(TAG, getString(R.string.error) + e.getMessage());
            }


            for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                if (doc.getType() == DocumentChange.Type.ADDED) {

                    FertilizersView name = doc.getDocument().toObject(FertilizersView.class);
                    fertilizerViewList.add(name);

                    fertilizerAdaptor.notifyDataSetChanged();
                }
            }

        }
    });
}

        return fertilizers;
    }


}
