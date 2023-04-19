package com.example.sql_bai2;

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

import com.example.sql_bai2.dal.SQLHelper;
import com.example.sql_bai2.model.BaiHat;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eten,etencasi;
    private Spinner spablum, sptheloai;
    private Button btnupdate, btncancel;
    private RadioButton gco,gkhong;
    public AddActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);
    }

    private void initView() {
        eten=findViewById(R.id.eten);
        etencasi=findViewById(R.id.etencasi);
        spablum=findViewById(R.id.spalbum);
        sptheloai=findViewById(R.id.sptheloai);
        btnupdate=findViewById(R.id.btnupdate);
        btncancel=findViewById(R.id.btncancel);
        gco=findViewById(R.id.gco);
        gkhong=findViewById(R.id.gkhong);
        spablum.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.album)));
        sptheloai.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.theloai)));
    }

    @Override
    public void onClick(View view) {
        if (view==btncancel)
            finish();
        if(view==btnupdate){
            String ten=eten.getText().toString();
            String tencasi=etencasi.getText().toString();
            String ablum=spablum.getSelectedItem().toString();
            String theloai=sptheloai.getSelectedItem().toString();
            boolean yeuthich=true;
            if(gco.isChecked())
                yeuthich=true;
            if(gkhong.isChecked())
                yeuthich=false;
            if(!ten.isEmpty()&&!tencasi.isEmpty()){
                BaiHat i=new BaiHat(ten,tencasi,ablum,theloai,yeuthich);
                SQLHelper db=new SQLHelper(this);
                db.addItem(i);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"phai dien day du thong tin",Toast.LENGTH_SHORT).show();
            }
        }
    }
}