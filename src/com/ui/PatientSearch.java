package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import patientController.utility;

import com.util.ConnectMSSQLServer;

class PatientSearch
		extends 	JFrame
		implements	ActionListener,
					ListSelectionListener
 {
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JList		listbox;
	private	Vector		listData;
	//private	JButton		showButton;
	private	JButton		detailButton;
	private	JTextField	dataField;
	private	JScrollPane scrollPane;
	Vector<String> list_of_patient_id;
	ConnectMSSQLServer cms = new ConnectMSSQLServer();


	// Constructor of main frame
	public PatientSearch()
	{
		// Set the frame characteristics
		setTitle( "List of Patients" );
		setSize( 500, 600 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the data model for this example
		listData = new Vector();

		// Create a new listbox control
		listbox = new JList( listData );
		listbox.addListSelectionListener( this );

		// Add the listbox to a scrolling pane
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add( listbox );
		topPanel.add( scrollPane, BorderLayout.CENTER );

		CreateDataEntryPanel();
		
		//populate the patient informarion for that doctor
		//---------------------------------------------------------
		Connection con = cms.dbConnect();
		Statement statement;
		ResultSet rs = null;
		list_of_patient_id = new Vector<String>();
		
		
		try {
			statement = con.createStatement();
			 String queryString = "select * from patient1 where doctorassigned = "+"'"+utility.user_session+"'" ;
			 rs = statement.executeQuery(queryString);
	         int cnt = 1;
	         while(rs.next()){
	        	 System.out.println( "result-------------");
	        	 String info = "Name " + rs.getString(1)+" \t "+rs.getString(2) +" \t Id "+rs.getString(3)+" \t Gender "+rs.getString(4);
	        	 list_of_patient_id.add(rs.getString(3));
	        	 listData.addElement(info );
	 			 listbox.setListData( listData );
	 			 scrollPane.revalidate();
	 			 scrollPane.repaint();
	         }
	        
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}

	}


	public void CreateDataEntryPanel()
	{
		// Create a panel to hold all other components
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout( new BorderLayout() );
		topPanel.add( dataPanel, BorderLayout.SOUTH );

		detailButton = new JButton( "Detail" );
		dataPanel.add( detailButton, BorderLayout.EAST );
		detailButton.addActionListener( this );

		// Create a text field for data entry and display
		dataField = new JTextField();
		dataPanel.add( dataField, BorderLayout.CENTER );
	}

	// Handler for list selection changes
 	public void valueChanged( ListSelectionEvent event )
 	{
 		// See if this is a listbox selection and the
 		// event stream has settled
		if( event.getSource() == listbox
						&& !event.getValueIsAdjusting() )
		{
			// Get the current selection and place it in the
			// edit field
			String stringValue = (String)listbox.getSelectedValue();
			if( stringValue != null )
				dataField.setText( stringValue );
		}
 	}

	// Handler for button presses
	public void actionPerformed( ActionEvent event )
	{


		if( event.getSource() == detailButton )
		{
			// Get the current selection
			int selection = listbox.getSelectedIndex();
			String patient_id = (String)list_of_patient_id.get(selection);
			System.out.println("input patient id is " + patient_id);
			//if( selection >= 0 )
			//{
				JFrame patientDetail = new PatientDetail(patient_id);
				patientDetail.setVisible(true);

		}
	}

	// Main entry point for this example
	public static void main( String args[] )
	{
		// Create an instance of the test application
		listTest mainFrame	= new listTest();
		mainFrame.setVisible( true );
	}
}