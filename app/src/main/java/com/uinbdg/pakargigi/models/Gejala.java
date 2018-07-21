package com.uinbdg.pakargigi.models;

import java.io.Serializable;

public class Gejala implements Serializable {
    private String nama_gejala;
    private double nilaiCf;

    public Gejala(String nama_gejala, double nilaiCf) {
        this.nama_gejala = nama_gejala;
        this.nilaiCf = nilaiCf;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }

    public double getNilaiCf() {
        return nilaiCf;
    }

    public void setNilaiCf(double nilaiCf) {
        this.nilaiCf = nilaiCf;
    }

    @Override
    public String toString() {
        return "Gejala{" +
                "nama_gejala='" + nama_gejala + '\'' +
                ", nilaiCf=" + nilaiCf +
                '}';
    }
}
