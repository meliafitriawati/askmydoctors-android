package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Artikel {
    String id, judul, artikel, publisher, tanggal, views, img;

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

    public String getArtikel() {
        return artikel;
    }

    public void setArtikel(String artikel) {
        this.artikel = artikel;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Artikel(String id, String judul, String artikel, String publisher, String tanggal, String views, String img) {

        this.id = id;
        this.judul = judul;
        this.artikel = artikel;
        this.publisher = publisher;
        this.tanggal = tanggal;
        this.views = views;
        this.img = img;
    }

    public Artikel (String id, String judul, String img){
        this.id = id;
        this.judul = judul;
        this.img = img;
    }

    public static Artikel fromJSONData(JSONObject json) {
        try {
            return new Artikel(json.getString("id_artikel"),
                    json.getString("judul"),
                    json.getString("img_tumb"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}
