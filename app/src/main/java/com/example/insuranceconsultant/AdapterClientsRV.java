package com.example.insuranceconsultant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterClientsRV extends RecyclerView.Adapter<AdapterClientsRV.ClientInfoHolder> {
    private List<ClientInfo> list;
    private Context context;


    public void setList(List<ClientInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ClientInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_client, parent, false);
        return new ClientInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientInfoHolder holder, int position) {
        holder.tvClinetName.setText(list.get(position).getName());
        Glide.with(context)
                .load("https://media.licdn.com/dms/image/C4E0BAQGvfZGd31Hw_Q/company-logo_200_200/0?e=2159024400&v=beta&t=zXUgvlTYqfO4cCIr5kggBOcypfhK4i4pbMtzq6dbeP8")
                .into(holder.imLifeInsCompany);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientInfoHolder extends RecyclerView.ViewHolder {
        ImageView imLifeInsCompany;
        TextView tvClinetName;

        public ClientInfoHolder(@NonNull View itemView) {
            super(itemView);
            imLifeInsCompany = itemView.findViewById(R.id.imLifeInsCompany);
            tvClinetName = itemView.findViewById(R.id.tvClinetName);
        }
    }
}
