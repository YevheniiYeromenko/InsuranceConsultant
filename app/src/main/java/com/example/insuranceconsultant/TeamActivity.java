package com.example.insuranceconsultant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamActivity extends BaseActivity {

    private RecyclerView rvItemConlultants;
    private AdapterConsultantsRV adapterConsultantsRV;
    private List<ConsultantInfo> consultantInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);




        rvItemConlultants = findViewById(R.id.rvItemConsultants);

        adapterConsultantsRV = new AdapterConsultantsRV();
        rvItemConlultants.setLayoutManager(new LinearLayoutManager(this));
        readContact();

    }

    private void readContact() {
        final String sharedLogin = mySharedPreferences.getString("Login", "Fault");

        CollectionReference reference = db.collection("Consultants");
        reference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("ERROR", error.getMessage());
                    return;
                }
                if (value != null) {
                    //consultantInfoList.clear();
                    for (DocumentSnapshot document :
                            value.getDocuments()) {
                        Log.wtf("++++++++++", document.getString("trainerNumber"));
                        Log.wtf("++++++++++", sharedLogin);
                        if (document.getString("trainerNumber").equals(sharedLogin)
                            || document.getString("number").equals(sharedLogin)){
                            document.getId().equals(sharedLogin);
                            consultantInfoList.add(document.toObject(ConsultantInfo.class));
                        }
                        //Log.wtf("FIRE TEAM =====" , consultantInfoList.get(0).toString());
                    }
                    Collections.sort(consultantInfoList, new Comparator<ConsultantInfo>() {
                        @Override
                        public int compare(ConsultantInfo o1, ConsultantInfo o2) {
                            return o1.getNumber().equals(sharedLogin)? -1: 1;
                        }
                    });
                    rvItemConlultants.setAdapter(adapterConsultantsRV);
                    adapterConsultantsRV.setList(consultantInfoList, getApplicationContext());

                }
            }
        });
    }
}