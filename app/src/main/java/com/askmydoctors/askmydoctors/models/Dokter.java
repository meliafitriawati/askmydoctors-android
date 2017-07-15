package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Dokter {
    String username, nama, img, email, gender, hak_akses, nama_spesialisasi, tentang, pendidikan, nama_klinik, lokasi, kota;

    public Dokter(String username, String nama, String img, String email, String gender, String hak_akses, String nama_spesialisasi, String tentang, String pendidikan, String nama_klinik, String lokasi, String kota) {
        this.username = username;
        this.nama = nama;
        this.img = img;
        this.email = email;
        this.gender = gender;
        this.hak_akses = hak_akses;
        this.nama_spesialisasi = nama_spesialisasi;
        this.tentang = tentang;
        this.pendidikan = pendidikan;
        this.nama_klinik = nama_klinik;
        this.lokasi = lokasi;
        this.kota = kota;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHak_akses() {
        return hak_akses;
    }

    public void setHak_akses(String hak_akses) {
        this.hak_akses = hak_akses;
    }

    public String getNama_spesialisasi() {
        return nama_spesialisasi;
    }

    public void setNama_spesialisasi(String nama_spesialisasi) {
        this.nama_spesialisasi = nama_spesialisasi;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getNama_klinik() {
        return nama_klinik;
    }

    public void setNama_klinik(String nama_klinik) {
        this.nama_klinik = nama_klinik;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }


    public static Dokter fromJSONData(JSONObject json) {
        try {
            return new Dokter(json.getString("username"),
                    json.getString("fullname"),
                    json.getString("image"),
                    json.getString("email"),
                    json.getString("gender"),
                    json.getString("hak_akses"),
                    json.getString("nama_spesialisasi"),
                    json.getString("tentang"),
                    json.getString("pendidikan"),
                    json.getString("nama_klinik"),
                    json.getString("lokasi"),
                    json.getString("kota"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}
