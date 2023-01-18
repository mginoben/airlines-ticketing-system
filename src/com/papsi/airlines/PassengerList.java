package com.papsi.airlines;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassengerList extends JFrame {

	private JPanel contentPane;
	static private int passengerCount;
	static List<Passenger> passenger_list = new ArrayList<Passenger>();
	
	static int getPassengerCount() {
		return passengerCount;
	}		

	/**
	 * Create the frame.
	 */
	public PassengerList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 236, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please enter how many passengers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(25, 11, 195, 14);
		contentPane.add(lblNewLabel);
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
		JSpinner count_spinner = new JSpinner(model);
		count_spinner.setBounds(74, 36, 71, 20);
		contentPane.add(count_spinner);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					count_spinner.commitEdit();
				} 
				catch ( java.text.ParseException e ) { 
					System.out.println("Error Found");
				}
			//CHECK IF COUNT = 0	
				if((Integer) count_spinner.getValue() == 0) {
					JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if((Integer) count_spinner.getValue() > 10) {
					JOptionPane.showMessageDialog(null, "Must not exceed 10 passengers!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					passengerCount = (Integer) count_spinner.getValue();
					dispose();
					Passenger passenger = new Passenger();
					passenger.setVisible(true);
					passenger.setLocationRelativeTo(null);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(121, 113, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Maximum of 10 passengers");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(45, 67, 149, 14);
		contentPane.add(lblNewLabel_1);
	}
}
