package com.example.nguyenconghuong_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private EditText txtId, txtName, txtDate, txtMajor;
    private Button btnUpdate, btnCancel, btnDate, btnDelete;
    private SqliteKhoaHocHelper sqliteOrderHelper;
    private Spinner spinner2;
    private CheckBox checkBox;
    private Khoahoc khoahoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();
        sqliteOrderHelper = new SqliteKhoaHocHelper(this);

        Intent intent = getIntent();
        if(intent != null) {
            khoahoc = (Khoahoc) intent.getSerializableExtra("khoahoc");
            Integer id = khoahoc.getId();
            String name = khoahoc.getName();
            String date = khoahoc.getDate();
            String major = khoahoc.getMajor();
            int active = khoahoc.getActive();
            txtId.setText(id.toString());
            txtName.setText(name);
            txtDate.setText(date);
//            spinner2.setP
//            txtMajor.setText("ABC");
            boolean check = active == 1 ? true : false;
            checkBox.setChecked(check);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int id=Integer.parseInt(txtId.getText().toString());
                    String n=txtName.getText().toString();
                    String date = txtDate.getText().toString();
                    String major = txtMajor.getText().toString();
                    int active = checkBox.isChecked() == true ? 1 : 0;
                    Khoahoc s=new Khoahoc(id,n, date, major, active);
                    sqliteOrderHelper.update(s);
                    finish();
                }catch(NumberFormatException e){
                    System.out.println(e);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked() != true) {
                    int id=Integer.parseInt(txtId.getText().toString());
                    sqliteOrderHelper.delete(id);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Không thể xoá", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }

    private void initView() {
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        spinner2 = findViewById(R.id.spinner2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        btnDate = findViewById(R.id.btnDate);
        checkBox = findViewById(R.id.checkbox);
    }
}