package com.example.einfohukum.fahmi.presenter.psh;

import android.util.Log;

import com.example.einfohukum.fahmi.model.psh.ResponsePsh;
import com.example.einfohukum.fahmi.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PshPresenter {
    PshView pshView;
    public PshPresenter(PshView pshView) {
        this.pshView = pshView;
    }

    public void getPsh(String kesatuan)
    {
        Call<ResponsePsh> call = ApiService.Factory.create().getPsh(kesatuan);
        call.enqueue(new Callback<ResponsePsh>() {
            @Override
            public void onResponse(Call<ResponsePsh> call, Response<ResponsePsh> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        pshView.onSuccess(response.message(),data);
                        pshView.hideLoading();
                        pshView.hideProgress();
                    }
                    else
                    {
                        pshView.onEmpty();
                        pshView.hideLoading();
                        pshView.hideProgress();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    pshView.onError(response.message());
                    pshView.hideLoading();
                    pshView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponsePsh> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                pshView.onError(t.getLocalizedMessage());
                pshView.hideLoading();
                pshView.hideProgress();
            }
        });
    }
}
