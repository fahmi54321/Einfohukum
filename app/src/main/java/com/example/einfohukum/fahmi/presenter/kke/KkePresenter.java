package com.example.einfohukum.fahmi.presenter.kke;

import android.util.Log;

import com.example.einfohukum.fahmi.model.kke.ResponseKke;
import com.example.einfohukum.fahmi.network.ApiService;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KkePresenter {
    KkeView kkeView;
    public KkePresenter(KkeView kkeView) {
        this.kkeView = kkeView;
    }
    public void getKke(String kesatuan)
    {
        Call<ResponseKke> call = ApiService.Factory.create().getKke(kesatuan);
        call.enqueue(new Callback<ResponseKke>() {
            @Override
            public void onResponse(Call<ResponseKke> call, Response<ResponseKke> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        kkeView.onSuccess(response.message(),data);
                        kkeView.hideProgress();
                        kkeView.hideLoading();
                    }
                    else
                    {
                        kkeView.onEmty();
                        kkeView.hideProgress();
                        kkeView.hideLoading();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    kkeView.onError(response.message());
                    kkeView.hideProgress();
                    kkeView.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ResponseKke> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                kkeView.onError(t.getLocalizedMessage());
                kkeView.hideProgress();
                kkeView.hideLoading();
            }
        });
    }
}
