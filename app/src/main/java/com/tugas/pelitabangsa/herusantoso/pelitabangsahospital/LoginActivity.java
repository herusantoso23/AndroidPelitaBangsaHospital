package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.AdminRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Admin;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.AdminValue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog progress;

    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;

    String username, password, nama, email, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        editUsername = (EditText) findViewById(R.id.edit_email);
        editPassword = (EditText) findViewById(R.id.edit_pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = new ProgressDialog(LoginActivity.this);
                progress.setCancelable(false);
                progress.setMessage("Loading");
                progress.show();

                user = editUsername.getText().toString();
                pass = editPassword.getText().toString();

                if(user.equals("admin") && pass.equals("admin")){
                    progress.dismiss();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Username dan Password Salah !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
