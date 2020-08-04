package com.example.einfohukum.fahmi.presenter.disiplin;

import android.util.Log;
import android.widget.ProgressBar;

import com.example.einfohukum.fahmi.model.disiplin.ResponseDisiplin;
import com.example.einfohukum.fahmi.network.ApiService;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisiplinPresenter {
    DisiplinView disiplinView;
    public DisiplinPresenter(DisiplinView disiplinView) {
        this.disiplinView = disiplinView;
    }
    public void getDisiplin(String kesatuan)
    {
        Call<ResponseDisiplin> call = ApiService.Factory.create().getDisiplin(kesatuan);
        call.enqueue(new Callback<ResponseDisiplin>() {
            @Override
            public void onResponse(Call<ResponseDisiplin> call, Response<ResponseDisiplin> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        disiplinView.onSuccess(response.message(),data);
                        disiplinView.hideProgress();
                        disiplinView.hideLoading();
                    }
                    else
                    {
                        disiplinView.onEmty();
                        disiplinView.hideProgress();
                        disiplinView.hideLoading();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    disiplinView.onError(response.message());
                    disiplinView.hideProgress();
                    disiplinView.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ResponseDisiplin> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                disiplinView.onError(t.getLocalizedMessage());
                disiplinView.hideProgress();
                disiplinView.hideLoading();
            }
        });
    }
}
