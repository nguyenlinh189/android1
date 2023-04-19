package com.example.sql_bai1;

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
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sql_bai1.dal.SQLHelper;
import com.example.sql_bai1.model.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eten,enoidung,engay;
    private Spinner sptinhtrang;
    private RadioButton g1,g2;
    private Button btnupdate, btncancel, btndelete;
    private CongViec cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        Intent t=getIntent();
        cv=(CongViec) t.getSerializableExtra("cv");
        eten.setText(cv.getTen());
        engay.setText(cv.getNgay());
        enoidung.setText(cv.getNoidung());
        int p=0;
        for(int i=0;i<sptinhtrang.getCount();i++)
            if(sptinhtrang.getItemAtPosition(i).toString().equals(cv.getTinhtrang()))
            {
                p=i;
                break;
            }
        sptinhtrang.setSelection(p);
        if(cv.isCongtac())
        {
            g1.setChecked(true);
            g2.setChecked(false);
        }
        else
        {
            g2.setChecked(true);
            g1.setChecked(false);
        }
        engay.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        btnupdate.setOnClickListener(this);
        btndelete.setOnClickListener(this);
    }

    private void initView() {
        eten=findViewById(R.id.eten);
        enoidung=findViewById(R.id.enoidung);
        engay=findViewById(R.id.engay);
        sptinhtrang=findViewById(R.id.sptinhtrang);
        g1=findViewById(R.id.g1);
        g2=findViewById(R.id.g2);
        btnupdate=findViewById(R.id.btnupdate);
        btncancel=findViewById(R.id.btnback);
        btndelete=findViewById(R.id.btndelete);
        sptinhtrang.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.tinhtrang)));
    }

    @Override
    public void onClick(View view) {
        if(view==engay){
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
                    engay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if (view==btncancel)
            finish();
        if(view==btndelete){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa"+cv.getTen()+" khong?");
            builder.setIcon(R.drawable.icon_remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLHelper db=new SQLHelper(getApplicationContext());
                    db.delete(cv.getId());
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
        if(view==btnupdate){
            int id=cv.getId();
            String ten=eten.getText().toString();
            String noidung=enoidung.getText().toString();
            String ngay=engay.getText().toString();
            String tinhtrang=sptinhtrang.getSelectedItem().toString();
            boolean congtac=false;
            if(g1.isChecked())
                congtac=false;
            else
                congtac=true;
            CongViec cv=new CongViec(id,ten,noidung,ngay,tinhtrang,congtac);
            if(!ten.isEmpty() && !noidung.isEmpty()){
                SQLHelper sq=new SQLHelper(this);
                sq.update(cv);
                finish();
            }
        }
    }
}