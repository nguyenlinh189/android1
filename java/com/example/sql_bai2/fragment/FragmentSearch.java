package com.example.sql_bai2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_bai2.R;
import com.example.sql_bai2.adapter.RecycleViewAdapter;
import com.example.sql_bai2.dal.SQLHelper;
import com.example.sql_bai2.model.BaiHat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView rview;
    private TextView tvthongke;
    private Button btsearch;
    private Spinner spalbum;
    private RecycleViewAdapter adapter;
    private SQLHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        adapter=new RecycleViewAdapter();
        db=new SQLHelper(getContext());

        // load du lieu len
        List<BaiHat>list=db.getbaiTop();
        adapter.setList(list);
        tvthongke.setText(db.thongke());

        // set cho rview
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rview.setLayoutManager(manager);
        rview.setAdapter(adapter);


        btsearch.setOnClickListener(this);

        //search theo category(spinner)
        spalbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String album=spalbum.getItemAtPosition(position).toString();
                List<BaiHat>list=new ArrayList<>();
                if (album.equals("All"))
                    list=db.getbaiTop();
                else
                    list=db.searchByAlbum(album);
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initview(View view) {
        rview=view.findViewById(R.id.rview);
        tvthongke=view.findViewById(R.id.tvthongke);
        btsearch=view.findViewById(R.id.btnsearch);
        spalbum=view.findViewById(R.id.spalbum);
        String[]arr=getResources().getStringArray(R.array.album);
        String[]arr1=new String[arr.length+1];
        // cho all len dau
        arr1[0]="All";
        for(int i=1;i<arr1.length;i++)
            arr1[i]=arr[i-1];
        spalbum.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));

    }

    @Override
    public void onClick(View view) {
    }
}
