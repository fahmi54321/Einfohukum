package com.example.einfohukum.fahmi.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einfohukum.R;
import com.example.einfohukum.fahmi.model.kke.ResultItemKke;
import com.example.einfohukum.fahmi.model.psh.ResultItemPsh;

import java.util.List;
import java.util.Objects;

public class AdapterKke extends RecyclerView.Adapter<AdapterKke.KkeViewHolder> {

    private Context context;
    private List<ResultItemKke> list;

    public AdapterKke(Context context, List<ResultItemKke> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterKke.KkeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_get_kke,null);
        return new KkeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKke.KkeViewHolder holder, int position) {
        final ResultItemKke item =list.get(position);
        holder.nama_pelaku.setText(item.getNamaPelaku());
        holder.pangkat.setText(item.getPangkatNrp());
        holder.nrp.setText(item.getNrp());
        holder.jabatan.setText(item.getJabatan());
        holder.kesatuan.setText(item.getKesatuan());
        holder.nama_pelapor.setText(item.getNamaPelapor());
        holder.pasal_dilanggar.setText(item.getPasalDilanggar());
        holder.id_kke.setText(item.getIdKke());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_item_details_kke);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                TextView txtNamaPelaku,txtPangkat,txtNrp,txtJabatan,txtKesatuan,txtNamaPelapor,txtAlamat,txtPasalDilanggar,
                        txtNomorLaporan,txtTglLaporan,txtNoBerkasPerkara,txtNomorPsh,txtTglSidang,txtIsiPutusan,txtIsiBanding,
                txtPhotoVonis,txtPhotoSurat,txtPhotoPutusan;
                ImageView imgClose;

                txtNamaPelaku = dialog.findViewById(R.id.nama_pelaku);
                txtPangkat = dialog.findViewById(R.id.pangkat);
                txtNrp = dialog.findViewById(R.id.nrp);
                txtJabatan = dialog.findViewById(R.id.jabatan);
                txtKesatuan = dialog.findViewById(R.id.kesatuan);
                txtNamaPelapor = dialog.findViewById(R.id.nama_pelapor);
                txtAlamat = dialog.findViewById(R.id.alamat);
                txtPasalDilanggar = dialog.findViewById(R.id.pasal_dilanggar);
                txtNomorLaporan = dialog.findViewById(R.id.no_laporan);
                txtTglLaporan = dialog.findViewById(R.id.tgl_laporan);
                txtNoBerkasPerkara = dialog.findViewById(R.id.no_berkas_laporan);
                txtNomorPsh = dialog.findViewById(R.id.nomor_psh);
                txtTglSidang = dialog.findViewById(R.id.tgl_sidang);
                txtIsiPutusan = dialog.findViewById(R.id.isi_putusan);
                txtIsiBanding = dialog.findViewById(R.id.isi_banding);
                txtPhotoVonis = dialog.findViewById(R.id.photo_vonis);
                txtPhotoSurat = dialog.findViewById(R.id.photo_surat);
                txtPhotoPutusan = dialog.findViewById(R.id.photo_putusan);
                imgClose = dialog.findViewById(R.id.imgClose);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtNamaPelaku.setText(item.getNamaPelaku());
                txtPangkat.setText(item.getPangkatNrp());
                txtNrp.setText(item.getNrp());
                txtJabatan.setText(item.getJabatan());
                txtKesatuan.setText(item.getKesatuan());
                txtNamaPelapor.setText(item.getNamaPelapor());
                txtAlamat.setText(item.getAlamat());
                txtPasalDilanggar.setText(item.getPasalDilanggar());
                txtNomorLaporan.setText(item.getNoLaporan());
                txtTglLaporan.setText(item.getTglLaporan());
                txtNoBerkasPerkara.setText(item.getNoSidang());
                txtNomorPsh.setText(item.getNomorPsh());
                txtTglSidang.setText(item.getTglSidang());
                txtIsiPutusan.setText(item.getIsiPutusan());
                txtIsiBanding.setText(item.getIsiBanding());
                txtPhotoVonis.setText(item.getPhotoVonis());
                txtPhotoSurat.setText(item.getPhotoSurat());
                txtPhotoPutusan.setText(item.getPhotoPutusan());

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class KkeViewHolder extends RecyclerView.ViewHolder{

        private TextView nama_pelaku,pangkat,nrp,jabatan,kesatuan,nama_pelapor,pasal_dilanggar,id_kke;
        Button buttonDetails;
        public KkeViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_pelaku = itemView.findViewById(R.id.nama_pelaku);
            pangkat = itemView.findViewById(R.id.pangkat);
            nrp = itemView.findViewById(R.id.nrp);
            jabatan = itemView.findViewById(R.id.jabatan);
            kesatuan = itemView.findViewById(R.id.kesatuan);
            nama_pelapor = itemView.findViewById(R.id.nama_pelapor);
            pasal_dilanggar = itemView.findViewById(R.id.pasal_dilanggar);
            id_kke = itemView.findViewById(R.id.id_kke);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
        }
    }
}
