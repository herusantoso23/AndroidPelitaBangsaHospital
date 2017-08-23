package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class AdminValue {
    private String value;
    private String message;
    private List<Admin> result;

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

    public List<Admin> getResult() {
        return result;
    }

    public void setResult(List<Admin> result) {
        this.result = result;
    }
}
