package com.uinbdg.pakargigi.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItemGejala implements Serializable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("mb")
	private int mb;

	@SerializedName("md")
	private int md;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("gejala")
	private String gejala;


	private double nilaiCf;

	public DataItemGejala(String nama_gejala, double nilaiCf) {
		this.gejala = nama_gejala;
		this.nilaiCf = nilaiCf;
	}

	public DataItemGejala() {
	}

	public String getNama_gejala() {
		return gejala;
	}

	public void setNama_gejala(String nama_gejala) {
		this.gejala = nama_gejala;
	}

	public double getNilaiCf() {
		return nilaiCf;
	}

	public void setNilaiCf(double nilaiCf) {
		this.nilaiCf = nilaiCf;
	}

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setMb(int mb){
		this.mb = mb;
	}

	public int getMb(){
		return mb;
	}

	public void setMd(int md){
		this.md = md;
	}

	public int getMd(){
		return md;
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

	public void setGejala(String gejala){
		this.gejala = gejala;
	}

	public String getGejala(){
		return gejala;
	}

	@Override
 	public String toString(){
		return 
			"DataItemGejala{" +
			"updated_at = '" + updatedAt + '\'' + 
			",mb = '" + mb + '\'' + 
			",md = '" + md + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",gejala = '" + gejala + '\'' + 
			"}";
		}


}