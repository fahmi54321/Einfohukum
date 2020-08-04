package com.example.einfohukum.fahmi.presenter.pidana;

import android.util.Log;

import com.example.einfohukum.fahmi.model.kke.ResponseKke;
import com.example.einfohukum.fahmi.model.pidana.ResponsePidana;
import com.example.einfohukum.fahmi.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PidanaPresenter {
    PidanaView pidanaView;
    public PidanaPresenter(PidanaView pidanaView) {
        this.pidanaView = pidanaView;
    }
    public void getPidana(String kesatuan)
    {
        Call<ResponsePidana> call = ApiService.Factory.create().getPidana(kesatuan);
        call.enqueue(new Callback<ResponsePidana>() {
            @Override
            public void onResponse(Call<ResponsePidana> call, Response<ResponsePidana> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        pidanaView.onSuccess(response.message(),data);
                        pidanaView.hideLoading();
                        pidanaView.hideProgress();
                    }
                    else
                    {
                        pidanaView.onEmty();
                        pidanaView.hideLoading();
                        pidanaView.hideProgress();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    pidanaView.onError(response.message());
                    pidanaView.hideLoading();
                    pidanaView.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponsePidana> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                pidanaView.onError(t.getLocalizedMessage());
                pidanaView.hideLoading();
                pidanaView.hideProgress();
            }
        });
    }
}
