package com.uinbdg.pakargigi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataItemPenyakit implements Serializable{

    @SerializedName("penyakit")
    private String penyakit;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;


    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("id")
    private int id;

    @SerializedName("deleted_at")
    private Object deletedAt;

    @SerializedName("gejala")
    private List<DataItemGejala> gejala;

    public List<DataItemGejala> getGejala() {
        return gejala;
    }

    private double hasil_cf;

    public DataItemPenyakit() {
    }

    public DataItemPenyakit(String penyakit, List<DataItemGejala> gejalaList) {
        this.penyakit = penyakit;
        this.gejala = gejalaList;
    }

    public double getHasil_cf() {
        return hasil_cf;
    }

    public void setHasil_cf(double hasil_cf) {
        this.hasil_cf = hasil_cf;
    }

    public void setGejala(List<DataItemGejala> gejala) {
        this.gejala = gejala;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    @Override
    public String toString() {
        return
                "DataItemPenyakit{" +
                        "penyakit = '" + penyakit + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",deleted_at = '" + deletedAt + '\'' +
                        "}";
    }
}