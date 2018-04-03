package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.Fakultas;
import com.example.model.Mahasiswa;
import com.example.model.ProgramStudi;
import com.example.model.Universitas;

@Mapper
public interface StudentMapper
{	
	@Select("SELECT * FROM mahasiswa WHERE npm = #{npm}")
    Mahasiswa selectStudent (@Param("npm") String npm);
		
	@Select("SELECT * FROM mahasiswa")
    List<Mahasiswa> selectAllStudents ();
	
    @Select("SELECT * FROM program_studi WHERE id = #{id}")
    ProgramStudi selectProdi (@Param("id") int id);
    
    @Select("SELECT * FROM fakultas WHERE id = #{id}")
    Fakultas selectFakultas (@Param("id") int id);
    
    @Select("SELECT * FROM universitas WHERE id = #{id}")
    Universitas selectUniversitas (@Param("id") int id);
    
    @Select("SELECT * FROM `universitas`")
    List<Universitas> selectAllUniversity();
    
    @Select("SELECT substring(npm, 10,3) FROM mahasiswa WHERE npm LIKE '${npm}%' ORDER BY substring(npm, 10, 3) DESC LIMIT 1")
    String selectNpmStudents(@Param("npm")String npm);
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void addStudent(Mahasiswa mahasiswa);
    
    @Update("UPDATE mahasiswa SET nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah}, status = #{status}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} WHERE npm = #{npm}")
    void updateStudent(Mahasiswa mahasiswa);
    
    @Update("UPDATE mahasiswa SET npm = #{npmAkhir} WHERE npm = #{npmMahasiswa}")
    void updateStudentNpm(@Param(value = "npmAkhir")String npmAkhir,
    					  @Param(value = "npmMahasiswa")String npmMahasiswa);
    
    @Select("SELECT COUNT(npm) FROM mahasiswa m, program_studi ps, fakultas f, universitas u WHERE m.id_prodi = ps.id AND ps.id_fakultas = f.id AND f.id_univ = u.id AND m.tahun_masuk = '${tahun_masuk}' AND ps.nama_prodi = #{nama_prodi} AND f.nama_fakultas = #{nama_fakultas} AND u.nama_univ = #{nama_univ}")
    int selectLulusByTahun(@Param(value = "tahun_masuk")String tahun_masuk,
    					   @Param(value = "nama_prodi")String nama_prodi,
    					   @Param(value = "nama_fakultas")String nama_fakultas,
    					   @Param(value = "nama_univ")String nama_univ);
    
    @Select("SELECT COUNT(npm) FROM mahasiswa m, program_studi ps, fakultas f, universitas u WHERE m.id_prodi = ps.id AND ps.id_fakultas = f.id AND f.id_univ = u.id AND m.tahun_masuk = '${tahun_masuk}' AND ps.nama_prodi = #{nama_prodi} AND f.nama_fakultas = #{nama_fakultas} AND u.nama_univ = #{nama_univ} AND m.status = '${status}'")
    int selectLulusByStatus(@Param(value = "tahun_masuk")String tahun_masuk,
    					    @Param(value = "nama_prodi")String nama_prodi,
    					    @Param(value = "nama_fakultas")String nama_fakultas,
    					    @Param(value = "nama_univ")String nama_univ,
    					    @Param(value = "status")String status);
    
    @Select("SELECT DISTINCT id_prodi FROM `mahasiswa`")
    List<Integer> selectStudentId();
    
    @Select("SELECT * FROM `fakultas` WHERE id_univ = #{id_univ}")
    List<Fakultas> selectAllFacultyByIdUniv(@Param("id_univ") int id_univ);
    
    @Select("SELECT * FROM `program_studi` WHERE id_fakultas = #{id_fakultas}")
    List<ProgramStudi> selectAllProdiByIdFaculty(@Param("id_fakultas") int id_fakultas);
    
    @Select("SELECT mahasiswa.npm, mahasiswa.nama, mahasiswa.tempat_lahir, mahasiswa.tanggal_lahir, mahasiswa.jenis_kelamin,  mahasiswa.agama, mahasiswa.golongan_darah, mahasiswa.status, mahasiswa.tahun_masuk, mahasiswa.jalur_masuk, mahasiswa.id_prodi FROM mahasiswa, program_studi, fakultas, universitas WHERE mahasiswa.id_prodi = program_studi.id AND program_studi.id_fakultas = fakultas.id AND fakultas.id_univ = universitas.id AND universitas.id = #{id_univ} AND fakultas.id = #{id_fakultas} AND program_studi.id = #{id_prodi}")
    List<Mahasiswa> selectAllStudentByIdProdi(@Param("id_univ") int id_univ, 
    										  @Param("id_fakultas") int id_fakultas,
    										  @Param("id_prodi") int id_prodi);
    
    @Select("SELECT * FROM `program_studi` WHERE id = #{id_prodi}")
    ProgramStudi selectProdiName(@Param("id_prodi") int id_prodi);
}
