package com.uinbdg.pakargigi.models;

import com.google.gson.annotations.SerializedName;

public class DataItemResult {

	@SerializedName("penyakit")
	private DataItemPenyakit penyakit;

	@SerializedName("gejala")
	private DataItemGejala gejala;

	@SerializedName("cf")
	private double cf;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_gejala")
	private int idGejala;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("id_penyakit")
	private int idPenyakit;

	public DataItemGejala getGejala() {
		return gejala;
	}

	public void setGejala(DataItemGejala gejala) {
		this.gejala = gejala;
	}

	double jumlah;

	public double getJumlah() {
		return jumlah;
	}

	public void setJumlah(double jumlah) {
		this.jumlah = jumlah;
	}

	public void setPenyakit(DataItemPenyakit penyakit){
		this.penyakit = penyakit;
	}

	public DataItemPenyakit getPenyakit(){
		return penyakit;
	}

	public void setCf(double cf){
		this.cf = cf;
	}

	public double getCf(){
		return cf;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdGejala(int idGejala){
		this.idGejala = idGejala;
	}

	public int getIdGejala(){
		return idGejala;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setIdPenyakit(int idPenyakit){
		this.idPenyakit = idPenyakit;
	}

	public int getIdPenyakit(){
		return idPenyakit;
	}

	@Override
 	public String toString(){
		return 
			"DataItemResult{" +
			"penyakit = '" + penyakit + '\'' + 
			",cf = '" + cf + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id_gejala = '" + idGejala + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",id_penyakit = '" + idPenyakit + '\'' + 
			"}";
		}
}