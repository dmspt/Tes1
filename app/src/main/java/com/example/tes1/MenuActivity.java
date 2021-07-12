package com.example.tes1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Button pilih_event, pilih_guest;
    TextView text_nama;

    private String nama;
    //Deklarasi key nama dari home screen
    private String KEY = "NAMA";

    private List<Events> daftarEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pilih_event = (Button)findViewById(R.id.btnEvent);
        pilih_guest = (Button)findViewById(R.id.btnGuest);
        text_nama = (TextView)findViewById(R.id.txtNama);

        //Mengambil data dengan bundle intent dan menampilkan pada textview screen 2
        Bundle extras = getIntent().getExtras();
        nama = extras.getString(KEY);
        text_nama.setText("Nama : " + nama);

        //Bundle extraBundle = getIntent().getExtras();
        //int[] myArray = extraBundle.getIntArray("daftarEvent");
        //pilih_event.setText(Integer.parseInt(Arrays.toString(myArray)));

        //Mengambil data dengan bundle intent untuk ditampilkan ditampilkan pada button menu event
        Bundle b = getIntent().getExtras();
        ArrayList<Events> q = (ArrayList<Events>) b.getSerializable("event");
        //pilih_event.setText((CharSequence) q);
        pilih_event.setText(String.valueOf(q));

        pilih_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, EventActivity.class);
                startActivity(i);
            }
        });

        pilih_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, GuestActivity.class);
                startActivity(i);
            }
        });

    }
}