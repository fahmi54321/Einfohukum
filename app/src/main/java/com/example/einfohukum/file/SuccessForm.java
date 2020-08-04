package com.example.einfohukum.file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.einfohukum.HomeActivity;
import com.example.einfohukum.R;

public class SuccessForm extends AppCompatActivity {

    ImageView imgCeklis;
    TextView txtTerimaKasih,txtKeterangan;
    Button bttnHome;
    Animation top_to_bottom,bottom_to_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_form);

        imgCeklis = findViewById(R.id.imgCeklis);
        txtTerimaKasih = findViewById(R.id.txtTerimaKasih);
        txtKeterangan = findViewById(R.id.txtKeterangan);
        bttnHome = findViewById(R.id.bttnHome);

        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom);
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top);

        imgCeklis.startAnimation(top_to_bottom);
        txtTerimaKasih.startAnimation(bottom_to_top);
        txtKeterangan.startAnimation(bottom_to_top);
        bttnHome.startAnimation(bottom_to_top);

        bttnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Intent intent = new Intent(SuccessForm.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
