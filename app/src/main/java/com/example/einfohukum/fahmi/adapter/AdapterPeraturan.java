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
import com.example.einfohukum.fahmi.model.peraturan.ResultItemPeraturan;
import java.util.List;
import java.util.Objects;

public class AdapterPeraturan extends RecyclerView.Adapter<AdapterPeraturan.PeraturanViewHolder> {

    private Context context;
    private List<ResultItemPeraturan> list;

    public AdapterPeraturan(Context context, List<ResultItemPeraturan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterPeraturan.PeraturanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_get_peraturan,null);
        return new PeraturanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPeraturan.PeraturanViewHolder holder, int position) {
        final ResultItemPeraturan item =list.get(position);
        holder.pilih_peraturan.setText(item.getPilihPeraturan());
        holder.nomor_peraturan.setText(item.getNomorPeraturan());
        holder.tgl_penetapan.setText(item.getTglPenetapan());
        holder.tgl_pengesahan.setText(item.getTglPengesahan());
        holder.nama_pejabat.setText(item.getNamaPejabat());
        holder.no_register.setText(item.getNoRegister());
        holder.tentang.setText(item.getTentang());
        holder.id_peraturan.setText(item.getIdPeraturan());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_item_details_peraturan);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                TextView txtPilihPeraturan,txtNomorPeraturan,txtTglPenetapan,txtTglPengesahan,txtNamaPejabat,txtNoRegiste,txtTentang,txtUploadPeraturan;
                ImageView imgClose;

                txtPilihPeraturan = dialog.findViewById(R.id.pilih_peraturan);
                txtNomorPeraturan = dialog.findViewById(R.id.nomor_peraturan);
                txtTglPenetapan = dialog.findViewById(R.id.tgl_penetapan);
                txtTglPengesahan = dialog.findViewById(R.id.tgl_pengesahan);
                txtNamaPejabat = dialog.findViewById(R.id.nama_pejabat_tanda_tangan);
                txtNoRegiste = dialog.findViewById(R.id.no_register);
                txtTentang = dialog.findViewById(R.id.tentang);
                txtUploadPeraturan = dialog.findViewById(R.id.upload_document);
                imgClose = dialog.findViewById(R.id.imgClose);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtPilihPeraturan.setText(item.getPilihPeraturan());
                txtNomorPeraturan.setText(item.getNomorPeraturan());
                txtTglPenetapan.setText(item.getTglPenetapan());
                txtTglPengesahan.setText(item.getTglPengesahan());
                txtNamaPejabat.setText(item.getNamaPejabat());
                txtNoRegiste.setText(item.getNoRegister());
                txtTentang.setText(item.getTentang());
                txtUploadPeraturan.setText(item.getUploadPeraturan());

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PeraturanViewHolder extends RecyclerView.ViewHolder{

        private TextView pilih_peraturan,nomor_peraturan,tgl_penetapan,tgl_pengesahan,nama_pejabat,no_register,tentang,id_peraturan,upload_document;
        Button buttonDetails;
        public PeraturanViewHolder(@NonNull View itemView) {
            super(itemView);

            pilih_peraturan = itemView.findViewById(R.id.pilih_peraturan);
            nomor_peraturan = itemView.findViewById(R.id.nomor_peraturan);
            tgl_penetapan = itemView.findViewById(R.id.tgl_penetapan);
            tgl_pengesahan = itemView.findViewById(R.id.tgl_pengesahan);
            nama_pejabat = itemView.findViewById(R.id.nama_pejabat_tanda_tangan);
            no_register = itemView.findViewById(R.id.no_register);
            tentang = itemView.findViewById(R.id.tentang);
            id_peraturan = itemView.findViewById(R.id.id_peraturan);
            upload_document = itemView.findViewById(R.id.upload_document);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
        }
    }
}
