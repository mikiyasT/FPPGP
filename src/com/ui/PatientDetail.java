package com.ui;


import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import patientController.PatientInfo;
import patientController.utility;

import com.util.ConnectMSSQLServer;

public class PatientDetail extends JFrame {
	
	public static enum Mode{New,Discharge,Deatil};

	public static Mode mode;
	protected static final JFrame Patient = null;
	private JPanel contentPane;
	
	PatientInfo patientInfo;
	private JTextField txt_patientLastName;
	ConnectMSSQLServer cms;
	
	String fname,lname,id,gender,datevisited,doctorassigned,diseasetype,age;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public PatientDetail(String patient_id) {
		

		cms = new ConnectMSSQLServer();
		//--------------------------------------------------------------
		
		//populate the patient informarion for that doctor
			//---------------------------------------------------------
		Statement statement;
		ResultSet rs = null;
		
		
		try {
			Connection con = cms.dbConnect();
			statement = con.createStatement();
			 String queryString = "select * from patient1 where id = '"+patient_id+"'";
			 System.out.println(queryString);
			 //String queryString = "select * from patient1 where doctorassigned = '102'" ;
			 rs = statement.executeQuery(queryString);
	         int cnt = 1;
	         System.out.println("Looing for patient with id  " + patient_id);
	         while(rs.next())
	         {
	        	 System.out.println( "----test------- result-------------");
	        	 String info = "Name " + rs.getString(1)+" \t "+rs.getString(2) +" \t Aeg "+rs.getString(3)+" \t Gender "+rs.getString(4);
	        	 System.out.println(info);
	        	 fname = "Full Name -> " + (String)rs.getString(1) + " " + (String)rs.getString(2);
	        	 id = "Id ->" + (String)rs.getString(3);
	        	 gender = "Gender -> " +(String)rs.getString(4);
	        	 datevisited = "Date Visited -> " + (String)rs.getString(5);
	        	 doctorassigned = "Docotr Assigned -> " + (String)rs.getString(6);
	        	 diseasetype = "Disease type -> " + (String)rs.getString(7);
	        	 age = "Age -> " + (String)rs.getString(8);

	         }
	        
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
		//patientInfo = new
		setTitle("Patient");
		setBounds(500, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel(id);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(100, 10, 250,25);
		contentPane.add(lblName);
		
		JLabel lblSex = new JLabel(gender);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSex.setBounds(100, 40, 250,25);
		contentPane.add(lblSex);
		
		JLabel lblfirstName = new JLabel(fname);
		lblfirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblfirstName.setBounds(100, 80, 250,25);
		contentPane.add(lblfirstName);
		JLabel lblAge = new JLabel(age);
		
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setBounds(100, 120, 250,25);
		contentPane.add(lblAge);
		
		JLabel lblAdmitDate = new JLabel(datevisited);
		
		lblAdmitDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdmitDate.setBounds(100, 160, 250,25);
		contentPane.add(lblAdmitDate);

		
		JLabel lblDoctor = new JLabel(doctorassigned);
	
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctor.setBounds(100, 200, 250,25);
		contentPane.add(lblDoctor);
		

		
		JLabel label = new JLabel(diseasetype);
	
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(100, 240, 250,25);
		contentPane.add(label);
	
		JButton btnPatientCommit = new JButton("Commit");
		btnPatientCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mode = Mode.Deatil;
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							
							//createPatient();
							addPatientToDb();
							PatientSearch frame = new PatientSearch();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					private void addPatientToDb() {
						try {
						Connection con = cms.dbConnect();
						Statement statement = con.createStatement();
						
						String queryString = "insert into patient values('"+patientInfo.getFirstName()+"','"+patientInfo.getLastName()+"','"
						+patientInfo.getId()+"','"+patientInfo.getGender()+"','"+patientInfo.getGender()+"','"+patientInfo.getGender()+"','"+patientInfo.getGender()+"','"+patientInfo.getGender()+"')";
				      
						
						statement.execute(queryString);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      
					}

				});
			}
		});
		btnPatientCommit.setBounds(113, 625, 117, 29);
		contentPane.add(btnPatientCommit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(300, 625, 117, 29);
		contentPane.add(btnCancel);
	
	}
}
