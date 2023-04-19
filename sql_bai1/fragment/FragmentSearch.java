package com.example.sql_bai1.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.example.sql_bai1.R;
import com.example.sql_bai1.UpdateDeleteActivity;
import com.example.sql_bai1.adapter.RecycleViewAdapter;
import com.example.sql_bai1.dal.SQLHelper;
import com.example.sql_bai1.model.CongViec;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener ,RecycleViewAdapter.ItemListener{
    private RecyclerView rview;
    private TextView tvthongke;
    private Button btsearch;
    private SearchView searchView;
    private EditText engay;
    private Spinner sptinhtrang;
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
        List<CongViec> list=db.getAll();
        adapter.setList(list);
        tvthongke.setText(db.thongke());

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
                List<CongViec>list=db.searchByTenOrNoiDung(key);
                adapter.setList(list);
                return true;
            }
        });
        engay.setOnClickListener(this);
        btsearch.setOnClickListener(this);

        //search theo tinhtrang(spinner)
        sptinhtrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String tinhtrang=sptinhtrang.getItemAtPosition(position).toString();
                List<CongViec>list=new ArrayList<>();
                if (tinhtrang.equals("All"))
                    list=db.getAll();
                else
                    list=db.searchByTinhTrang(tinhtrang);
                adapter.setList(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter.setItemListener(this);
    }

    private void initview(View view) {
        rview=view.findViewById(R.id.rview);
        btsearch=view.findViewById(R.id.btnsearch);
        searchView=view.findViewById(R.id.search);
        tvthongke=view.findViewById(R.id.tvthongke);
        engay=view.findViewById(R.id.engay);
        sptinhtrang=view.findViewById(R.id.sptinhtrang);
        String[]arr=getResources().getStringArray(R.array.tinhtrang);
        String[]arr1=new String[arr.length+1];
        // cho all len dau
        arr1[0]="All";
        for(int i=1;i<arr1.length;i++)
            arr1[i]=arr[i-1];
        sptinhtrang.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));

    }

    @Override
    public void onClick(View view) {
        if(view==engay) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m > 8) {
                        if (d < 10)
                            date = "0" + d + "/" + (m + 1) + "/" + y;
                        else
                            date = d + "/" + (m + 1) + "/" + y;
                    } else {
                        if (d < 10)
                            date = "0" + d + "/" + "0" + (m + 1) + "/" + y;
                        else
                            date = d + "/" + "0" + (m + 1) + "/" + y;
                    }
                    engay.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if(view==btsearch){
            String ngay=engay.getText().toString();
            List<CongViec>list=new ArrayList<>();
            if(!ngay.isEmpty())
            {
                list=db.searchByNgay(ngay);
                adapter.setList(list);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec>list=db.getAll();
        adapter.setList(list);
        tvthongke.setText(db.thongke());
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent t=new Intent(getContext(), UpdateDeleteActivity.class);
        t.putExtra("cv",adapter.getItem(position));
        startActivity(t);
    }
}
