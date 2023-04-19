package com.example.sql_bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sql_bai3.dal.SQLHelper;
import com.example.sql_bai3.model.Sach;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eten,etacgia,engayxb;
    private Spinner spnhaxb;
    private EditText egia;
    private Button btnupdate, btncancel;

    public AddActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        engayxb.setOnClickListener(this);
    }

    private void initView() {
        eten=findViewById(R.id.eten);
        etacgia=findViewById(R.id.etacgia);
        engayxb=findViewById(R.id.engayxb);
        spnhaxb=findViewById(R.id.spnhasx);
        btnupdate=findViewById(R.id.btnupdate);
        btncancel=findViewById(R.id.btncancel);
        egia=findViewById(R.id.egia);
        spnhaxb.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.nhasx)));
    }

    @Override
    public void onClick(View view) {
        if(view==engayxb){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>8){
                        if(d<10)
                            date="0"+d+"/"+(m+1)+"/"+y;
                        else
                            date=d+"/"+(m+1)+"/"+y;
                    }else{
                        if(d<10)
                            date="0"+d+"/"+"0"+(m+1)+"/"+y;
                        else
                            date=d+"/"+"0"+(m+1)+"/"+y;
                    }
                    engayxb.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if (view==btncancel)
            finish();
        if(view==btnupdate){
            String ten=eten.getText().toString();
            String nhaxb=spnhaxb.getSelectedItem().toString();
            String ngayxb=engayxb.getText().toString();
            String tacgia=etacgia.getText().toString();
            String gias=egia.getText().toString();
            System.out.println(ten+" "+nhaxb+" "+tacgia+" "+gias);

            try{
                float gia=Float.parseFloat(egia.getText().toString());
                if(!ten.isEmpty() && !tacgia.isEmpty()){
                    System.out.println(gia);
                    Sach i=new Sach(ten,tacgia,ngayxb,nhaxb,gia);
                    SQLHelper db=new SQLHelper(this);
                    System.out.println(db.addItem(i));
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"phai day du",Toast.LENGTH_SHORT).show();
            }catch(NumberFormatException e){
                Toast.makeText(getApplicationContext(),"phai nhap so",Toast.LENGTH_SHORT).show();
            }

        }
    }
}