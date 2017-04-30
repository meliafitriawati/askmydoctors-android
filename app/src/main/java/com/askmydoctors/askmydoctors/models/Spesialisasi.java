package com.askmydoctors.askmydoctors.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class Spesialisasi {
    String id, spesialisasi;

    public Spesialisasi(String id, String spesialisasi) {
        this.id = id;
        this.spesialisasi = spesialisasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public static Spesialisasi fromJSONData(JSONObject json) {
        try {
            return new Spesialisasi(json.getString("id"),
                    json.getString("nama_spesialisasi"));
        } catch (JSONException e) {
            e.printStackTrace();
        }return null;
    }
}
