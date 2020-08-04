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
import com.example.einfohukum.fahmi.adapter.AdapterPeraturan;
import com.example.einfohukum.fahmi.model.peraturan.ResponsePeraturan;
import com.example.einfohukum.fahmi.model.peraturan.ResultItemPeraturan;
import com.example.einfohukum.fahmi.network.ApiService;
import com.example.einfohukum.fahmi.presenter.peraturan.PeraturanPresenter;
import com.example.einfohukum.fahmi.presenter.peraturan.PeraturanView;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPeraturanActivity extends AppCompatActivity implements PeraturanView {

    Button bttnCari;
    TextView kesatuan;
    RecyclerView rvPeraturan;
    ProgressBar progressBarPeraturan;
    ImageView imgSearch;
    private PeraturanPresenter peraturanPresenter;

    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_peraturan);

        bttnCari = findViewById(R.id.buttonKesatuan);
        kesatuan = findViewById(R.id.txtkesatuan);
        rvPeraturan = findViewById(R.id.getPeraturan);
        progressBarPeraturan = findViewById(R.id.progressBarPeraturan);
        imgSearch = findViewById(R.id.immgSearch);

        peraturanPresenter = new PeraturanPresenter(this);
        kesatuan.setText(user.getKesatuan());
        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                progressBarPeraturan.setVisibility(View.VISIBLE);
                peraturanPresenter.getPeraturan(kesatuan.getText().toString());
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilDialog();
            }
        });
    }

    @Override
    public void onSuccess(String message, List<ResultItemPeraturan> result) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        AdapterPeraturan adapterPeraturan = new AdapterPeraturan(this,result);
        rvPeraturan.setAdapter(adapterPeraturan);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEmpty() {
        Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        progressBarPeraturan.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnCari.setText("Tampilkan Data");
        bttnCari.setEnabled(true);
    }

    private void tampilDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_item_search_peraturan);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        final TextView txtKesatuan;
        final EditText NamaPejabat,Tentang;
        final Button buttonCari;
        final ProgressBar progressBarSearch;
        final RecyclerView getPeraturanSearch;

        NamaPejabat = dialog.findViewById(R.id.edtNamaPejabat);
        Tentang = dialog.findViewById(R.id.edtTentang);
        buttonCari = dialog.findViewById(R.id.buttonCari);
        progressBarSearch = dialog.findViewById(R.id.progressBarSearch);
        getPeraturanSearch = dialog.findViewById(R.id.getPeraturanSearch);
        txtKesatuan = dialog.findViewById(R.id.txtKesatuan);
        txtKesatuan.setText(user.getKesatuan());

        ImageView imgClose;
        imgClose = dialog.findViewById(R.id.imgClose);
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

                String hslNamaPejabat,hslTentang,hslKesatuan;
                hslNamaPejabat = NamaPejabat.getText().toString();
                hslTentang = Tentang.getText().toString();
                hslKesatuan = txtKesatuan.getText().toString();

                Call<ResponsePeraturan> call = ApiService.Factory.create().searchingPeraturan(hslKesatuan,hslNamaPejabat,hslTentang);
                call.enqueue(new Callback<ResponsePeraturan>() {
                    @Override
                    public void onResponse(Call<ResponsePeraturan> call, Response<ResponsePeraturan> response) {
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
                                AdapterPeraturan adapterPeraturan = new AdapterPeraturan(dialog.getContext(),data);
                                getPeraturanSearch.setAdapter(adapterPeraturan);
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
                    public void onFailure(Call<ResponsePeraturan> call, Throwable t) {
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