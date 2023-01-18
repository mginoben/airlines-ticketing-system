package com.papsi.airlines;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class Transaction extends JFrame {

	private JPanel contentPane;
	private double totalBaggageFee = 0;
	private double totalTravelTaxCharge = 0;
	private double totalTravelInsurance = 0;
	private double totalDestinationFare = 0;
	private double baggageFee;
	private double travelTaxCharge;
	private double travelInsurance;
	private double total;
	private String airplaneType;
	private String destinationType;
	private String destination;
	private int totalPassenger;
	private Database DB = new Database();
	private NumberFormat formatDecimal = new DecimalFormat("#,##0.00");
	
	public Transaction() {
		this.airplaneType = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getAirplaneType(); //airplane type
		this.destinationType = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getDestination().getDestinationType(); //destination type
		this.destination = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getDestination().getDestination(); //destination
		this.totalPassenger = PassengerList.passenger_list.size(); //passenger count
		this.baggageFee = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getBaggageFee(); //basic baggage fee
		this.travelTaxCharge = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getTravelTaxCharge(); //basic travel tax charge
		this.travelInsurance = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getTravelInsurance(); //basic travel insurance
		this.totalDestinationFare = PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().getDestination().getTotalFare(); // total destination fare
		
		for(Passenger x : PassengerList.passenger_list) { //Iterate every passenger to check their age
			if(x.getAge() < 59) {
				this.totalBaggageFee += baggageFee;
				this.totalTravelInsurance += travelInsurance;
				this.totalTravelTaxCharge += travelTaxCharge;
			}
		}
		
		total = totalBaggageFee + totalTravelTaxCharge + totalTravelInsurance + totalDestinationFare; //Total
		
	//	System.out.println(totalPassenger + " " + totalBaggageFee + " " + totalTravelTaxCharge + " " + totalTravelInsurance + " " + totalDestinationFare);
		
	//SEND TO DATABASE
		DB.sendToDatabase(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transaction Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 191, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Receipt no :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 43, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Airplane type :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 68, 103, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Destination type :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 93, 103, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Destination :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 115, 103, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Baggage fee :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(306, 65, 103, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Travel tax charge :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(306, 90, 103, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Destination fare :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(306, 42, 103, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Insurance Fee :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(306, 115, 103, 14);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Passengers");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPassengerTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 178, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Book Again");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PassengerList.passenger_list.clear();
				dispose();
				Main mainWindow = new Main();
				mainWindow.setVisible(true);
				mainWindow.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(109, 178, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				dispose();
			}
		});
		btnNewButton_2.setBounds(208, 178, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel totallbl = new JLabel("Total :");
		totallbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		totallbl.setBounds(306, 151, 103, 21);
		contentPane.add(totallbl);
		
		JLabel lblNewLabel_10 = new JLabel("_______________________________________");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(306, 128, 251, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel receiptNo = new JLabel(String.valueOf(DB.receiptID(this)));
		receiptNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		receiptNo.setBounds(123, 43, 46, 14);
		contentPane.add(receiptNo);
		
		JLabel airplaneType = new JLabel(this.airplaneType);
		airplaneType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		airplaneType.setBounds(123, 68, 155, 14);
		contentPane.add(airplaneType);
		
		JLabel destinationType = new JLabel(this.destinationType);
		destinationType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		destinationType.setBounds(123, 93, 155, 14);
		contentPane.add(destinationType);
		
		JLabel destination = new JLabel(this.destination);
		destination.setFont(new Font("Tahoma", Font.PLAIN, 11));
		destination.setBounds(123, 115, 173, 14);
		contentPane.add(destination);
		
		JLabel destinationFare_total = new JLabel("Php " + String.valueOf(formatDecimal.format(totalDestinationFare)));
		destinationFare_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		destinationFare_total.setBounds(419, 43, 94, 14);
		contentPane.add(destinationFare_total);
		
		JLabel baggageFee_total = new JLabel("Php " + String.valueOf(formatDecimal.format(totalBaggageFee)));
		baggageFee_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		baggageFee_total.setBounds(419, 65, 94, 14);
		contentPane.add(baggageFee_total);
		
		JLabel travelTaxCharge_total = new JLabel("Php " + String.valueOf(formatDecimal.format(totalTravelTaxCharge)));
		travelTaxCharge_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		travelTaxCharge_total.setBounds(419, 90, 94, 14);
		contentPane.add(travelTaxCharge_total);
		
		JLabel insuranceFee_total = new JLabel("Php " + String.valueOf(formatDecimal.format(totalTravelInsurance)));
		insuranceFee_total.setFont(new Font("Tahoma", Font.PLAIN, 11));
		insuranceFee_total.setBounds(419, 115, 94, 14);
		contentPane.add(insuranceFee_total);
		
		JLabel total = new JLabel("Php " + String.valueOf(formatDecimal.format(this.total)));
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setBounds(419, 151, 155, 21);
		contentPane.add(total);
	}

	public void showPassengerTable() {
		String[] columns = {"#", "First Name", "Middle Name", "Last Name", "Age"};
		String[][] rows = new String[10][5];
		
		for(int x = 0; x < PassengerList.passenger_list.size(); x++) {
				rows[x][0] = String.valueOf((x + 1));
				rows[x][1] = PassengerList.passenger_list.get(x).getFirstname();
				rows[x][2] = PassengerList.passenger_list.get(x).getMiddlename();
				rows[x][3] = PassengerList.passenger_list.get(x).getLastname();
				rows[x][4] = String.valueOf(PassengerList.passenger_list.get(x).getAge());
		}
			
			JTable passengerTable = new JTable(rows, columns);
			TableColumnModel columnModel = passengerTable.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(20);
			columnModel.getColumn(1).setPreferredWidth(180);
			columnModel.getColumn(2).setPreferredWidth(180);
			columnModel.getColumn(3).setPreferredWidth(180);
			columnModel.getColumn(4).setPreferredWidth(32);
		//	JOptionPane.showMessageDialog(null, new JScrollPane(passengerTable), "Passengers Table", JOptionPane.PLAIN_MESSAGE);
			
			JScrollPane scrollpane = new JScrollPane(passengerTable);
			scrollpane.setPreferredSize(new Dimension(615, 150));
			
		    
		    JOptionPane.showMessageDialog(null, scrollpane, "Passenger Table",  
		                                              JOptionPane.PLAIN_MESSAGE);
	}
	
	public double getTotalBaggageFee() {
		return totalBaggageFee;
	}

	public double getTotalTravelTaxCharge() {
		return totalTravelTaxCharge;
	}

	public double getTotalTravelInsurance() {
		return totalTravelInsurance;
	}

	public double getTotalDestinationFare() {
		return totalDestinationFare;
	}

	public int getTotalPassenger() {
		return totalPassenger;
	}

	
}
