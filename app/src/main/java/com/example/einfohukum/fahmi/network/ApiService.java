package com.example.einfohukum.fahmi.network;

import com.example.einfohukum.fahmi.config.Constants;
import com.example.einfohukum.fahmi.model.disiplin.ResponseDisiplin;
import com.example.einfohukum.fahmi.model.kke.ResponseKke;
import com.example.einfohukum.fahmi.model.naskah.ResponseNaskahKerma;
import com.example.einfohukum.fahmi.model.peraturan.ResponsePeraturan;
import com.example.einfohukum.fahmi.model.pidana.ResponsePidana;
import com.example.einfohukum.fahmi.model.psh.ResponsePsh;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("getDataAngkaPsh.php")
    Call<ResponsePsh> getPsh
            (
                    @Query("kesatuan") String kesatuan
            );
    @GET("getDataAngkaKke.php")
    Call<ResponseKke> getKke
            (
                    @Query("kesatuan") String kesatuan
            );
    @GET("getDataAngkaDisiplin.php")
    Call<ResponseDisiplin> getDisiplin
            (
                    @Query("kesatuan") String kesatuan
            );
    @GET("getDataAngkaPidana.php")
    Call<ResponsePidana> getPidana
            (
                    @Query("kesatuan") String kesatuan
            );
    @GET("getDataAngkaNaskahKerma.php")
    Call<ResponseNaskahKerma> getNaskahKerma
            (
                    @Query("kesatuan") String kesatuan
            );
    @GET("getDataAngkaPeraturan.php")
    Call<ResponsePeraturan> getPeraturan
            (
                    @Query("kesatuan") String kesatuan
            );

    @GET("searchingDataPsh.php")
    Call<ResponsePsh> searchingPsh
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("nama") String nama,
                    @Query("pangkat_nrp") String pangkat_nrp
            );

    @GET("searchingDataPidana.php")
    Call<ResponsePidana> searchingPidana
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("nama_pelaku") String nama_pelaku,
                    @Query("pangkat_nrp") String pangkat_nrp
            );
    @GET("searchingDataDisiplin.php")
    Call<ResponseDisiplin> searchingDisiplin
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("nama_pelaku") String nama_pelaku,
                    @Query("pangkat_nrp") String pangkat_nrp
            );
    @GET("searchingDataPeraturan.php")
    Call<ResponsePeraturan> searchingPeraturan
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("nama_pejabat") String nama_pejabat,
                    @Query("tentang") String tentang
            );
    @GET("searchingDataNaskah.php")
    Call<ResponseNaskahKerma> searchingNaskah
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("para_pihak") String para_pihak,
                    @Query("tentang") String tentang
            );
    @GET("searchingDataKke.php")
    Call<ResponseKke> searchingKke
            (
                    @Query("kesatuan") String kesatuan,
                    @Query("nama_pelaku") String nama_pelaku,
                    @Query("pangkat_nrp") String pangkat_nrp
            );

    class Factory
    {
        public static ApiService create()
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20,TimeUnit.SECONDS);
            builder.writeTimeout(20,TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
