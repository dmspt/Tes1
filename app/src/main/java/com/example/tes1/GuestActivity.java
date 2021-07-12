package com.example.tes1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private GridView gv;

    //Url API
    private static String url = "http://www.mocky.io/v2/596dec7f0f000023032b8017";

    ArrayList<HashMap<String, String>> guestList;

    TextView txtNamaGuest, txtTanggalGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        guestList = new ArrayList<>();

        gv = (GridView) findViewById(R.id.grid_view);
        txtNamaGuest = (TextView) findViewById(R.id.txtNamaGuest);
        txtTanggalGuest = (TextView) findViewById(R.id.txtTanggalGuest);

        new GetGuest().execute();
    }


     //Async task GET data dari http call

    private class GetGuest extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(GuestActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    //GET JSON Array
                    JSONArray guests = jsonObj.getJSONArray("guests");

                    //looping array data
                    for (int i = 0; i < guests.length(); i++) {
                        JSONObject c = guests.getJSONObject(i);

                        String name = c.getString("name");
                        String tanggal = c.getString("birthdate");

                        //hashmap 1 data
                        HashMap<String, String> guest = new HashMap<>();

                        //Menambahkan data sesuai variabel ke value hashmap
                        guest.put("name", name);
                        guest.put("birthdate", tanggal);


                        //Menambahkan data ke list array
                        guestList.add(guest);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Menghilangkan progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //Menambahkan data ke dalam gridview
            ListAdapter adapter = new SimpleAdapter(
                    GuestActivity.this, guestList,
                    R.layout.grid_layout, new String[]{"name", "birthdate"}, new int[]{R.id.txtNamaGuest,
                    R.id.txtTanggalGuest});

            gv.setAdapter(adapter);
        }

    }
}

    /**private void getData() {
        //Melakukan request dengan volley.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://www.mocky.io/v2/596dec7f0f000023032b8017";

        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        //Melakukan request data string ke url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Mengambil data JSON ke dalam JSONObject
                            JSONObject jsonPost = new JSONObject(response.toString());

                            //Menambahkan data kedalam textview
                            txtNamaGuest.setText(jsonPost.getString("name"));
                            txtTanggalGuest.setText(jsonPost.getString("birthdate"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        //Memberikan data respon yang didapat dari url
        queue.add(stringRequest);

    }

}*/