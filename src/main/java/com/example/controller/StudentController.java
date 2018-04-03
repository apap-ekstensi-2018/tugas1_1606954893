package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Fakultas;
import com.example.model.Mahasiswa;
import com.example.model.ProgramStudi;
import com.example.model.Universitas;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;
    
    private String npmMahasiswa;
    private int id_univ;
    private int id_fakultas;
    private int id_prodi;
    
    @RequestMapping("/")
    public String index (){
        return "home";
    }
    
    @RequestMapping("/mahasiswa")
    public String mahasiswa(@RequestParam(value = "npm", required = false) String npm, Model model) {
    	Mahasiswa student = studentDAO.selectStudent(npm);
        if (student != null) {
        	ProgramStudi prodi = studentDAO.selectProdi(student.getId_prodi());
        	Fakultas fakultas = studentDAO.selectFakultas(prodi.getId_fakultas());
        	Universitas univ = studentDAO.selectUniversitas(fakultas.getId_univ());
            model.addAttribute ("student", student);
            model.addAttribute ("prodi", prodi);
            model.addAttribute ("fakultas", fakultas);
            model.addAttribute ("univ", univ);
            return "tampil-mahasiswa";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
        
    }
    
    @RequestMapping("/mahasiswa/tambah")
    public String tambah (Model model)
    {	
    	List<Integer> idStudent = studentDAO.selectStudentId();
    	model.addAttribute("idStudent", idStudent);
    	return "tambah-mahasiswa";
    }
    
    @RequestMapping(value = "/mahasiswa/tambah", method = RequestMethod.POST)
    public String tambahUpdate(Model model, @RequestParam(value = "nama", required = false) String nama,
    						 @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
    						 @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
    						 @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
    						 @RequestParam(value = "agama", required = false) String agama,
    						 @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
    						 @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
    						 @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
    						 @RequestParam(value = "id_prodi", required = false) int id_prodi) 
    {
    	ProgramStudi prodi = studentDAO.selectProdi(id_prodi);
    	Fakultas fakultas = studentDAO.selectFakultas(prodi.getId_fakultas());
    	Universitas univ = studentDAO.selectUniversitas(fakultas.getId_univ());
    	    	
    	String npmTahunFinal = tahun_masuk.substring(2, 4);
    	String npmKodeUnivProdi = "" + univ.getKode_univ() + prodi.getKode_prodi();
    	String jalurMasuk = "";
    	if (jalur_masuk.equals("Undangan Reguler/SNMPTN")) {
    		jalurMasuk = "54";
    	} else if (jalur_masuk.equals("Ujian Tulis Mandiri")){
    		jalurMasuk = "62";
    	} else if (jalur_masuk.equals("Undangan Paralel/PPKB")) {
    		jalurMasuk = "55";
    	} else if (jalur_masuk.equals("Ujian Tulis Bersama/SBMPTN")) {
    		jalurMasuk = "57";
    	} else {
    		jalurMasuk = "53";
    	}
       	String npmAkhir = npmTahunFinal + npmKodeUnivProdi + jalurMasuk;
       	String cekNpm = studentDAO.selectNpmStudents(npmAkhir);
        if (cekNpm == null) {
        	npmAkhir = npmAkhir + "001";
        } else {
        	cekNpm = "" + (Integer.parseInt(cekNpm) + 1);
        	if (cekNpm.length() == 1) {
        		npmAkhir = npmAkhir + "00" + cekNpm; 
        	} else if (cekNpm.length() == 2) {
        		npmAkhir = npmAkhir + "0" + cekNpm;
        	} else {
        		npmAkhir = npmAkhir + cekNpm;
        	}
        }
        
       	Mahasiswa mahasiswa = new Mahasiswa (npmAkhir, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, "Aktif", tahun_masuk, jalur_masuk, id_prodi);
       	studentDAO.addStudent(mahasiswa);
       	model.addAttribute("mahasiswa", mahasiswa);
       	model.addAttribute("message", "Mahasiswa dengan NPM " + npmAkhir + " berhasil ditambahkan");
       	return "success-add";
    }
    
    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String updateMahasiswa(@PathVariable(value = "npm") String npm, Model model) {
    	Mahasiswa student = studentDAO.selectStudent(npm);
    	List<Integer> idStudent = studentDAO.selectStudentId();
    	if (student != null) {
    		npmMahasiswa = npm;
    		model.addAttribute("student", student);
    		model.addAttribute("idStudent", idStudent);
    		return "form-update";
    	}
    	else {
    		model.addAttribute("npm", npm);
    		return "not-found";
    	}
    }
    
    @RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
    public String updateMahasiswa (Model model, @RequestParam(value = "nama", required = false) String nama,
											    @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
											    @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
											    @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
											    @RequestParam(value = "agama", required = false) String agama,
											    @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
											    @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
											    @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
											    @RequestParam(value = "id_prodi", required = false) int id_prodi)
    {
    	ProgramStudi prodi = studentDAO.selectProdi(id_prodi);
    	Fakultas fakultas = studentDAO.selectFakultas(prodi.getId_fakultas());
    	Universitas univ = studentDAO.selectUniversitas(fakultas.getId_univ());
    	    	
    	String npmTahunFinal = tahun_masuk.substring(2, 4);
    	String npmKodeUnivProdi = "" + univ.getKode_univ() + prodi.getKode_prodi();
    	String jalurMasuk = "";
    	if (jalur_masuk.equals("Undangan Reguler/SNMPTN")) {
    		jalurMasuk = "54";
    	} else if (jalur_masuk.equals("Ujian Tulis Mandiri")){
    		jalurMasuk = "62";
    	} else if (jalur_masuk.equals("Undangan Paralel/PPKB")) {
    		jalurMasuk = "55";
    	} else if (jalur_masuk.equals("Ujian Tulis Bersama/SBMPTN")) {
    		jalurMasuk = "57";
    	} else {
    		jalurMasuk = "53";
    	}
       	String npmAkhir = npmTahunFinal + npmKodeUnivProdi + jalurMasuk;
       	String cekNpm = studentDAO.selectNpmStudents(npmAkhir);
        if (cekNpm == null) {
        	npmAkhir = npmAkhir + "001";
        } else {
        	cekNpm = "" + (Integer.parseInt(cekNpm) + 1);
        	if (cekNpm.length() == 1) {
        		npmAkhir = npmAkhir + "00" + cekNpm; 
        	} else if (cekNpm.length() == 2) {
        		npmAkhir = npmAkhir + "0" + cekNpm;
        	} else {
        		npmAkhir = npmAkhir + cekNpm;
        	}
        }
        Mahasiswa student = studentDAO.selectStudent(npmMahasiswa);
       	Mahasiswa mahasiswa = new Mahasiswa (npmMahasiswa, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, student.getStatus(), tahun_masuk, jalur_masuk, id_prodi);
       	studentDAO.updateStudent(mahasiswa);
       	studentDAO.updateStudentNpm(npmAkhir, npmMahasiswa);
       	model.addAttribute("mahasiswa", mahasiswa);
       	model.addAttribute("message", "Mahasiswa dengan NPM " + npmAkhir + " berhasil diubah");
       	return "success-update"; 	
    }
    
    @RequestMapping("/kelulusan")
    public String tampilKelulusanFinal(Model model, @RequestParam(value = "thn", required = false) String tahun_masuk,
    								   				@RequestParam(value = "prodi", required = false) String id_prodi) 
    {
    	if (tahun_masuk != null && id_prodi != null) { 
    		int id = Integer.parseInt(id_prodi);
	    	ProgramStudi prodi = studentDAO.selectProdi(id);
	    	Fakultas fakultas = studentDAO.selectFakultas(prodi.getId_fakultas());
	    	Universitas univ = studentDAO.selectUniversitas(fakultas.getId_univ());
	    	
	    	int lulusByTahunMasuk = studentDAO.selectLulusByTahun(tahun_masuk, prodi.getNama_prodi(), fakultas.getNama_fakultas(), univ.getNama_univ());
	    	int lulusByStatus = studentDAO.selectLulusByStatus(tahun_masuk, prodi.getNama_prodi(), fakultas.getNama_fakultas(), univ.getNama_univ(), "Lulus");
	    	model.addAttribute("tahun_masuk", tahun_masuk);
	    	model.addAttribute("prodi", prodi);
	    	model.addAttribute("fakultas", fakultas);
	    	model.addAttribute("univ", univ);
	    	int hasil = Math.round((float)lulusByStatus/(float)lulusByTahunMasuk * (float)100);
	    	model.addAttribute("lulusByTahunMasuk", lulusByTahunMasuk);
	    	model.addAttribute("lulusByStatus", lulusByStatus);
	    	model.addAttribute("hasil", hasil);
    	}
    	else {
    		List<Integer> idStudent = studentDAO.selectStudentId();
        	model.addAttribute("idStudent", idStudent);
        	return "kelulusan-mahasiswa";
    	}
    	return "tampil-kelulusan";
    }
    
    @RequestMapping("/mahasiswa/cari")
    public String tampilCariUniv(Model model, @RequestParam(value = "univ" , required = false) String univ,
    										  @RequestParam(value = "fakultas" , required = false) String fakultas,
    										  @RequestParam(value = "prodi" , required = false) String prodi)
    {
    	if (univ != null && fakultas == null) {
    		id_univ = Integer.parseInt(univ);
    		Universitas idUniversitas = studentDAO.selectUniversitas(id_univ);
    		List<Fakultas> fak = studentDAO.selectAllFacultyByIdUniv(id_univ);
    		model.addAttribute("idUniversitas", idUniversitas);
    		model.addAttribute("fak", fak);
    		return "cari-mahasiswa-fakultas";
    	}
    	else {
    		if (univ != null && fakultas != null && prodi == null) {
        		id_fakultas = Integer.parseInt(fakultas);
        		Universitas idUniversitas = studentDAO.selectUniversitas(id_univ);
        		Fakultas idFakultas = studentDAO.selectFakultas(id_fakultas);
        		List<ProgramStudi> programStudi = studentDAO.selectAllProdiByIdFaculty(id_fakultas);
        		model.addAttribute("idUniversitas", idUniversitas);
        		model.addAttribute("idFakultas", idFakultas);
        		model.addAttribute("programStudi", programStudi);
        		return "cari-mahasiswa-prodi";
        	}
    		else if(univ != null && fakultas != null && prodi != null) {
    			id_univ = Integer.parseInt(univ);
    			id_fakultas = Integer.parseInt(fakultas);
    			id_prodi = Integer.parseInt(prodi);
    			ProgramStudi prodiName = studentDAO.selectProdiName(id_prodi);
        		List<Mahasiswa> student = studentDAO.selectAllStudentByIdProdi(id_univ, id_fakultas, id_prodi);
        		model.addAttribute("prodiName", prodiName);
        		model.addAttribute("student", student);
        		return "tampil-mahasiswa-dataTables";
    		}
    		else {
    			List<Universitas> universitas = studentDAO.selectAllUniversity();
    	    	model.addAttribute("universitas", universitas);
    	    	return "cari-mahasiswa-univ";
    		}
	    	
    	}
    	
    }
}
