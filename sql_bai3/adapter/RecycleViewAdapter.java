package com.example.sql_bai3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sql_bai3.R;
import com.example.sql_bai3.model.Sach;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Sach>list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public List<Sach> getList() {
        return list;
    }

    public void setList(List<Sach> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Sach getItem(int position){
        return list.get(position);
    }
    // tra ve view
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
//        gan du lieu cho cac o trong rview
        Sach s=getItem(position);
        holder.tvten.setText(s.getTen());
        holder.tvtacgia.setText(s.getTacgia());
        holder.tvngayxb.setText(s.getNgayxb());
        holder.tvnhasx.setText(s.getNhaxb());
        holder.tvgia.setText(s.getGia()+"");
    }
    // tra ve so phan tu
    @Override
    public int getItemCount() {
        return list.size();
    }

    // tao adapter cho view holder
    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvten,tvtacgia,tvngayxb,tvnhasx,tvgia;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            tvten=view.findViewById(R.id.tvten);
            tvtacgia=view.findViewById(R.id.tvtacgia);
            tvngayxb=view.findViewById(R.id.tvngayxb);
            tvnhasx=view.findViewById(R.id.tvnhasx);
            tvgia=view.findViewById(R.id.tvgia);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null)
                itemListener.onItemClick(view,getAdapterPosition());
        }
    }
    // viet interface de click vao mot item
    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}
