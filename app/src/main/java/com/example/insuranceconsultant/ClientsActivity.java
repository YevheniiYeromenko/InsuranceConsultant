package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ClientsActivity extends BaseActivity {

    private AdapterClientsRV adapterClientsRV;
    private RecyclerView rvItemClients;
    private ImageView imAddNewClient;
    private AdapterFireStoreClients adapterFireStoreClients;
    private List<ClientInfo> clientInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        ImageView imAddNewClient = findViewById(R.id.imAddNewClient);

//        clientInfoList.add(new ClientInfo("111","Jeremi Jackson","82882","121","sd21","121214"));
//        clientInfoList.add(new ClientInfo("111","Jeremi Johnson","82882","121","sd21","121214"));

        readContact();
        adapterClientsRV = new AdapterClientsRV();
        rvItemClients = findViewById(R.id.rvItemClients);
        rvItemClients.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //rvItemClients.setAdapter(adapterClientsRV);
        //adapterClientsRV.setList(clientInfoList, getApplicationContext());




        imAddNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddNewClientActivity.class);
                startActivity(intent);
            }
        });

    }

    private void readContact(){
        CollectionReference reference = db.collection("Clients");
        reference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("ERROR", error.getMessage());
                    return;
                }
                if (value != null) {
                    clientInfoList.clear();
                    for (DocumentSnapshot document:
                            value.getDocuments()) {
                        clientInfoList.add(document.toObject(ClientInfo.class));
                    }
                    rvItemClients.setAdapter(adapterClientsRV);
                    adapterClientsRV.setList(clientInfoList, getApplicationContext());
                }
            }
        });
        /*db.collection("Clients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:
                                 task.getResult()) {
                                clientInfoList.add(document.toObject(ClientInfo.class));
                            }
                            rvItemClients.setAdapter(adapterClientsRV);
                            adapterClientsRV.setList(clientInfoList, getApplicationContext());
                        }
                    }
                });*/
    }
}