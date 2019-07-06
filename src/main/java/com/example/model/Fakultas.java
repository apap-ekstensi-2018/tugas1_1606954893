package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Fakultas {
	private int id;
	private String kode_fakultas;
	private String nama_fakultas;
	private int id_univ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode_fakultas() {
		return kode_fakultas;
	}

	public void setKode_fakultas(String kode_fakultas) {
		this.kode_fakultas = kode_fakultas;
	}

	public String getNama_fakultas() {
		return nama_fakultas;
	}

	public void setNama_fakultas(String nama_fakultas) {
		this.nama_fakultas = nama_fakultas;
	}

	public int getId_univ() {
		return id_univ;
	}

	public void setId_univ(int id_univ) {
		this.id_univ = id_univ;
	}
}


