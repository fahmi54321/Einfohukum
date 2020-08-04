package com.example.einfohukum.fahmi.model.kke;

import com.google.gson.annotations.SerializedName;

public class ResultItemKke {

	@SerializedName("tgl_sidang")
	private String tglSidang;

	@SerializedName("no_laporan")
	private String noLaporan;

	@SerializedName("nama_pelapor")
	private String namaPelapor;

	@SerializedName("tgl_laporan")
	private String tglLaporan;

	@SerializedName("no_sidang")
	private String noSidang;

	@SerializedName("jabatan")
	private String jabatan;

	@SerializedName("kesatuan")
	private String kesatuan;

	@SerializedName("nrp")
	private String nrp;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama_pelaku")
	private String namaPelaku;

	@SerializedName("pangkat_nrp")
	private String pangkatNrp;

	@SerializedName("id_kke")
	private String idKke;

	@SerializedName("nomor_psh")
	private String nomorPsh;

	@SerializedName("isi_putusan")
	private String isiPutusan;

	@SerializedName("pasal_dilanggar")
	private String pasalDilanggar;

	@SerializedName("isi_banding")
	private String isiBanding;

	@SerializedName("photo_vonis")
	private String photoVonis;

	@SerializedName("photo_putusan")
	private String photoPutusan;

	@SerializedName("photo_surat")
	private String photoSurat;

	public String getPhotoVonis() {
		return photoVonis;
	}

	public String getPhotoPutusan() {
		return photoPutusan;
	}

	public String getPhotoSurat() {
		return photoSurat;
	}

	public String getTglSidang(){
		return tglSidang;
	}

	public String getNoLaporan(){
		return noLaporan;
	}

	public String getNamaPelapor(){
		return namaPelapor;
	}

	public String getTglLaporan(){
		return tglLaporan;
	}

	public String getNoSidang(){
		return noSidang;
	}

	public String getJabatan(){
		return jabatan;
	}

	public String getKesatuan(){
		return kesatuan;
	}

	public String getNrp(){
		return nrp;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getNamaPelaku(){
		return namaPelaku;
	}

	public String getPangkatNrp(){
		return pangkatNrp;
	}

	public String getIdKke(){
		return idKke;
	}

	public String getNomorPsh(){
		return nomorPsh;
	}

	public String getIsiPutusan(){
		return isiPutusan;
	}

	public String getPasalDilanggar(){
		return pasalDilanggar;
	}

	public String getIsiBanding(){
		return isiBanding;
	}
}