package com.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import patientController.utility;

import com.util.ConnectMSSQLServer;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	LoginForm lform;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	ButtonGroup bG;
	ConnectMSSQLServer cms = new ConnectMSSQLServer();
	boolean login_status;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setBounds(6, 65, 119, 28);
		contentPane.add(lblUser);
		
		JLabel lblPas = new JLabel("Password:");
		lblPas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPas.setBounds(6, 105, 119, 28);
		contentPane.add(lblPas);
		
		JLabel lblImA = new JLabel("I'm a:");
		lblImA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImA.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImA.setBounds(57, 144, 68, 28);
		contentPane.add(lblImA);
		
		userName = new JTextField();
		userName.setBounds(175, 70, 155, 20);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(175, 110, 155, 20);
		contentPane.add(password);
		
		final JRadioButton rdDoc = new JRadioButton("Doctor");
		rdDoc.setActionCommand("Doctor");
	//	buttonGroup.add(rdDoc);
		rdDoc.setBounds(175, 148, 109, 23);
		contentPane.add(rdDoc);
		
		final JRadioButton rdbtnReception = new JRadioButton("Reception");
		rdbtnReception.setActionCommand("Reception");
	//	buttonGroup.add(rdbtnReception);
		rdbtnReception.setBounds(175, 175, 109, 23);
		contentPane.add(rdbtnReception);
		
		bG = new ButtonGroup();
		bG.add(rdDoc);
		bG.add(rdbtnReception);
		
		JButton btnRLogin = new JButton("Login");
		btnRLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if(bG.getSelection() == null)
				{
					JOptionPane.showMessageDialog(contentPane, "Please select if you are a doctor or a reception.");
					//return;
				}else{
					try{					
						
					String type = bG.getSelection().getActionCommand();
										
					if(type.equals("Doctor")){
						utility.isDoctor = true;
					}else{
						utility.isDoctor = false;
					}
					String user = userName.getText();
					utility.user_session = user;
					String pass = password.getText();
					System.out.println("Type : "+type+" Username :"+user+" Password :"+pass);
					Connection con = cms.dbConnect();
					Statement statement = con.createStatement();
			        String queryString = "select * from login where type='"+type+"' and username='"+user+"' and password ='"+pass+"' and status ='A'";
			       
			         ResultSet rs = statement.executeQuery(queryString);
			      
			        
			         if (rs.next()) 
			         {
			           System.out.println( "result-------------");
			        	 System.out.println("Type -> "+rs.getString(1)+": Username ->"+rs.getString(2)+": Password ->"+rs.getString(3)+": Status -> "+rs.getString(4));
			            if(rs.getString(2).equals(user) && rs.getString(3).equals(pass)){
			            	JOptionPane.showMessageDialog(contentPane, "Successfuly logged in as " + rs.getString(1));
			            	login_status = true;
			            }
			            
			         }else
			         {
			        	 JOptionPane.showMessageDialog(contentPane, "Invalid username or password.");
			        	//LoginForm log =  new LoginForm();
			        	 userName.setText("");
			        	 password.setText("");
			        	 setVisible(true);
			        	 
			         }
			        
					}catch ( SQLException ex) { 
						ex.printStackTrace();
					}
				}
				if(login_status)
				{	
					dispose();
					Patient.mode = Patient.Mode.New;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Selection frame = new Selection();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});	
				}
			}
		});
		btnRLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRLogin.setBounds(175, 228, 89, 23);
		contentPane.add(btnRLogin);
	}
}
