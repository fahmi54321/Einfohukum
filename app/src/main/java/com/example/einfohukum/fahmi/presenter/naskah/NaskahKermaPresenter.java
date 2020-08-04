package com.example.einfohukum.fahmi.presenter.naskah;

import android.util.Log;

import com.example.einfohukum.fahmi.model.naskah.ResponseNaskahKerma;
import com.example.einfohukum.fahmi.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaskahKermaPresenter {
    NaskahKermaView naskahKermaView;
    public NaskahKermaPresenter(NaskahKermaView naskahKermaView) {
        this.naskahKermaView = naskahKermaView;
    }

    public void getPsh(String kesatuan)
    {
        Call<ResponseNaskahKerma> call = ApiService.Factory.create().getNaskahKerma(kesatuan);
        call.enqueue(new Callback<ResponseNaskahKerma>() {
            @Override
            public void onResponse(Call<ResponseNaskahKerma> call, Response<ResponseNaskahKerma> response) {
                if (response.isSuccessful())
                {
                    List data =response.body().getResult();
                    if (data.size()>0)
                    {
                        Log.d("Response Berhasil",response.message());
                        naskahKermaView.onSuccess(response.message(),data);
                        naskahKermaView.hideProgress();
                        naskahKermaView.hideLoading();
                    }
                    else
                    {
                        naskahKermaView.onEmpty();
                        naskahKermaView.hideLoading();
                        naskahKermaView.hideProgress();
                    }
                }
                else
                {
                    Log.d("Response Gagal",response.message());
                    naskahKermaView.onError(response.message());
                    naskahKermaView.hideProgress();
                    naskahKermaView.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ResponseNaskahKerma> call, Throwable t) {
                Log.d("Response Gagal Total",t.getLocalizedMessage());
                naskahKermaView.onError(t.getLocalizedMessage());
                naskahKermaView.hideLoading();
                naskahKermaView.hideProgress();
            }
        });
    }
}
