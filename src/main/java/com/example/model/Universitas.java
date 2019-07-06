package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Universitas {
	private int id;
	private String kode_univ;
	private String nama_univ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode_univ() {
		return kode_univ;
	}

	public void setKode_univ(String kode_univ) {
		this.kode_univ = kode_univ;
	}

	public String getNama_univ() {
		return nama_univ;
	}

	public void setNama_univ(String nama_univ) {
		this.nama_univ = nama_univ;
	}
}
