package com.lock.fixie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.lock.fixie.Adapter.GridAdapter;

import java.util.ArrayList;

public class ImagenesActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Paola");
        arrayList.add("Fernando");
        arrayList.add("Alex");
        arrayList.add("Kevin");
        arrayList.add("Rata");



        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new GridAdapter(this,arrayList);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ImagenesActivity.this, DetallesActivity.class);
                intent.putExtra("Nombre", adapter.getItem(position).toString());
                startActivity(intent);
            }
        });

    }
}
