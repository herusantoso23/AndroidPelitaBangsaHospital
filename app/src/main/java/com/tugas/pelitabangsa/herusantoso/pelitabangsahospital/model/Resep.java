package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model;

/**
 * Created by santoso on 8/15/17.
 */

public class Resep {
    static String id;
    private String nomor;
    private String kodeObat;
    private String nama;
    private String jenis;

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getKodeObat() {
        return kodeObat;
    }

    public void setKodeObat(String kodeObat) {
        this.kodeObat = kodeObat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Resep.id = id;
    }
}
