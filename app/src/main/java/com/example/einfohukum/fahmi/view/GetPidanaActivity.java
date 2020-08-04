package com.example.einfohukum.fahmi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.einfohukum.R;
import com.example.einfohukum.fahmi.adapter.AdapterKke;
import com.example.einfohukum.fahmi.adapter.AdapterPidana;
import com.example.einfohukum.fahmi.model.kke.ResponseKke;
import com.example.einfohukum.fahmi.model.pidana.ResponsePidana;
import com.example.einfohukum.fahmi.model.pidana.ResultItemPidana;
import com.example.einfohukum.fahmi.network.ApiService;
import com.example.einfohukum.fahmi.presenter.pidana.PidanaPresenter;
import com.example.einfohukum.fahmi.presenter.pidana.PidanaView;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPidanaActivity extends AppCompatActivity implements PidanaView {

    Button bttnCari;
    TextView kesatuan;
    RecyclerView rvPidana;
    ProgressBar progressBarPidana;
    ImageView imgSearch;
    private PidanaPresenter pidanaPresenter;
    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pidana);

        bttnCari = findViewById(R.id.buttonKesatuan);
        kesatuan = findViewById(R.id.txtkesatuan);
        rvPidana = findViewById(R.id.getPidana);
        progressBarPidana = findViewById(R.id.progressBarPidana);
        imgSearch = findViewById(R.id.immgSearch);

        pidanaPresenter = new PidanaPresenter(this);
        kesatuan.setText(user.getKesatuan());
        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                progressBarPidana.setVisibility(View.VISIBLE);
                pidanaPresenter.getPidana(kesatuan.getText().toString());
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilDiaog();
            }
        });
    }

    @Override
    public void onSuccess(String message, List<ResultItemPidana> result) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        AdapterPidana adapterPidana = new AdapterPidana(this,result);
        rvPidana.setAdapter(adapterPidana);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmty() {
        Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        progressBarPidana.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnCari.setText("Tampilkan Data");
        bttnCari.setEnabled(true);
    }

    private void tampilDiaog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_item_search_pidana);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        final TextView txtKesatuan;
        ImageView imgClose;
        final EditText edtNamaPelaku,edtPangkat;
        final RecyclerView getPidanaSearch;
        final Button buttonCari;
        final ProgressBar progressBarSearch;

        txtKesatuan = dialog.findViewById(R.id.txtKesatuan);
        imgClose = dialog.findViewById(R.id.imgClose);
        edtNamaPelaku = dialog.findViewById(R.id.edtNamaPelaku);
        edtPangkat = dialog.findViewById(R.id.edtPangkat);
        buttonCari = dialog.findViewById(R.id.buttonCari);
        getPidanaSearch = dialog.findViewById(R.id.getPidanaSearch);
        progressBarSearch = dialog.findViewById(R.id.progressBarSearch);

        txtKesatuan.setText(user.getKesatuan());
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCari.setText("Loading...");
                buttonCari.setEnabled(false);
                progressBarSearch.setVisibility(View.VISIBLE);

                String hslNama,hslPangkat,hslKesatuan;
                hslNama = edtNamaPelaku.getText().toString();
                hslPangkat = edtPangkat.getText().toString();
                hslKesatuan = txtKesatuan.getText().toString();

                Call<ResponsePidana> call = ApiService.Factory.create().searchingPidana(hslKesatuan,hslNama,hslPangkat);
                call.enqueue(new Callback<ResponsePidana>() {
                    @Override
                    public void onResponse(Call<ResponsePidana> call, Response<ResponsePidana> response) {
                        if (response.isSuccessful())
                        {
                            List data =response.body().getResult();
                            if (data.size()>0)
                            {
                                Toast.makeText(dialog.getContext(),"Data Ditemukan",Toast.LENGTH_LONG).show();
                                buttonCari.setText("Cari");
                                buttonCari.setEnabled(true);
                                progressBarSearch.setVisibility(View.GONE);

                                Log.d("Koneksi Berhasil",response.message());
                                AdapterPidana adapterPidana = new AdapterPidana(dialog.getContext(),data);
                                getPidanaSearch.setAdapter(adapterPidana);
                            }
                            else
                            {
                                Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                                buttonCari.setText("Cari");
                                buttonCari.setEnabled(true);
                                progressBarSearch.setVisibility(View.GONE);

                                Log.d("Koneksi Gagal 1",response.message());
                            }
                        }
                        else
                        {
                            Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                            buttonCari.setText("Cari");
                            buttonCari.setEnabled(true);
                            progressBarSearch.setVisibility(View.GONE);

                            Log.d("Koneksi Gagal 2",response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePidana> call, Throwable t) {
                        Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                        buttonCari.setText("Cari");
                        buttonCari.setEnabled(true);
                        progressBarSearch.setVisibility(View.GONE);

                        Log.d("Koneksi Gagal 2",t.getLocalizedMessage());
                    }
                });
            }
        });

        dialog.show();
    }
}