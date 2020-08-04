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
import com.example.einfohukum.fahmi.adapter.AdapterDisiplin;
import com.example.einfohukum.fahmi.model.disiplin.ResponseDisiplin;
import com.example.einfohukum.fahmi.model.disiplin.ResultItemDisiplin;
import com.example.einfohukum.fahmi.network.ApiService;
import com.example.einfohukum.fahmi.presenter.disiplin.DisiplinPresenter;
import com.example.einfohukum.fahmi.presenter.disiplin.DisiplinView;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDisiplinActivity extends AppCompatActivity implements DisiplinView {

    ProgressBar progressBarDisiplin;
    Button bttnCari;
    TextView kesatuan;
    RecyclerView rvDisiplin;
    ImageView imgSearch;
    private DisiplinPresenter disiplinPresenter;
    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_disiplin);
        bttnCari = findViewById(R.id.buttonKesatuan);
        kesatuan = findViewById(R.id.txtkesatuan);
        rvDisiplin = findViewById(R.id.getDisiplin);
        progressBarDisiplin = findViewById(R.id.progressBarDisiplin);
        imgSearch = findViewById(R.id.immgSearch);

        disiplinPresenter = new DisiplinPresenter(this);
        kesatuan.setText(user.getKesatuan());
        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarDisiplin.setVisibility(View.VISIBLE);
                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                disiplinPresenter.getDisiplin(kesatuan.getText().toString());
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
    public void onSuccess(String message, List<ResultItemDisiplin> result) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        AdapterDisiplin adapterDisiplin = new AdapterDisiplin(this,result);
        rvDisiplin.setAdapter(adapterDisiplin);
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
        progressBarDisiplin.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnCari.setText("Tampilkan Data");
        bttnCari.setEnabled(true);
    }

    private void tampilDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_item_search_disiplin);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        final TextView txtKesatuan;
        ImageView imgClose;
        final EditText edtNamaPelaku,edtPangkat;
        final RecyclerView getDisiplinSearch;
        final Button buttonCari;
        final ProgressBar progressBarSearch;

        txtKesatuan = dialog.findViewById(R.id.txtKesatuan);
        imgClose = dialog.findViewById(R.id.imgClose);
        edtNamaPelaku = dialog.findViewById(R.id.edtNamaPelaku);
        edtPangkat = dialog.findViewById(R.id.edtPangkat);
        buttonCari = dialog.findViewById(R.id.buttonCari);
        getDisiplinSearch = dialog.findViewById(R.id.getDisiplinSearch);
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

                Call<ResponseDisiplin> call = ApiService.Factory.create().searchingDisiplin(hslKesatuan,hslNama,hslPangkat);
                call.enqueue(new Callback<ResponseDisiplin>() {
                    @Override
                    public void onResponse(Call<ResponseDisiplin> call, Response<ResponseDisiplin> response) {
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
                                AdapterDisiplin adapterDisiplin = new AdapterDisiplin(dialog.getContext(),data);
                                getDisiplinSearch.setAdapter(adapterDisiplin);
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
                    public void onFailure(Call<ResponseDisiplin> call, Throwable t) {
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