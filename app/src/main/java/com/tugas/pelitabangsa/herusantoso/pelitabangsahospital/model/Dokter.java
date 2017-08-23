package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model;

/**
 * Created by santoso on 8/15/17.
 */

public class Dokter {
    private String kodeDokter;
    private String nama;
    private String telepon;
    private String spesialis;

    public String getKodeDokter() {
        return kodeDokter;
    }

    public void setKodeDokter(String kodeDokter) {
        this.kodeDokter = kodeDokter;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }
}
