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
import com.example.einfohukum.fahmi.adapter.AdapterNaskahKerma;
import com.example.einfohukum.fahmi.model.kke.ResponseKke;
import com.example.einfohukum.fahmi.model.naskah.ResponseNaskahKerma;
import com.example.einfohukum.fahmi.model.naskah.ResultItemNaskahKerma;
import com.example.einfohukum.fahmi.network.ApiService;
import com.example.einfohukum.fahmi.presenter.naskah.NaskahKermaPresenter;
import com.example.einfohukum.fahmi.presenter.naskah.NaskahKermaView;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNaskahKermaActivity extends AppCompatActivity implements NaskahKermaView {

    Button bttnCari;
    TextView kesatuan;
    RecyclerView rvNaskah;
    ProgressBar progressBarNaskah;
    ImageView imgSearch;
    private NaskahKermaPresenter naskahKermaPresenter;

    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_naskah_kerma);

        bttnCari = findViewById(R.id.buttonKesatuan);
        kesatuan = findViewById(R.id.txtkesatuan);
        rvNaskah = findViewById(R.id.getNaskah);
        progressBarNaskah = findViewById(R.id.progressBarNaskah);
        imgSearch = findViewById(R.id.immgSearch);

        naskahKermaPresenter = new NaskahKermaPresenter(this);
        kesatuan.setText(user.getKesatuan());
        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarNaskah.setVisibility(View.VISIBLE);
                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                naskahKermaPresenter.getPsh(kesatuan.getText().toString());
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
    public void onSuccess(String message, List<ResultItemNaskahKerma> result) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        AdapterNaskahKerma adapterNaskahKerma = new AdapterNaskahKerma(this,result);
        rvNaskah.setAdapter(adapterNaskahKerma);
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
        progressBarNaskah.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnCari.setText("Tampilkan Data");
        bttnCari.setEnabled(true);
    }

    private void tampilDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_item_search_naskah);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        final TextView txtKesatuan;
        final EditText edtParaPihak,edtTentang;
        final Button buttonCari;
        final RecyclerView getNaskahSearch;
        final ProgressBar progressBarSearch;

        edtParaPihak = dialog.findViewById(R.id.edtParaPihak);
        edtTentang = dialog.findViewById(R.id.edtTentang);
        buttonCari = dialog.findViewById(R.id.buttonCari);
        getNaskahSearch = dialog.findViewById(R.id.getNaskahSearch);
        progressBarSearch = dialog.findViewById(R.id.progressBarSearch);
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

                String hslParaPihak,hslTentang,hslKesatuan;
                hslParaPihak = edtParaPihak.getText().toString();
                hslTentang = edtTentang.getText().toString();
                hslKesatuan = txtKesatuan.getText().toString();

                Call<ResponseNaskahKerma> call = ApiService.Factory.create().searchingNaskah(hslKesatuan,hslParaPihak,hslTentang);
                call.enqueue(new Callback<ResponseNaskahKerma>() {
                    @Override
                    public void onResponse(Call<ResponseNaskahKerma> call, Response<ResponseNaskahKerma> response) {
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
                                AdapterNaskahKerma adapterNaskahKerma = new AdapterNaskahKerma(dialog.getContext(),data);
                                getNaskahSearch.setAdapter(adapterNaskahKerma);
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
                    public void onFailure(Call<ResponseNaskahKerma> call, Throwable t) {
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