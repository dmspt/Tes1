package com.example.tes1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_next;
    EditText edt_nama;

    //Deklarasi vaiabel kunci untuk mengambil data dari input nama untuk ditampilkan di screen 2
    private String KEY = "NAMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_next = (Button) findViewById(R.id.btnNext);
        edt_nama = (EditText) findViewById(R.id.edtNama);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPalindrome();
                try {
                    //Mengambil value dari edittext
                    String nama = edt_nama.getText().toString();

                    //Melakukan pengecekan form edittext apakah kosong atau tidak
                    //Jika tidak maka akan diteruskan ke screen 2
                    if (nama != null && !nama.equals("")) {
                        Intent i = new Intent(MainActivity.this, MenuActivity.class);
                        i.putExtra(KEY, nama);
                        startActivity(i);

                        //Jika kosong atau belum mengisi akan tampil toast untuk mengisi data
                    } else {
                        Toast.makeText(MainActivity.this, "Isi nama dulu dong!", Toast.LENGTH_SHORT).show();
                    }

                    //Menampilkan log error jika ada error terjadi
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error, Coba lagi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Method untuk cek palindrome
    private void CheckPalindrome() {
        //Mengubah semua teks yang diinput ke huruf biasa dari kapital
        String namaa = edt_nama.getText().toString().toLowerCase();
        //Mengubah varibel nama dari string menjadi char untuk dihitung jumlah hurufnya
        char[] charInput = namaa.toCharArray();
        int intLength = charInput.length;
        boolean isPalindrome = true;

        //Melakukan penghitungan dan mencocokkan apakah jumlah huruf sama atau tidak dan cek apakah memiliki huruf yang sama atau palindrome
        for (int i=0; i<intLength/2; i++) {
            if (charInput[i] != charInput[intLength-1-i]){
                isPalindrome = false;
                break;
            }
        }

        //Jika palindrome akan tampil toast is Palindrome
        if (isPalindrome) {
            Toast.makeText(this, "is Palindrome", Toast.LENGTH_SHORT).show();

            //Jika tidak akan tampil toast not palindrome
        } else {
            Toast.makeText(this, "not Palindrome", Toast.LENGTH_SHORT).show();
        }
    }

}