package com.example.einfohukum.fahmi.presenter.peraturan;

import android.util.Log;

import com.example.einfohukum.fahmi.model.peraturan.ResponsePeraturan;
import com.example.einfohukum.fahmi.model.psh.ResponsePsh;
import com.example.einfohukum.fahmi.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeraturanPresenter {
    PeraturanView peraturanView;
    public PeraturanPresenter(PeraturanView peraturanView) {
        this.peraturanView = peraturanView;
    }

    public void getPeraturan(String kesatuan)
    {
        Call<ResponsePeraturan> call = ApiService.Factory.create().getPeraturan(kesatuan);
        call.enqueue(new Callback<ResponsePeraturan>() {
            @Override
            public void onResponse(Call<ResponsePeraturan> call, Response<ResponsePeraturan> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        peraturanView.onSuccess(response.message(),data);
                        peraturanView.hideLoading();
                        peraturanView.hideProgress();
                    }
                    else
                    {
                        peraturanView.onEmpty();
                        peraturanView.hideProgress();
                        peraturanView.hideLoading();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    peraturanView.onError(response.message());
                    peraturanView.hideLoading();
                    peraturanView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponsePeraturan> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                peraturanView.onError(t.getLocalizedMessage());
                peraturanView.hideLoading();
                peraturanView.hideProgress();
            }
        });
    }
}
