package patientController;

import java.util.Date;



public class DoctorInfo extends PersonInfo{

	Date hireDate;
	SPECIALIZATION specialization;
	
	DoctorInfo(String fname, String lname, Date hire, SPECIALIZATION spec){
		super(fname,lname);
		this.hireDate = hire;
		this.specialization = spec;
	}
	
	@Override
	String getFirstName() {
		// TODO Auto-generated method stub
		return first_name;
	}

	@Override
	String getLastName() {
		// TODO Auto-generated method stub
		return last_name;
	}

	

}
