package com.example.sql_bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sql_bai3.dal.SQLHelper;
import com.example.sql_bai3.model.Sach;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eten,etacgia,engayxb;
    private Spinner spnhaxb;
    private EditText egia;
    private Button btnupdate, btncancel,btndelete;
    private Sach s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initview();
        engayxb.setOnClickListener(this);
        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        Intent intent=getIntent();
        // them du lieu vao trang
        s=(Sach) intent.getSerializableExtra("s");
        eten.setText(s.getTen());
        etacgia.setText(s.getTacgia());
        engayxb.setText(s.getNgayxb());
        egia.setText(s.getGia()+"");
        int p=0;
        for (int i=0;i<spnhaxb.getCount();i++)
            if(spnhaxb.getItemAtPosition(i).toString().equals(s.getNhaxb()))
            {
                p=i;
                break;
            }
        spnhaxb.setSelection(p);
    }

    private void initview() {
        eten=findViewById(R.id.eten);
        etacgia=findViewById(R.id.etacgia);
        engayxb=findViewById(R.id.engayxb);
        spnhaxb=findViewById(R.id.spnhasx);
        btnupdate=findViewById(R.id.btnupdate);
        btncancel=findViewById(R.id.btnback);
        btndelete=findViewById(R.id.btndelete);
        egia=findViewById(R.id.egia);
        spnhaxb.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.nhasx)));}

    @Override
    public void onClick(View view) {
        if(view==engayxb){
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            // nut update
            int id=s.getId();
            String ten=eten.getText().toString();
            String nhaxb=spnhaxb.getSelectedItem().toString();
            String ngayxb=engayxb.getText().toString();
            String tacgia=etacgia.getText().toString();
            String gias=egia.getText().toString();

            try{
                float gia=Float.parseFloat(egia.getText().toString());
                if(!ten.isEmpty() && !tacgia.isEmpty()){
                    Sach i=new Sach(id,ten,tacgia,ngayxb,nhaxb,gia);
                    SQLHelper db=new SQLHelper(this);
                    db.update(i);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"phai day du",Toast.LENGTH_SHORT).show();
            }catch(NumberFormatException e){
                Toast.makeText(getApplicationContext(),"phai nhap so",Toast.LENGTH_SHORT).show();
            }
        }
        if(view==btndelete){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            // thong bao xoa
            builder.setMessage("Ban co chac muon xoa"+s.getTen()+" khong?");
            builder.setIcon(R.drawable.icon_remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLHelper db=new SQLHelper(getApplicationContext());
                    db.delete(s.getId());
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }
}