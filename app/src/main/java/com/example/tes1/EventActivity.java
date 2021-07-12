package com.example.tes1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventActivity extends AppCompatActivity implements Serializable{

    List<Events> daftarEvent;
    ListView listView;

    TextView txt_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        daftarEvent = new ArrayList<>();
        listView = findViewById(R.id.list_view);
        txt_nama = findViewById(R.id.txtNamaEvent);

        //Menambahkan data Array ke dalam list
        daftarEvent.add(new Events(R.drawable.lari, "Lomba Lari", "17 Agustus 2021"));
        daftarEvent.add(new Events(R.drawable.kucing, "Kontes Kucing", "20 Juli 2021"));
        daftarEvent.add(new Events(R.drawable.lukisan, "Pameran Lukisan", "29 Oktober 2021"));
        daftarEvent.add(new Events(R.drawable.uefa, "Final UEFA", "12 Desember 2021"));

        EventAdapter adapter = new EventAdapter(this, R.layout.list_item, daftarEvent);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Mengambil data dari list yang diklik untuk ditampilkan pada button menu event screen 2
                Bundle bundle = new Bundle();
                bundle.putSerializable("event", (Serializable) daftarEvent);
                Intent i = new Intent(EventActivity.this, MenuActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }

}