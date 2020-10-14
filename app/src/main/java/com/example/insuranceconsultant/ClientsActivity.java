package com.example.insuranceconsultant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ClientsActivity extends AppCompatActivity {

    private AdapterClientsRV adapterClientsRV;
    private RecyclerView rvItemClients;
    private ImageView imAddNewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        ImageView imAddNewClient = findViewById(R.id.imAddNewClient);

        List<ClientInfo> clientInfoList = new ArrayList<>();
        clientInfoList.add(new ClientInfo("111","Jeremi Jackson","82882","121","sd21","121214"));
        clientInfoList.add(new ClientInfo("111","Jeremi Johnson","82882","121","sd21","121214"));

        adapterClientsRV = new AdapterClientsRV();
        rvItemClients = findViewById(R.id.rvItemClients);
        rvItemClients.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvItemClients.setAdapter(adapterClientsRV);
        adapterClientsRV.setList(clientInfoList, getApplicationContext());

        imAddNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(),)
            }
        });

    }
}