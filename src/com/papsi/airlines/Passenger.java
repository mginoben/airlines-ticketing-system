package com.papsi.airlines;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Passenger extends JFrame {

	private JPanel contentPane;
	private JTextField firstname_field;
	private JTextField middlename_field;
	private JTextField lastname_field;
	private JTextField age_field;
	private String firstname;
	private String middlename;
	private String lastname;
	private int age;
	private JLabel lblNewLabel;
	private JLabel lblMiddleName;
	private JLabel lblLastName;
	private JLabel lblAge;
	private JLabel lblNewLabel_1;
	private Airplane airplane;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Airplane getAirplane() {
		return this.airplane;
	}
	
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passenger frame = new Passenger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Passenger(String firstname, String middlename, String lastname, int age) {
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.age = age;
	}
	
	public Passenger() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstname_field = new JTextField();
		firstname_field.setBounds(107, 47, 171, 20);
		contentPane.add(firstname_field);
		firstname_field.setColumns(10);
		
		middlename_field = new JTextField();
		middlename_field.setColumns(10);
		middlename_field.setBounds(107, 72, 171, 20);
		contentPane.add(middlename_field);
		
		lastname_field = new JTextField();
		lastname_field.setColumns(10);
		lastname_field.setBounds(107, 97, 171, 20);
		contentPane.add(lastname_field);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstname;
				String middlename;
				String lastname;
				int age;
			//THIS IS THE PART TO CHECK	
				if(checkDetails()) {
					if(Integer.parseInt(age_field.getText()) > 123) {
						JOptionPane.showMessageDialog(null, "Age must not exceed 123 years old!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						firstname = firstname_field.getText();
						middlename = middlename_field.getText();
						lastname = lastname_field.getText();
						age = Integer.parseInt(age_field.getText());
						
						Passenger passenger = new Passenger(firstname, middlename, lastname, age);
						PassengerList.passenger_list.add(passenger);
						
						if(PassengerList.passenger_list.size() < PassengerList.getPassengerCount()){
							dispose();
							passenger = new Passenger();
							passenger.setVisible(true);
							passenger.setLocationRelativeTo(null);
						}
						else {
							PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).setAirplane(new Airplane());
							PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setVisible(true);
							PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setLocationRelativeTo(null);
							dispose();
						}
					}
					
				}
				else JOptionPane.showMessageDialog(null, "Please input necessary details", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		submit.setBounds(99, 168, 89, 23);
		contentPane.add(submit);
		
		age_field = new JTextField();
		age_field.setBounds(107, 122, 46, 20);
		contentPane.add(age_field);
		age_field.setColumns(10);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(11, 50, 86, 14);
		contentPane.add(lblNewLabel);
		
		lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMiddleName.setBounds(11, 75, 86, 14);
		contentPane.add(lblMiddleName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLastName.setBounds(11, 100, 86, 14);
		contentPane.add(lblLastName);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAge.setBounds(11, 125, 46, 14);
		contentPane.add(lblAge);
		
		lblNewLabel_1 = new JLabel("Please enter passenger "+ (PassengerList.passenger_list.size()+1) +" details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(66, 11, 212, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	boolean checkDetails() {
		boolean noError = true;
		//Patter check if there are symbols or numbers in a string
		Pattern lettersOnly = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		
		String firstname = firstname_field.getText();
		String middlename = middlename_field.getText();
		String lastname = lastname_field.getText();
		
		Matcher firstname_error = lettersOnly.matcher(firstname);
		Matcher middlename_error = lettersOnly.matcher(middlename);
		Matcher lastname_error = lettersOnly.matcher(lastname);
		
		//if error found then errorFound = true
		boolean errorFound;
		errorFound = firstname_error.find();
		if(errorFound || firstname.isEmpty() || firstname.matches("[ -]*")) {
			noError = false;
			return noError;
		}
		
		errorFound = middlename_error.find();
		if(errorFound || middlename.isEmpty() || middlename.matches("[ -]*")) {
			noError = false;
			return noError;
		}
		
		errorFound = lastname_error.find();
		if(errorFound || lastname.isEmpty() || lastname.matches("[ -]*")) {
			noError = false;
			return noError;
		}
		
		
		//AGE
		try {
			age = Integer.parseInt(age_field.getText());
		}
		catch(NumberFormatException e) {
			noError = false;
			return noError;
		}
		
		return noError;
	}
}
