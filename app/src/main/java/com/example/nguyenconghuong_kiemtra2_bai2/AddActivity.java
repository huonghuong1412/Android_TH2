package com.example.nguyenconghuong_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText txtName, txtDate, txtMajor;
    private Button btnAdd, btnCancel, btnDate;
    private Spinner spinner;
    private SqliteKhoaHocHelper sqliteOrderHelper;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_kh, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        sqliteOrderHelper = new SqliteKhoaHocHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Khoahoc s = new Khoahoc();
                String name=txtName.getText().toString();
                String date = txtDate.getText().toString();
                String major = spinner.getSelectedItem().toString();
                int active = checkBox.isChecked() == true ? 1 : 0;
                s.setName(name);
                s.setDate(date);
                s.setMajor(major);
                s.setActive(active);
                sqliteOrderHelper.addKhoahoc(s);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        spinner = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnDate = findViewById(R.id.btnDate);
        checkBox = findViewById(R.id.checkbox);
    }

}