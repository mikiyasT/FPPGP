package com.ui;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import patientController.PatientInfo;

import com.util.ConnectMSSQLServer;

public class Patient extends JFrame {
	
	public static enum Mode{New,Discharge,Deatil};

	public static Mode mode;
	protected static final JFrame Patient = null;
	private JPanel contentPane;
	private JTextField txt_patientID;
	private JTextField txt_patientGender;
	private JTextField txt_patientFirstName;
	private JTextField txt_Disease;
	private JTextField textField_1;
	private JTextField txt_dob;
	private JTextField txt_admissionDate;
	PatientInfo patientInfo;
	private JTextField txt_patientLastName;
	ConnectMSSQLServer cms;
	
	
	JRadioButton rdbtnMale;
	JRadioButton rdbtnFemale;
	ButtonGroup bG;
	JComboBox lst_doctorsName;

	
	/**
	 * Create the frame.
	 */
	public Patient() {
		
		cms = new ConnectMSSQLServer();
		//patientInfo = new
		setTitle("Patient");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(250, 0, 650, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Patient ID:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(10, 16, 177, 14);
		contentPane.add(lblName);
		
		JLabel lblSex = new JLabel("Gender:");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSex.setBounds(0, 55, 177, 14);
		contentPane.add(lblSex);
		
		JLabel firstName = new JLabel("First Name:");
		firstName.setHorizontalAlignment(SwingConstants.RIGHT);
		firstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		firstName.setBounds(10, 91, 177, 14);
		contentPane.add(firstName);

		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setBounds(10, 165, 177, 14);
		contentPane.add(lblAge);
		
		JLabel lblAdmitDate = new JLabel("Admit Date:");
		lblAdmitDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmitDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdmitDate.setBounds(10, 202, 177, 14);
		contentPane.add(lblAdmitDate);
	
		JLabel lblDoctor = new JLabel("Doctor Name:");
		lblDoctor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctor.setBounds(10, 276, 177, 14);
		contentPane.add(lblDoctor);
	
		JLabel label = new JLabel("Diease:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 325, 177, 14);
		contentPane.add(label);
		
		txt_patientID = new JTextField();
		txt_patientID.setBounds(197, 11, 268, 26);
		contentPane.add(txt_patientID);
		txt_patientID.setColumns(10);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setActionCommand("Male");
		rdbtnMale.setBounds(200, 50, 109, 25);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setActionCommand("Female");
		rdbtnFemale.setBounds(320, 50, 109, 25);
		contentPane.add(rdbtnFemale);
		
		bG = new ButtonGroup();
		bG.add(rdbtnMale);
		bG.add(rdbtnFemale);
		
		
		txt_patientFirstName = new JTextField();
		txt_patientFirstName.setColumns(10);
		txt_patientFirstName.setBounds(197, 86, 268, 26);
		contentPane.add(txt_patientFirstName);
		
		txt_Disease = new JTextField();
		txt_Disease.setBounds(199, 314, 274, 95);
		contentPane.add(txt_Disease);
		txt_Disease.setColumns(10);

		
		JButton btnPatientCommit = new JButton("Commit");
		btnPatientCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mode = Mode.Deatil;
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							
							createPatient();
							addPatientToDb();
							setVisible(true);
							clearData();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					private void clearData() {
						// TODO Auto-generated method stub
						txt_admissionDate.setText("");
						txt_Disease.setText("");
						txt_dob.setText("");
						txt_patientFirstName.setText("");
						txt_patientID.setText("");
						txt_patientLastName.setText("");
						
					}

					private void addPatientToDb() {
						try {
						Connection con = cms.dbConnect();
						Statement statement = con.createStatement();
						
			       
						String queryString = "insert into patient1 values('"+patientInfo.getFirstName()+"','"+patientInfo.getLastName()+"','"
								+patientInfo.getId()+"','"+patientInfo.getGender()+"','"+patientInfo.getVisitDate()+"','"+patientInfo.getDoc_assigned()+"','"+patientInfo.getDisease_info()+"','"+patientInfo.getAge()+"')";
						
						
						statement.executeUpdate(queryString);
							JOptionPane.showMessageDialog(contentPane, "Patient details added successfully");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      
						
					}
					
					private void createPatient() {
						patientInfo = new PatientInfo(txt_patientFirstName.getText(),txt_patientLastName.getText());
						patientInfo.setDisease_info(txt_Disease.getText());
						patientInfo.setAge(Integer.parseInt((txt_dob.getText())));
						patientInfo.setId(txt_patientID.getText());
						patientInfo.setGender(bG.getSelection().getActionCommand());
						patientInfo.setVisitDate(txt_admissionDate.getText());
						String doct = (String)lst_doctorsName.getSelectedItem();
						patientInfo.setDoc_assigned(doct);
						
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
		
		txt_dob = new JTextField();
		txt_dob.setColumns(10);
		txt_dob.setBounds(197, 160, 108, 26);
		contentPane.add(txt_dob);
		
		txt_admissionDate = new JTextField();
		txt_admissionDate.setColumns(10);
		txt_admissionDate.setBounds(199, 197, 218, 26);
		contentPane.add(txt_admissionDate);
	
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_admissionDate.setText(new DatePicker(Patient).setPickedDate());
			}
		});
		btnNewButton.setBounds(419, 197, 56, 26);
		contentPane.add(btnNewButton);
		
		lst_doctorsName = new JComboBox();
		lst_doctorsName.setBounds(203, 276, 136, 20);
		lst_doctorsName.addItem("miki");
		lst_doctorsName.addItem("dave");
		lst_doctorsName.addItem("shank");
		
		contentPane.add(lst_doctorsName);
		
		JLabel lastName = new JLabel("Last Name:");
		lastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lastName.setBounds(10, 128, 177, 14);
		contentPane.add(lastName);
		
		txt_patientLastName = new JTextField();
		txt_patientLastName.setColumns(10);
		txt_patientLastName.setBounds(197, 123, 268, 26);
		contentPane.add(txt_patientLastName);
		
		


	}
}
