package com.example.nguyenconghuong_kiemtra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderViewHolder> {

    private List<Khoahoc> list;
    private Context context;

    public RecyclerViewAdapter() { list = new ArrayList<>(); }

    public void setOrders(List<Khoahoc> list){ this.list=list; }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.khoahoc_item,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Khoahoc s=list.get(position);

        holder.txtIdName.setText(s.getName() + " - " + s.getId());
        holder.txtDate.setText("Date: " + s.getDate());
        holder.txtMajor.setText("Major: " + s.getMajor());
        holder.txtActive.setText("Active: " + (s.getActive() == 1 ? "Yes" : "No"));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent intent = new Intent(context , UpdateActivity.class);
                intent.putExtra("khoahoc", s);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView txtIdName, txtDate, txtMajor, txtActive;
        private CardView cardView;
        public OrderViewHolder(@NonNull View v) {
            super(v);
            txtIdName = v.findViewById(R.id.idName);
            txtMajor=v.findViewById(R.id.major);
            txtDate=v.findViewById(R.id.date);
            txtActive = v.findViewById(R.id.active);
            cardView = v.findViewById(R.id.cardView);
        }
    }
}
