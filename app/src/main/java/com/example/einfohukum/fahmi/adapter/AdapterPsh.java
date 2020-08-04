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
import com.example.einfohukum.fahmi.model.psh.ResultItemPsh;

import java.util.List;
import java.util.Objects;

public class AdapterPsh extends RecyclerView.Adapter<AdapterPsh.PshViewHolder> {

    private Context context;
    private List<ResultItemPsh> list;

    public AdapterPsh(Context context, List<ResultItemPsh> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterPsh.PshViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_get_psh,null);
        return new PshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterPsh.PshViewHolder holder, int position) {
        final ResultItemPsh item =list.get(position);
        holder.kesatuan.setText(item.getKesatuan());
        holder.perkara.setText(item.getPerkara());
        holder.nomor_psh.setText(item.getNomorPsh());
        holder.tanggal.setText(item.getTanggal());
        holder.nama_terduga_pelanggar.setText(item.getNama());
        holder.pangkat.setText(item.getPangkatNrp());
        holder.nrp.setText(item.getNrp());
        holder.pasal_dilanggar.setText(item.getPasalDilanggar());
        holder.id_psh.setText(item.getIdPsh());
        holder.photo_vonis.setText(item.getPhotoVonis());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_item_details_psh);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                TextView txtPerkara,txtNomorPsh,txtTanggal,txtNamaTerduga,txtPangkat,txtNrp,txtKesatuan,txtPasalDilanggar,txtPhotoVonis;
                ImageView imgClose;

                txtPerkara = dialog.findViewById(R.id.perkara);
                txtNomorPsh = dialog.findViewById(R.id.nomor_psh);
                txtTanggal = dialog.findViewById(R.id.tanggal);
                txtNamaTerduga = dialog.findViewById(R.id.nama_terduga_pelanggar);
                txtPangkat = dialog.findViewById(R.id.pangkat);
                txtNrp = dialog.findViewById(R.id.nrp);
                txtKesatuan = dialog.findViewById(R.id.kesatuan);
                txtPasalDilanggar = dialog.findViewById(R.id.pasal_dilanggar);
                imgClose = dialog.findViewById(R.id.imgClose);
                txtPhotoVonis = dialog.findViewById(R.id.photo_vonis);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtPerkara.setText(item.getPerkara());
                txtNomorPsh.setText(item.getNomorPsh());
                txtTanggal.setText(item.getTanggal());
                txtNamaTerduga.setText(item.getNama());
                txtPangkat.setText(item.getPangkatNrp());
                txtNrp.setText(item.getNrp());
                txtKesatuan.setText(item.getKesatuan());
                txtPasalDilanggar.setText(item.getPasalDilanggar());
                txtPhotoVonis.setText(item.getPhotoVonis());

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PshViewHolder extends RecyclerView.ViewHolder{

        private TextView kesatuan,nomor_psh,tanggal,nama_terduga_pelanggar,pangkat,nrp,pasal_dilanggar,perkara,id_psh,photo_vonis;
        Button buttonDetails;
        public PshViewHolder(@NonNull View itemView) {
            super(itemView);

            perkara = itemView.findViewById(R.id.perkara);
            kesatuan = itemView.findViewById(R.id.kesatuan);
            nomor_psh = itemView.findViewById(R.id.nomor_psh);
            tanggal = itemView.findViewById(R.id.tanggal);
            nama_terduga_pelanggar = itemView.findViewById(R.id.nama_terduga_pelanggar);
            pangkat = itemView.findViewById(R.id.pangkat);
            nrp = itemView.findViewById(R.id.nrp);
            pasal_dilanggar = itemView.findViewById(R.id.pasal_dilanggar);
            id_psh = itemView.findViewById(R.id.id_psh);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
            photo_vonis = itemView.findViewById(R.id.photo_vonis);
        }
    }
}
