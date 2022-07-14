package co.id.diulinken.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Datum {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("titik_koordinat")
    @Expose
    private String titikKoordinat;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTitikKoordinat() {
        return titikKoordinat;
    }

    public void setTitikKoordinat(String titikKoordinat) {
        this.titikKoordinat = titikKoordinat;
    }

}