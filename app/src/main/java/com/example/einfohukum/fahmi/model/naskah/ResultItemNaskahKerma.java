package com.example.einfohukum.fahmi.model.naskah;

import com.google.gson.annotations.SerializedName;

public class ResultItemNaskahKerma {

	@SerializedName("para_pihak")
	private String paraPihak;

	@SerializedName("tentang")
	private String tentang;

	@SerializedName("nomor_naskah")
	private String nomorNaskah;

	@SerializedName("tgl_naskah")
	private String tglNaskah;

	@SerializedName("masa_berlaku")
	private String masaBerlaku;

	@SerializedName("id_naskah")
	private String idNaskah;

	@SerializedName("upload_document")
	private String uploadDocument;

	public String getUploadDocument() {
		return uploadDocument;
	}

	public String getParaPihak(){
		return paraPihak;
	}

	public String getTentang(){
		return tentang;
	}

	public String getNomorNaskah(){
		return nomorNaskah;
	}

	public String getTglNaskah(){
		return tglNaskah;
	}

	public String getMasaBerlaku(){
		return masaBerlaku;
	}

	public String getIdNaskah(){
		return idNaskah;
	}
}