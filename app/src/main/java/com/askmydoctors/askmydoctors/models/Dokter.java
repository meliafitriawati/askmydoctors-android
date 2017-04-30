package com.askmydoctors.askmydoctors.models;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Dokter {
    String id, nama, spesialisasi;

    public Dokter(String id, String nama, String spesialisasi) {
        this.id = id;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
}
