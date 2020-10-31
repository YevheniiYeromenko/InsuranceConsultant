package com.example.insuranceconsultant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterConsultantsRV extends RecyclerView.Adapter<AdapterConsultantsRV.ConsultantsInfoHolder> {

    private Context context;
    private List<ConsultantInfo> consultantInfoList;

    public void setList(List<ConsultantInfo> consultantInfoList, Context context){
        this.consultantInfoList = consultantInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultantsInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_consultant, parent, false);
        return new ConsultantsInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultantsInfoHolder holder, int position) {
        holder.tvConsultantName.setText(consultantInfoList.get(position).getName());
        holder.tvConsultantLevel.setText("Рівень: " + String.valueOf(consultantInfoList.get(position).getLevel()));
    }

    @Override
    public int getItemCount() {
        return consultantInfoList.size();
    }

    public class ConsultantsInfoHolder extends RecyclerView.ViewHolder {
        TextView tvConsultantName;
        TextView tvConsultantLevel;

        public ConsultantsInfoHolder(@NonNull View itemView) {
            super(itemView);
            tvConsultantName = itemView.findViewById(R.id.tvConsultantName);
            tvConsultantLevel = itemView.findViewById(R.id.tvConsultantLevel);
        }
    }
}
