package com.example.insuranceconsultant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends BaseActivity {

    private RecyclerView rvItemConlultants;
    private ImageView imAddNewConcultant;
    private AdapterConsultantsRV adapterConsultantsRV;
    private List<ConsultantInfo> consultantInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);



        rvItemConlultants = findViewById(R.id.rvItemConsultants);
        imAddNewConcultant = findViewById(R.id.imAddNewConsultant);

        adapterConsultantsRV = new AdapterConsultantsRV();
        rvItemConlultants.setLayoutManager(new LinearLayoutManager(this));
        readContact();

    }

    private void readContact() {
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
                        consultantInfoList.add(document.toObject(ConsultantInfo.class));
                        Log.wtf("FIRE TEAM =====" , consultantInfoList.get(0).toString());
                    }
                    rvItemConlultants.setAdapter(adapterConsultantsRV);
                    adapterConsultantsRV.setList(consultantInfoList, getApplicationContext());
                }
            }
        });
    }
}