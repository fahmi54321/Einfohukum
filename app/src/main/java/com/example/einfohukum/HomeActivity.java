package com.example.einfohukum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.einfohukum.fahmi.view.TampilActivity;
import com.example.einfohukum.file.FragmentDisiplinForm;
import com.example.einfohukum.file.FragmentKKEForm;
import com.example.einfohukum.file.FragmentLpBulananForm;
import com.example.einfohukum.file.FragmentNaskahKermaForm;
import com.example.einfohukum.file.FragmentPSHForm;
import com.example.einfohukum.file.FragmentPeraturanForm;
import com.example.einfohukum.file.FragmentPidanaForm;
import com.example.einfohukum.file.FragmentUser;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

public class HomeActivity extends AppCompatActivity implements
        FragmentPSHForm.OnFragmentInteractionListener,
        FragmentKKEForm.OnFragmentInteractionListener,
        FragmentNaskahKermaForm.OnFragmentInteractionListener,
        FragmentPeraturanForm.OnFragmentInteractionListener,
        FragmentPidanaForm.OnFragmentInteractionListener,
        FragmentDisiplinForm.OnFragmentInteractionListener,
        FragmentUser.OnFragmentInteractionListener,
        FragmentLpBulananForm.OnFragmentInteractionListener{

    Button bttnTampil;
    Button bttnPsh, bttnKKE, bttnDisiplin, bttnPidana, bttnNaskahKerma, bttnPeraturan,bttnLpBulanan;
    TextView kesatuan;
    User user = PrefManager.getInstance(getActivity()).getUser();

    private Context getActivity() {
      return this;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        bttnTampil = findViewById(R.id.bttnTampil);
        bttnPsh = findViewById(R.id.bttn_psh);
        bttnKKE = findViewById(R.id.bttn_kke);
        bttnDisiplin = findViewById(R.id.bttn_disiplin);
        bttnPidana = findViewById(R.id.bttn_pidana);
        bttnNaskahKerma = findViewById(R.id.bttn_naskah_kerma);
        bttnPeraturan = findViewById(R.id.bttn_peraturan);
        bttnLpBulanan = findViewById(R.id.bttn_lp_bulanan);
        kesatuan = (TextView)findViewById(R.id.txtkesatuan);

        kesatuan.setText("Kesatuan : " +user.getKesatuan());
        FragmentPSHForm fragmentPSHForm = new FragmentPSHForm();
        setFragment(fragmentPSHForm);

        bttnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TampilActivity.class);
                intent.putExtra("nama_kesatuan",kesatuan.getText().toString());
                startActivity(intent);
            }
        });


        bttnPsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPSHForm fragmentPSHForm = new FragmentPSHForm();
                setFragment(fragmentPSHForm);
                changeIcon(bttnPsh, R.drawable.bttn_form_active);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
            }
        });

        bttnKKE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentKKEForm fragmentKKEForm = new FragmentKKEForm();
                setFragment(fragmentKKEForm);
                changeIcon(bttnKKE, R.drawable.bttn_form_active);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
            }
        });

        bttnDisiplin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDisiplinForm fragmentDisiplinForm = new FragmentDisiplinForm();
                setFragment(fragmentDisiplinForm);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_active);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
            }
        });

        bttnPidana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPidanaForm fragmentPidanaForm = new FragmentPidanaForm();
                setFragment(fragmentPidanaForm);
                changeIcon(bttnPidana, R.drawable.bttn_form_active);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
            }
        });
        bttnNaskahKerma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentNaskahKermaForm fragmentNaskahKermaForm = new FragmentNaskahKermaForm();
                setFragment(fragmentNaskahKermaForm);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_active);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
            }
        });
        bttnPeraturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPeraturanForm FragmentPeraturanForm = new FragmentPeraturanForm();
                setFragment(FragmentPeraturanForm);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_active);
            }
        });
        bttnLpBulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentLpBulananForm fragmentLpBulananForm = new FragmentLpBulananForm();
                setFragment(fragmentLpBulananForm);
                changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
                changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
                changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
                changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
                changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
                changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);
                changeIcon(bttnLpBulanan, R.drawable.bttn_form_active);
            }
        });
    }

    private void changeIcon(Button button, int drawable) {
        button.setBackgroundResource(drawable);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_home, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void user(View view) {
        FragmentUser FragmentUser = new FragmentUser();
        setFragment(FragmentUser);
        changeIcon(bttnNaskahKerma, R.drawable.bttn_form_inactive);
        changeIcon(bttnPidana, R.drawable.bttn_form_inactive);
        changeIcon(bttnDisiplin, R.drawable.bttn_form_inactive);
        changeIcon(bttnKKE, R.drawable.bttn_form_inactive);
        changeIcon(bttnPsh, R.drawable.bttn_form_inactive);
        changeIcon(bttnPeraturan, R.drawable.bttn_form_inactive);

    }
}
