package patientController;

import java.util.Date;

public abstract class PersonInfo {
	String first_name;
	String last_name;
	
	
	PersonInfo(String fname, String lname){
		this.first_name = fname;
		this.last_name = lname;
		
	}
	
	abstract String getFirstName();
	abstract String getLastName();
	
	
}
