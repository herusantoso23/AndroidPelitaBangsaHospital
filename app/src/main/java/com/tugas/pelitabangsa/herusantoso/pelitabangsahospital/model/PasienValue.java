package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class PasienValue {
    private String value;
    private String message;
    private List<Pasien> result;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pasien> getResult() {
        return result;
    }

    public void setResult(List<Pasien> result) {
        this.result = result;
    }
}
