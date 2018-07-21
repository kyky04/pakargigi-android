package com.uinbdg.pakargigi.models;

public class JumlahPenyakit {
    String namaPenyakit;
    int Jumlah;

    public JumlahPenyakit(String namaPenyakit, int jumlah) {
        this.namaPenyakit = namaPenyakit;
        Jumlah = jumlah;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public int getJumlah() {
        return Jumlah;
    }

    public void setJumlah(int jumlah) {
        Jumlah = jumlah;
    }
}
