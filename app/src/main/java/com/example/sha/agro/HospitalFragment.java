package com.example.sha.agro;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
public class HospitalFragment extends Fragment {

    private String num = "8778155739";
    private static final String TAG = "Hospital";
    private FirebaseFirestore hosDatabase = FirebaseFirestore.getInstance();
    private RecyclerView hosMainlist;
    private View hospital;
    private HospitalAdaptor hospitalAdaptor;
    private List<HospitalView> hospitalViewList;


    public HospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        hospital= inflater.inflate(R.layout.fragment_hospital, container, false);
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
        hospitalViewList = new ArrayList<>();
        hospitalAdaptor = new HospitalAdaptor(hospitalViewList, call);
        hosMainlist = hospital.findViewById(R.id.hospital_recycler);
        hosMainlist.setHasFixedSize(true);
        hosMainlist.setLayoutManager(new LinearLayoutManager(container.getContext()));
        hosMainlist.setAdapter(hospitalAdaptor);

        hosDatabase.collection("hospitals/hospitals/hospital").whereEqualTo("My_Lang","en").addSnapshotListener(new EventListener<QuerySnapshot>(){
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }


                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        HospitalView name = doc.getDocument().toObject(HospitalView.class);
                        hospitalViewList.add(name);

                        hospitalAdaptor.notifyDataSetChanged();
                    }
                }

            }
        });
        return hospital;
    }

}
