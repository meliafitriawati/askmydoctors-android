package com.askmydoctors.askmydoctors.models;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Chat {
    private String id, pengirim, detail, tanggal, image, spesialisasi;

    public Chat(String id, String pengirim, String detail, String tanggal, String image, String spesialisasi) {
        this.id = id;
        this.pengirim = pengirim;
        this.detail = detail;
        this.tanggal = tanggal;
        this.image = image;
        this.spesialisasi = spesialisasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }
}
