package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.Fakultas;
import com.example.model.Mahasiswa;
import com.example.model.ProgramStudi;
import com.example.model.Universitas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Mahasiswa selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent(npm);
    }


    @Override
    public List<Mahasiswa> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


	@Override
	public ProgramStudi selectProdi (int id) 
	{
		log.info ("select student with id_prodi {}", id);
        return studentMapper.selectProdi (id);
	}


	@Override
	public Fakultas selectFakultas (int id) 
	{
		log.info ("select student with id_fakultas {}", id);
        return studentMapper.selectFakultas (id);
	}


	@Override
	public Universitas selectUniversitas (int id_univ) {
		log.info("select student with id_univ {}", id_univ);
		return studentMapper.selectUniversitas(id_univ);
	}


    @Override
    public void addStudent (Mahasiswa student)
    {
        studentMapper.addStudent(student);
    }


	@Override
	public String selectNpmStudents (String npm) {
		log.info("select student with npm {}", npm);
		return studentMapper.selectNpmStudents(npm);
	}

	@Override
	public void updateStudent(Mahasiswa mahasiswa) {
		// TODO Auto-generated method stub
		studentMapper.updateStudent(mahasiswa);
	}


	@Override
	public void updateStudentNpm(String npmAkhir, String npmMahasiswa) {
		// TODO Auto-generated method stub
		studentMapper.updateStudentNpm(npmAkhir, npmMahasiswa);
	}


	@Override
	public int selectLulusByTahun(String tahun_masuk, String nama_prodi, String nama_fakultas, String nama_univ) {
		// TODO Auto-generated method stub
		return studentMapper.selectLulusByTahun(tahun_masuk, nama_prodi, nama_fakultas, nama_univ);
	}


	@Override
	public int selectLulusByStatus(String tahun_masuk, String nama_prodi, String nama_fakultas, String nama_univ, String status) {
		// TODO Auto-generated method stub
		return studentMapper.selectLulusByStatus(tahun_masuk, nama_prodi, nama_fakultas, nama_univ, status);
	}


	@Override
	public List<Integer> selectStudentId() {
		// TODO Auto-generated method stub
		return studentMapper.selectStudentId();
	}


	@Override
	public List<Universitas> selectAllUniversity() {
		// TODO Auto-generated method stub
		return studentMapper.selectAllUniversity();
	}


	@Override
	public List<Fakultas> selectAllFacultyByIdUniv(int id_univ) {
		// TODO Auto-generated method stub
		return studentMapper.selectAllFacultyByIdUniv(id_univ);
	}


	@Override
	public List<ProgramStudi> selectAllProdiByIdFaculty(int id_fakultas) {
		// TODO Auto-generated method stub
		return studentMapper.selectAllProdiByIdFaculty(id_fakultas);
	}


	@Override
	public List<Mahasiswa> selectAllStudentByIdProdi(int id_univ, int id_fakultas, int id_prodi) {
		// TODO Auto-generated method stub
		return studentMapper.selectAllStudentByIdProdi(id_univ, id_fakultas, id_prodi);
	}


	@Override
	public ProgramStudi selectProdiName(int id_prodi) {
		// TODO Auto-generated method stub
		return studentMapper.selectProdiName(id_prodi);
	}

}
