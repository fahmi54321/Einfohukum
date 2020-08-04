package com.example.einfohukum.fahmi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.einfohukum.R;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

public class TampilActivity extends AppCompatActivity {

    TextView kesatuan,kesatuan1;
    LinearLayout buttonPsh,buttonKke,buttonDisiplin,buttonPidana,buttonNaskah,buttonPeraturan;

    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        buttonPsh = findViewById(R.id.linearPsh);
        buttonKke = findViewById(R.id.linearKke);
        buttonDisiplin = findViewById(R.id.linearDisiplin);
        buttonPidana = findViewById(R.id.linearPidana);
        buttonNaskah = findViewById(R.id.linearNaskah);
        buttonPeraturan = findViewById(R.id.linearPeraturan);
        kesatuan = findViewById(R.id.kesatuan);
        kesatuan1 = findViewById(R.id.kesatuan1);

        kesatuan.setText(user.getKesatuan());
        kesatuan1.setText("Data "+user.getKesatuan());

        buttonPsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetPshActivity.class);
                startActivity(intent);
            }
        });
        buttonKke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetKkeActivity.class);
                startActivity(intent);
            }
        });
        buttonDisiplin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetDisiplinActivity.class);
                startActivity(intent);
            }
        });

        buttonPidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetPidanaActivity.class);
                startActivity(intent);
            }
        });
        buttonNaskah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetNaskahKermaActivity.class);
                startActivity(intent);
            }
        });
        buttonPeraturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilActivity.this,GetPeraturanActivity.class);
                startActivity(intent);
            }
        });
    }
}