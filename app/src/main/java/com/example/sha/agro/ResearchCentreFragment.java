package com.example.sha.agro;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private String num = "8778155739";
    private static final String TAG = "Research centre";
    private FirebaseFirestore resDatabase = FirebaseFirestore.getInstance();
    private RecyclerView resMainlist;
    private View Research;
    private Uri mainImageURI= null;
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

        researchCentreViewList = new ArrayList<>();
        researchCentreAdaptor = new ResearchCentreAdaptor(researchCentreViewList, call);
        resMainlist = Research.findViewById(R.id.res_recycler);
        resMainlist.setHasFixedSize(true);
        resMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        resMainlist.setAdapter(researchCentreAdaptor);




        resDatabase.collection("Research Centre").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>(){
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
