package com.example.tes1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class EventAdapter extends ArrayAdapter<Events> {

    private List<Events> daftarEvent;
    private Context context;
    private int resource;

    ListView listView;
    ImageView gambarEvent;
    TextView namaEvent, tanggalEvent;

    EventAdapter(Context context, int resource, List<Events> daftarEvent) {
        super(context, resource, daftarEvent);
        this.context = context;
        this.resource = resource;
        this.daftarEvent = daftarEvent;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        @SuppressLint("ViewHolder")
        View view = layoutInflater.inflate(resource, null, false);

        listView = view.findViewById(R.id.list_view);
        gambarEvent = view.findViewById(R.id.imgEvent);
        namaEvent = view.findViewById(R.id.txtNamaEvent);
        tanggalEvent = view.findViewById(R.id.txtTanggalEvent);

        //Mengambil data dari arraylist dan mengurutkan sesuai urutan
        Events events = daftarEvent.get(position);

        //Menampilkan data dari array kedalam imageview dan textview
        gambarEvent.setImageDrawable(context.getResources().getDrawable(events.getGambarId()));
        namaEvent.setText(events.getNamaEvent());
        tanggalEvent.setText(events.getTanggalEvent());

        return view;
    }

}
