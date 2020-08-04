package com.example.einfohukum.fahmi.model.peraturan;

import com.google.gson.annotations.SerializedName;

public class ResultItemPeraturan {

	@SerializedName("tgl_pengesahan")
	private String tglPengesahan;

	@SerializedName("nomor_peraturan")
	private String nomorPeraturan;

	@SerializedName("tentang")
	private String tentang;

	@SerializedName("id_peraturan")
	private String idPeraturan;

	@SerializedName("nama_pejabat")
	private String namaPejabat;

	@SerializedName("pilih_peraturan")
	private String pilihPeraturan;

	@SerializedName("tgl_penetapan")
	private String tglPenetapan;

	@SerializedName("no_register")
	private String noRegister;

	@SerializedName("upload_peraturan")
	private String uploadPeraturan;

	public String getUploadPeraturan() {
		return uploadPeraturan;
	}

	public String getTglPengesahan(){
		return tglPengesahan;
	}

	public String getNomorPeraturan(){
		return nomorPeraturan;
	}

	public String getTentang(){
		return tentang;
	}

	public String getIdPeraturan(){
		return idPeraturan;
	}

	public String getNamaPejabat(){
		return namaPejabat;
	}

	public String getPilihPeraturan(){
		return pilihPeraturan;
	}

	public String getTglPenetapan(){
		return tglPenetapan;
	}

	public String getNoRegister(){
		return noRegister;
	}
}