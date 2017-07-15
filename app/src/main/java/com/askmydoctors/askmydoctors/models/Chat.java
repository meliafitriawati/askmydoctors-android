package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Chat {
    private String id, pengirim, penerima, stat, tanggal;

    public Chat(String id, String pengirim, String penerima, String tanggal, String stat  ) {
        this.id = id;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.stat = stat;
        this.tanggal = tanggal;
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

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public static Chat fromJSONData(JSONObject json) {
        try {
            return new Chat(json.getString("id_chat"),
                    json.getString("pengirim"),
                    json.getString("penerima"),
                    json.getString("tanggal"),
                    json.getString("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}
