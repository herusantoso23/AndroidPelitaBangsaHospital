package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model;

/**
 * Created by santoso on 8/15/17.
 */

public class Pasien {
    private String kodePasien;
    private String nama;
    private String umur;
    private String alamat;

    public String getKodePasien() {
        return kodePasien;
    }

    public void setKodePasien(String kodePasien) {
        this.kodePasien = kodePasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
