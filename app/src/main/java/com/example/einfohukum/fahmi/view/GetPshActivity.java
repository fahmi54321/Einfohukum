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
import com.example.einfohukum.fahmi.adapter.AdapterPsh;
import com.example.einfohukum.fahmi.model.psh.ResponsePsh;
import com.example.einfohukum.fahmi.model.psh.ResultItemPsh;
import com.example.einfohukum.fahmi.network.ApiService;
import com.example.einfohukum.fahmi.presenter.psh.PshPresenter;
import com.example.einfohukum.fahmi.presenter.psh.PshView;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPshActivity extends AppCompatActivity implements PshView {

    Button bttnCari;
    TextView kesatuan;
    RecyclerView rvPsh;
    ProgressBar progressBarPsh;
    ImageView imgSearch;
    private PshPresenter pshPresenter;

    User user = PrefManager.getInstance(getActivity()).getUser();
    private Context getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_psh);

        bttnCari = findViewById(R.id.buttonKesatuan);
        kesatuan = findViewById(R.id.txtkesatuan);
        rvPsh = findViewById(R.id.getPsh);
        progressBarPsh = findViewById(R.id.progressBarPsh);
        imgSearch = findViewById(R.id.immgSearch);

        pshPresenter = new PshPresenter(this);
        kesatuan.setText(user.getKesatuan());
        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                progressBarPsh.setVisibility(View.VISIBLE);
                pshPresenter.getPsh(kesatuan.getText().toString());
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
    public void onSuccess(String message, List<ResultItemPsh> result) {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        AdapterPsh adapterPsh = new AdapterPsh(this,result);
        rvPsh.setAdapter(adapterPsh);
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
        progressBarPsh.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnCari.setText("Tampilkan Data");
        bttnCari.setEnabled(true);
    }

    private void tampilDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_item_search_psh);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        ImageView imgClose;
        final ProgressBar progressBarSearch;
        final TextView txtKesatuan;
        final EditText edtnama,edtPangkat;
        final Button bttnCari;
        final RecyclerView getPshSearch;

        txtKesatuan = dialog.findViewById(R.id.txtKesatuan);
        edtnama = dialog.findViewById(R.id.edtNama);
        edtPangkat = dialog.findViewById(R.id.edtPangkat);
        bttnCari = dialog.findViewById(R.id.buttonCari);
        getPshSearch = dialog.findViewById(R.id.getPshSearch);
        progressBarSearch = dialog.findViewById(R.id.progressBarSearch);

        txtKesatuan.setText(user.getKesatuan());
        imgClose = dialog.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        bttnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bttnCari.setText("Loading...");
                bttnCari.setEnabled(false);
                progressBarSearch.setVisibility(View.VISIBLE);

                String hslNama,hslPangkat;
                hslNama = edtnama.getText().toString();
                hslPangkat = edtPangkat.getText().toString();

                Call<ResponsePsh> call = ApiService.Factory.create().searchingPsh(txtKesatuan.getText().toString(),hslNama,hslPangkat);
                call.enqueue(new Callback<ResponsePsh>() {
                    @Override
                    public void onResponse(Call<ResponsePsh> call, Response<ResponsePsh> response) {
                        if (response.isSuccessful())
                        {
                            List data =response.body().getResult();
                            if (data.size()>0)
                            {
                                Toast.makeText(dialog.getContext(),"Data Ditemukan",Toast.LENGTH_LONG).show();
                                bttnCari.setText("Cari");
                                bttnCari.setEnabled(true);
                                progressBarSearch.setVisibility(View.GONE);

                                Log.d("Koneksi Berhasil",response.message());
                                AdapterPsh adapterPsh = new AdapterPsh(dialog.getContext(),data);
                                getPshSearch.setAdapter(adapterPsh);
                            }
                            else
                            {
                                Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                                bttnCari.setText("Cari");
                                bttnCari.setEnabled(true);
                                progressBarSearch.setVisibility(View.GONE);

                                Log.d("Koneksi Gagal 1",response.message());
                            }
                        }
                        else
                        {
                            Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                            bttnCari.setText("Cari");
                            bttnCari.setEnabled(true);
                            progressBarSearch.setVisibility(View.GONE);

                            Log.d("Koneksi Gagal 2",response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePsh> call, Throwable t) {
                        bttnCari.setText("Cari");
                        bttnCari.setEnabled(true);
                        progressBarSearch.setVisibility(View.GONE);

                        Toast.makeText(dialog.getContext(),"Data Tidak Ditemukan",Toast.LENGTH_LONG).show();
                        Log.d("Koneksi Gagal total",t.getLocalizedMessage());

                    }
                });
            }
        });

        dialog.show();
    }
}