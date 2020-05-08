package com.example.sha.agro;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

    private String num = "8778155739";
    private static final String TAG = "fertilizer";
    private FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
    private RecyclerView mMainlist;
    private View fertilizers;
    private FertilizerAdaptor fertilizerAdaptor;
    private List<FertilizersView> fertilizerViewList;
    private Bundle bundle;
    private String My_Lang= "en";

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
        final View.OnClickListener call = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder a_builder = new AlertDialog.Builder(getContext());
                a_builder.setCancelable(false);
                a_builder.setMessage(getString(R.string.want_to_call));
                a_builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" +num));

                        startActivity(callIntent);
                    }
                });


                a_builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !");
                alert.show();



            }
        };
       bundle = getArguments();
       My_Lang = (String) bundle.getSerializable("My_Lang");


        fertilizerViewList = new ArrayList<>();
        fertilizerAdaptor = new FertilizerAdaptor(fertilizerViewList, call);
        mMainlist = fertilizers.findViewById(R.id.recycler);
        mMainlist.setHasFixedSize(true);
        mMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mMainlist.setAdapter(fertilizerAdaptor);


if(My_Lang ="1"){
    mDatabase.collection("Fertilizer").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
