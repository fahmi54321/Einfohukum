package com.example.einfohukum.fahmi.model.psh;

import com.google.gson.annotations.SerializedName;

public class ResultItemPsh {

	@SerializedName("pangkat_nrp")
	private String pangkatNrp;

	@SerializedName("nama")
	private String nama;

	@SerializedName("perkara")
	private String perkara;

	@SerializedName("id_psh")
	private String idPsh;

	@SerializedName("nomor_psh")
	private String nomorPsh;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("kesatuan")
	private String kesatuan;

	@SerializedName("nrp")
	private String nrp;

	@SerializedName("pasal_dilanggar")
	private String pasalDilanggar;

	@SerializedName("photo_vonis")
	private String photoVonis;

	public String getPhotoVonis(){
		return photoVonis;
	}

	public String getPangkatNrp(){
		return pangkatNrp;
	}

	public String getNama(){
		return nama;
	}

	public String getPerkara(){
		return perkara;
	}

	public String getIdPsh(){
		return idPsh;
	}

	public String getNomorPsh(){
		return nomorPsh;
	}

	public String getTanggal(){
		return tanggal;
	}

	public String getKesatuan(){
		return kesatuan;
	}

	public String getNrp(){
		return nrp;
	}

	public String getPasalDilanggar(){
		return pasalDilanggar;
	}
}