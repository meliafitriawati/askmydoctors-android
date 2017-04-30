package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/27/2017.
 */

public class Komentar {
    String id_komentar, id_diskusi, pengirim, komentar, waktu, kode_pengirim;

    public Komentar(String id_komentar, String id_diskusi, String pengirim, String komentar, String waktu, String kode_pengirim) {
        this.id_komentar = id_komentar;
        this.id_diskusi = id_diskusi;
        this.pengirim = pengirim;
        this.komentar = komentar;
        this.waktu = waktu;
        this.kode_pengirim = kode_pengirim;
    }

    public String getId_komentar() {
        return id_komentar;
    }

    public void setId_komentar(String id_komentar) {
        this.id_komentar = id_komentar;
    }

    public String getId_diskusi() {
        return id_diskusi;
    }

    public void setId_diskusi(String id_diskusi) {
        this.id_diskusi = id_diskusi;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKode_pengirim() {
        return kode_pengirim;
    }

    public void setKode_pengirim(String kode_pengirim) {
        this.kode_pengirim = kode_pengirim;
    }

    public static Komentar fromJSONData(JSONObject json) {
        try {
            return new Komentar(json.getString("id_komentar"),
                    json.getString("id_diskusi"),
                    json.getString("pengirim"),
                    json.getString("komentar"),
                    json.getString("waktu_kirim"),
                    json.getString("kode_pengirim"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}
