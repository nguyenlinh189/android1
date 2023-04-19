package com.example.sql_bai2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sql_bai2.R;
import com.example.sql_bai2.model.BaiHat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<BaiHat>list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public List<BaiHat> getList() {
        return list;
    }

    public void setList(List<BaiHat> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public BaiHat getItem(int position){
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
        BaiHat bh=getItem(position);
        holder.ten.setText(bh.getTen());
        holder.tencasi.setText(bh.getTencasi());
        holder.album.setText(bh.getAlbum());
        holder.theloai.setText(bh.getTheloai());
        System.out.println(bh.isYeuthich());
        if(bh.isYeuthich())
            holder.yeuthich.setText("co yeu thich");
        else
            holder.yeuthich.setText("khong yeu thich");
    }
    // tra ve so phan tu
    @Override
    public int getItemCount() {
        return list.size();
    }

    // tao adapter cho view holder
    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten, tencasi;
        private TextView album,theloai,yeuthich;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            // khai bao thuoc tinh trong recycle view
            ten=view.findViewById(R.id.tvten);
            tencasi=view.findViewById(R.id.tvtencasi);
            album=view.findViewById(R.id.album);
            theloai=view.findViewById(R.id.tvtheloai);
            yeuthich=view.findViewById(R.id.tvyeuthich);
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
