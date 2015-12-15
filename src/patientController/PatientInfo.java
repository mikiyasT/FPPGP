package patientController;

import java.time.LocalDate;
import java.util.Date;

public class PatientInfo extends PersonInfo{
	
	String id;
	String gender;
	String visitDate;
	int age;
	
	String doc_assigned;
	String disease_info;
	
//	DoctorInfo doc_assigned;
//	String disease_info;
//	
	public String getId() {
		return id;
	}

	public String getGender() {
		return this.gender;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public int getAge() {
		return age;
	}

	public String getDoc_assigned() {
		return doc_assigned;
	}

	public String getDisease_info() {
		return disease_info;
	}

	public PatientInfo(String fname, String lname) {
		super(fname, lname);
		// TODO Auto-generated constructor stub
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setVisitDate(String ld) {
		this.visitDate = ld;
	}

	public void setDoc_assigned(String doc_assigned) {
		this.doc_assigned = doc_assigned;
	}

	public void setDisease_info(String disease_info) {
		this.disease_info = disease_info;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return first_name;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return last_name;
	}
	
	public int getAge(int age) {
		return this.age;
	}

	
}
