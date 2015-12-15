package patientController;

import java.util.Date;



public class ReceptionInfo extends PersonInfo{

	Date hireDate;
	SHIFT shift;
	
	ReceptionInfo(String fname, String lname, int age,Date hire, SHIFT shift){
		super(fname,lname);
		this.hireDate = hire;
		this.shift = shift;
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
