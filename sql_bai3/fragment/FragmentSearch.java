package com.example.sql_bai3.fragment;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_bai3.R;
import com.example.sql_bai3.adapter.RecycleViewAdapter;
import com.example.sql_bai3.dal.SQLHelper;
import com.example.sql_bai3.model.Sach;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView rview;
    private Button btsearch;
    private SearchView searchView;
    private EditText egia1,egia2;
    private Spinner spnhaxb;
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
        List<Sach>list=db.getAll();
        adapter.setList(list);
//        tvtong.setText("Tong tien: "+tong(list)+"K");

        // set cho rview
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rview.setLayoutManager(manager);
        rview.setAdapter(adapter);

        // tim kiem bang seachview

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String key) {
                List<Sach>list=db.earchByName(key);
                adapter.setList(list);
                return true;
            }
        });
        btsearch.setOnClickListener(this);

        //search theo category(spinner)
        spnhaxb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String nhaxb=spnhaxb.getItemAtPosition(position).toString();
                List<Sach>list=new ArrayList<>();
                if (nhaxb.equals("All"))
                    list=db.getAll();
                else
                {
                    Sach s=db.searchBynhaxb(nhaxb);
                    list=new ArrayList<>();
                    if(s!=null)
                    {
                        list.add(s);
                    }
                }
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initview(View view) {
        rview=view.findViewById(R.id.rview);
        btsearch=view.findViewById(R.id.btnsearch);
        searchView=view.findViewById(R.id.search);
        egia1=view.findViewById(R.id.egia1);
        egia2=view.findViewById(R.id.egia2);
        spnhaxb=view.findViewById(R.id.spnhasx);
        String[]arr=getResources().getStringArray(R.array.nhasx);
        String[]arr1=new String[arr.length+1];
        // cho all len dau
        arr1[0]="All";
        for(int i=1;i<arr1.length;i++)
            arr1[i]=arr[i-1];
        spnhaxb.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));

    }

    @Override
    public void onClick(View view) {


        if(view==btsearch){
//            String gia1=egia1.getText().toString();
//            String gia2=egia2.getText().toString();
//            List<Sach>list=new ArrayList<>();
//            try{
//                float g1=Float.parseFloat(gia1);
//                float g2=Float.parseFloat(gia2);
//                list=db.searchByGiafromto(g1,g2);
//                adapter.setList(list);
//            }catch (NumberFormatException e){
//                Toast.makeText(getContext(),"phai nhap so",Toast.LENGTH_SHORT).show();
//            }
            String sx[]=getResources().getStringArray(R.array.nhasx);
            List<Sach>list=new ArrayList<>();
            for(int i=0;i<sx.length;i++) {
                Sach s = db.searchBynhaxb(sx[i]);
                if(s!=null)
                    list.add(s);
            }
            Collections.sort(list, new Comparator<Sach>() {
                @Override
                public int compare(Sach s1, Sach s2) {
                    return Float.compare(s2.getGia(),s1.getGia());
                }
            });
            adapter.setList(list);
        }
    }
}
