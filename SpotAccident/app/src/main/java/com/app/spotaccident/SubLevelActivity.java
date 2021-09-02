package com.app.spotaccident;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotaccident.adapter.SubListAdapter;
import com.app.spotaccident.model.DataPojo;
import com.app.spotaccident.model.TypePojo;

import java.util.ArrayList;
import java.util.List;

public class SubLevelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubListAdapter adapter;
    private String TAG = SubLevelActivity.class.getSimpleName();

    List<TypePojo> listType = new ArrayList<TypePojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_level);

        init();
    }

    private void init() {
        Intent intent = getIntent();
        DataPojo dataPojo = (DataPojo) intent.getSerializableExtra("data");

        getSupportActionBar().setTitle(dataPojo.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listType = dataPojo.getType();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubListAdapter(this, listType);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}