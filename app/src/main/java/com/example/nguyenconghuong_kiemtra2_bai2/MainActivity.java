package com.example.nguyenconghuong_kiemtra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnShowAddForm;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SqliteKhoaHocHelper sqliteKhoaHocHelper;
    private SearchView searchView;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter();
        sqliteKhoaHocHelper = new SqliteKhoaHocHelper(this);

        List<Khoahoc> list = sqliteKhoaHocHelper.getAll();
        adapter.setOrders(list);
        recyclerView.setAdapter(adapter);

        txt = findViewById(R.id.textView5);
        txt.setText("Tong so khoa hoc: " + list.size());

        btnShowAddForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Khoahoc> list=sqliteKhoaHocHelper.getByName(newText);
                adapter.setOrders(list);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });

    }

    @Override
    protected void onRestart() {
        List<Khoahoc> list = sqliteKhoaHocHelper.getAll();
        adapter.setOrders(list);
        recyclerView.setAdapter(adapter);
        txt = findViewById(R.id.textView5);
        txt.setText("Tong so khoa hoc: " + list.size());
        super.onRestart();
    }

    private void initView() {
        btnShowAddForm = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
    }
}