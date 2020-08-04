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
import com.example.einfohukum.fahmi.model.naskah.ResultItemNaskahKerma;
import java.util.List;
import java.util.Objects;

public class AdapterNaskahKerma extends RecyclerView.Adapter<AdapterNaskahKerma.NaskahKermaViewHolder> {

    private Context context;
    private List<ResultItemNaskahKerma> list;

    public AdapterNaskahKerma(Context context, List<ResultItemNaskahKerma> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterNaskahKerma.NaskahKermaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.item_get_naskah,null);
        return new NaskahKermaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNaskahKerma.NaskahKermaViewHolder holder, int position) {
        final ResultItemNaskahKerma item =list.get(position);
        holder.nomor_naskah.setText(item.getNomorNaskah());
        holder.tgl_naskah.setText(item.getTglNaskah());
        holder.para_pihak.setText(item.getParaPihak());
        holder.tentang.setText(item.getTentang());
        holder.masa_berlaku.setText(item.getMasaBerlaku());
        holder.id_naskah.setText(item.getIdNaskah());
        holder.upload_document.setText(item.getUploadDocument());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_item_details_naskah);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                TextView txtNomorNaskah,txtTglNaskah,txtParaPihak,txtTentang,txtMasaBerlaku,txtUploadDocument;
                ImageView imgClose;

                txtNomorNaskah = dialog.findViewById(R.id.nomor_naskah);
                txtTglNaskah = dialog.findViewById(R.id.tgl_naskah);
                txtParaPihak = dialog.findViewById(R.id.para_pihak);
                txtTentang = dialog.findViewById(R.id.tentang);
                txtMasaBerlaku = dialog.findViewById(R.id.masa_berlaku);
                txtUploadDocument = dialog.findViewById(R.id.upload_document);
                imgClose = dialog.findViewById(R.id.imgClose);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtNomorNaskah.setText(item.getNomorNaskah());
                txtTglNaskah.setText(item.getTglNaskah());
                txtParaPihak.setText(item.getParaPihak());
                txtTentang.setText(item.getTentang());
                txtMasaBerlaku.setText(item.getMasaBerlaku());
                txtUploadDocument.setText(item.getUploadDocument());

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NaskahKermaViewHolder extends RecyclerView.ViewHolder{

        private TextView nomor_naskah,tgl_naskah,para_pihak,tentang,masa_berlaku,id_naskah,upload_document;
        Button buttonDetails;
        public NaskahKermaViewHolder(@NonNull View itemView) {
            super(itemView);

            nomor_naskah = itemView.findViewById(R.id.nomor_naskah);
            tgl_naskah = itemView.findViewById(R.id.tgl_naskah);
            para_pihak = itemView.findViewById(R.id.para_pihak);
            tentang = itemView.findViewById(R.id.tentang);
            masa_berlaku = itemView.findViewById(R.id.masa_berlaku);
            id_naskah = itemView.findViewById(R.id.id_naskah);
            upload_document = itemView.findViewById(R.id.upload_document);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
        }
    }
}
