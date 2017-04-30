package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/3/2017.
 */

public class Pertanyaan {
    String id, judul, pertanyaan, spesialisasi, tanggal, pengirim;

    public Pertanyaan(String id, String judul, String pertanyaan, String tanggal) {
        this.id = id;
        this.judul = judul;
        this.pertanyaan = pertanyaan;
        this.tanggal = tanggal;
    }

    public Pertanyaan(String id, String judul, String pertanyaan, String spesialisasi, String tanggal, String pengirim) {
        this.id = id;
        this.judul = judul;
        this.pertanyaan = pertanyaan;
        this.spesialisasi = spesialisasi;
        this.tanggal = tanggal;
        this.pengirim = pengirim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public static Pertanyaan fromJSONData(JSONObject json) {
        try {
            return new Pertanyaan(json.getString("id_diskusi"),
                    json.getString("judul"),
                    json.getString("pertanyaan"),
                    json.getString("waktu_kirim"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}

