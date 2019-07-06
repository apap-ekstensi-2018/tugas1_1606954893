package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor

public class Mahasiswa {

    public Mahasiswa(String npm, String nama, String tempat_lahir, String tanggal_lahir, int jenis_kelamin, String agama, String golongan_darah, String status, String tahun_masuk, String jalur_masuk, int id_prodi) {
        this.npm = npm;
        this.nama = nama;
        this.tempat_lahir = tempat_lahir;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.agama = agama;
        this.golongan_darah = golongan_darah;
        this.status = status;
        this.tahun_masuk = tahun_masuk;
        this.jalur_masuk = jalur_masuk;
        this.id_prodi = id_prodi;
    }

    private String npm;
    private String nama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private int jenis_kelamin;
    private String agama;
    private String golongan_darah;
    private String status;
    private String tahun_masuk;
    private String jalur_masuk;
    private int id_prodi;

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public int getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(int jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGolongan_darah() {
        return golongan_darah;
    }

    public void setGolongan_darah(String golongan_darah) {
        this.golongan_darah = golongan_darah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTahun_masuk() {
        return tahun_masuk;
    }

    public void setTahun_masuk(String tahun_masuk) {
        this.tahun_masuk = tahun_masuk;
    }

    public String getJalur_masuk() {
        return jalur_masuk;
    }

    public void setJalur_masuk(String jalur_masuk) {
        this.jalur_masuk = jalur_masuk;
    }

    public int getId_prodi() {
        return id_prodi;
    }

    public void setId_prodi(int id_prodi) {
        this.id_prodi = id_prodi;
    }
}



