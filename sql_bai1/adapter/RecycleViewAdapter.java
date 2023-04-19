package com.example.sql_bai1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sql_bai1.R;
import com.example.sql_bai1.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<CongViec>list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public List<CongViec> getList() {
        return list;
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public CongViec getItem(int position){
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
        CongViec cv=list.get(position);
        holder.ten.setText(cv.getTen());
        holder.noidung.setText(cv.getNoidung());
        holder.ngay.setText(cv.getNgay());
        holder.tinhtrang.setText(cv.getTinhtrang());

        if(cv.isCongtac())
            holder.congtac.setText("1 minh");
        else
            holder.congtac.setText("lam chung");
    }
    // tra ve so phan tu
    @Override
    public int getItemCount() {
        return list.size();
    }

    // tao adapter cho view holder
    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten,noidung,ngay,tinhtrang,congtac;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            // khai bao thuoc tinh trong recycle view
            ten=view.findViewById(R.id.tvten);
            noidung=view.findViewById(R.id.tvnoidung);
            ngay=view.findViewById(R.id.tvngay);
            tinhtrang=view.findViewById(R.id.tvtinhtrang);
            congtac=view.findViewById(R.id.tvcongtac);
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
