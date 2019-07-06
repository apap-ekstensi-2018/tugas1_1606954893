package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProgramStudi {
	private int id;
	private String kode_prodi;
	private String nama_prodi;
	private int id_fakultas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode_prodi() {
		return kode_prodi;
	}

	public void setKode_prodi(String kode_prodi) {
		this.kode_prodi = kode_prodi;
	}

	public String getNama_prodi() {
		return nama_prodi;
	}

	public void setNama_prodi(String nama_prodi) {
		this.nama_prodi = nama_prodi;
	}

	public int getId_fakultas() {
		return id_fakultas;
	}

	public void setId_fakultas(int id_fakultas) {
		this.id_fakultas = id_fakultas;
	}
}


