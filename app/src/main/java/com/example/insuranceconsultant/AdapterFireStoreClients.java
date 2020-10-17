package com.example.insuranceconsultant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterFireStoreClients extends FirestoreRecyclerAdapter<ClientInfo, AdapterFireStoreClients.ClientsHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public AdapterFireStoreClients(@NonNull FirestoreRecyclerOptions<ClientInfo> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ClientsHolder holder, int position, @NonNull ClientInfo model) {
        holder.tvClinetName.setText(model.getName());
        Glide.with(context)
                .load("https://media.licdn.com/dms/image/C4E0BAQGvfZGd31Hw_Q/company-logo_200_200/0?e=2159024400&v=beta&t=zXUgvlTYqfO4cCIr5kggBOcypfhK4i4pbMtzq6dbeP8")
                .into(holder.imLifeInsCompany);
    }

    @NonNull
    @Override
    public ClientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class ClientsHolder extends RecyclerView.ViewHolder{

        ImageView imLifeInsCompany;
        TextView tvClinetName;
        public ClientsHolder(@NonNull View itemView) {
            super(itemView);
            tvClinetName = itemView.findViewById(R.id.tvClinetName);
            imLifeInsCompany = itemView.findViewById(R.id.imLifeInsCompany);
        }
    }
}
