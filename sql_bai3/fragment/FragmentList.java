package com.example.sql_bai3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_bai3.R;
import com.example.sql_bai3.UpdateDeleteActivity;
import com.example.sql_bai3.adapter.RecycleViewAdapter;
import com.example.sql_bai3.dal.SQLHelper;
import com.example.sql_bai3.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rview);
        adapter=new RecycleViewAdapter();
        db=new SQLHelper(getContext());
        List<Sach>listdb=db.getAll();
        adapter.setList(listdb);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }
    // khi click chuot vao 1 item thi no se nhay sang 1 activity sua
    @Override
    public void onItemClick(View view, int position) {
        Sach s=adapter.getItem(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("s",s);
        startActivity(intent);
    }
    // moi lan them thi se phai lam tuoi lai du lieu tai trang nay nen viet onresume()

    // lam moi du lieu
    @Override
    public void onResume() {
        super.onResume();
        // gan du lieu lai
        List<Sach>list=db.getAll();
        adapter.setList(list);
    }
}
