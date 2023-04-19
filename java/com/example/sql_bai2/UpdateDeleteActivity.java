package com.example.sql_bai2;

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
import android.widget.Toast;

import com.example.sql_bai2.dal.SQLHelper;
import com.example.sql_bai2.model.BaiHat;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eten,etencasi;
    private Spinner spablum, sptheloai;
    private Button btnupdate, btncancel,btndelete;
    private RadioButton gco,gkhong;
    private BaiHat bh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initview();
        btnupdate.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        Intent intent=getIntent();
        // them du lieu vao trang
        bh=(BaiHat)intent.getSerializableExtra("bh");
        eten.setText(bh.getTen());
        etencasi.setText(bh.getTencasi());
        int p=0;
        for(int i=0;i<spablum.getCount();i++)
            if(spablum.getItemAtPosition(i).toString().equalsIgnoreCase(bh.getAlbum()))
            {
                p=i;
                break;
            }
        spablum.setSelection(p);
        p=0;
        for(int i=0;i<sptheloai.getCount();i++)
            if(sptheloai.getItemAtPosition(i).toString().equalsIgnoreCase(bh.getTheloai()))
            {
                p=i;
                break;
            }
        sptheloai.setSelection(p);
        if(bh.isYeuthich())
        {
            gco.setChecked(true);
            gkhong.setChecked(false);
        }else{
            gco.setChecked(false);
            gkhong.setChecked(true);
        }
    }

    private void initview() {
        eten=findViewById(R.id.eten);
        etencasi=findViewById(R.id.etencasi);
        spablum=findViewById(R.id.spalbum);
        sptheloai=findViewById(R.id.sptheloai);
        btnupdate=findViewById(R.id.btnupdate);
        btncancel=findViewById(R.id.btnback);
        btndelete=findViewById(R.id.btndelete);
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
                BaiHat i=new BaiHat(bh.getId(),ten,tencasi,ablum,theloai,yeuthich);
                SQLHelper db=new SQLHelper(this);
                db.update(i);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"phai dien day du thong tin",Toast.LENGTH_SHORT).show();
            }
        }
        if(view==btndelete){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            // thong bao xoa
            builder.setMessage("Ban co chac muon xoa"+bh.getTen()+" khong?");
            builder.setIcon(R.drawable.icon_remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLHelper db=new SQLHelper(getApplicationContext());
                    db.delete(bh.getId());
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