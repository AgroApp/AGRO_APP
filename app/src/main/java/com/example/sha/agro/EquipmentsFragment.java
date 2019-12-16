package com.example.sha.agro;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentsFragment extends Fragment {
    private static final String TAG = "Equipments";

    private View equipments;
    private String num = "8778155739";
    private FirebaseFirestore eDatabase = FirebaseFirestore.getInstance();
    private RecyclerView eMainlist;
    private List<EquipmentsView> equipmentsViewList;
    private EquipmentsAdapter listAdapter;
    private String My_Lang= "ta";
    private Bundle bundle;
    public EquipmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        equipments= inflater.inflate(R.layout.fragment_equipments, container, false);
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
        equipmentsViewList = new ArrayList<>();
        eMainlist = equipments.findViewById(R.id.equi_recycler);
        listAdapter = new EquipmentsAdapter(equipmentsViewList, call);
        eMainlist = equipments.findViewById(R.id.equi_recycler);
        eMainlist.setHasFixedSize(true);
        eMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        eMainlist.setAdapter(listAdapter);
        bundle = getArguments();
        My_Lang = (String) bundle.getSerializable("My_Lang");

          eDatabase.collection("Shops/seed shops/seed shops").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
              public void onEvent( QuerySnapshot queryDocumentSnapshots,  FirebaseFirestoreException e) {
               if (e != null) {
                  Log.d(TAG, "Error : " + e.getMessage());
              }

               for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                   if (doc.getType() == DocumentChange.Type.ADDED) {

                       EquipmentsView name = doc.getDocument().toObject(EquipmentsView.class);
                       equipmentsViewList.add(name);


                       listAdapter.notifyDataSetChanged();
                   }
               }

             }
          });
        return equipments;
    }

}
