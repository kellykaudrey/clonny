package com.example.clonny;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;


import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txt_nama;
    private EditText txt_email;
    private EditText txt_nohp;
    private EditText txt_pass;
    private EditText txt_conf_pass;

    private ImageButton btn_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nama = (EditText) findViewById(R.id.txt_nama);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_nohp = (EditText) findViewById(R.id.txt_nohp);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
        txt_conf_pass = (EditText) findViewById(R.id.txt_conf_pass);

        btn_regis = (ImageButton) findViewById(R.id.btn_regis);

        btn_regis.setOnClickListener(this);
    }

    private void registerUser(){
        final String nama_user = txt_nama.getText().toString().trim();
        final String email = txt_email.getText().toString().trim();
        final String no_hp = txt_nohp.getText().toString().trim();
        final String password = txt_pass.getText().toString().trim();
        final String conf_pass = txt_conf_pass.getText().toString().trim();

        class RegisterUser extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v){
                HashMap<String,String> params = new HashMap<>();
                params.put(Koneksi_register.KEY_EMP_NAMA_USER,nama_user);
                params.put(Koneksi_register.KEY_EMP_EMAIL,email);
                params.put(Koneksi_register.KEY_EMP_NO_HP,no_hp);
                params.put(Koneksi_register.KEY_EMP_PASSWORD,password);

                RequestHandler_register rh_regis = new RequestHandler_register();
                String res = rh_regis.sendPostRequest(Koneksi_register.URL_ADD, params);
                return res;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();

    }

    public void onClick(View v){
        if(v == btn_regis){
            registerUser();
        }
    }

}