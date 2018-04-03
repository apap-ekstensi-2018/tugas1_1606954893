package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.Fakultas;
import com.example.model.Mahasiswa;
import com.example.model.ProgramStudi;
import com.example.model.Universitas;

public interface StudentService
{
	Mahasiswa selectStudent (String npm);

	List<Mahasiswa> selectAllStudents();
	
	ProgramStudi selectProdi (int id);
	
	Fakultas selectFakultas (int id);
	
	Universitas selectUniversitas (int id);
	
	void addStudent (Mahasiswa student);
	
	String selectNpmStudents (String npm);
	
	void updateStudent(Mahasiswa mahasiswa);
	
	void updateStudentNpm(String npmAkhir, String npmMahasiswa);
	
	int selectLulusByTahun(String tahun_masuk, String nama_prodi, String nama_fakultas, String nama_univ);
	
	int selectLulusByStatus(String tahun_masuk, String nama_prodi, String nama_fakultas, String nama_univ, String status);	
	
	List<Integer> selectStudentId();
	
	List<Universitas> selectAllUniversity();
	
	List<Fakultas> selectAllFacultyByIdUniv(int id_univ);
	
	List<ProgramStudi> selectAllProdiByIdFaculty(int id_fakultas);
	
	List<Mahasiswa> selectAllStudentByIdProdi(int id_univ, int id_fakultas, int id_prodi);
	
	ProgramStudi selectProdiName(int id_prodi);
	
}
