package com.papsi.airlines;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class Destination extends JFrame {

	private JPanel contentPane;
	private double totalFare;
	private String destinationType;
	private String destination;
	private String destinationFrom;
	private String destinationTo;
	private int destinationID;
	private Database DB = new Database();
	private NumberFormat formatDecimal = new DecimalFormat("#,##0.00");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Destination frame = new Destination("local");
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
	public Destination(String destinationType) {
		getContentPane().setLayout(null);
		this.destinationType = destinationType; // Initialize destinationType
	if(destinationType.equalsIgnoreCase("local")) { //LOCAL DESTINATION WINDOW
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 303, 251);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel passengersOnBoard = new JLabel();
			passengersOnBoard.setFont(new Font("Tahoma", Font.PLAIN, 11));
			passengersOnBoard.setBounds(157, 109, 46, 14);
			contentPane.add(passengersOnBoard);
			
			JLabel lblNewLabel = new JLabel("Local Destinations");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(81, 11, 126, 25);
			contentPane.add(lblNewLabel);
			
			JLabel totalFarelbl = new JLabel();
			totalFarelbl.setBounds(157, 134, 121, 14);
			contentPane.add(totalFarelbl);
			totalFarelbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			JRadioButton manilaBatanes = new JRadioButton("Manila to Batanes");
			manilaBatanes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {	
					setDestinationID(1);
					setDestination(getDestinationID(), "Manila", "Batanes", 8000, 12500, 3500, totalFarelbl, passengersOnBoard);
				}
			});
			manilaBatanes.setFont(new Font("Tahoma", Font.PLAIN, 11));
			manilaBatanes.setBounds(10, 43, 111, 23);
			contentPane.add(manilaBatanes);
			
			JRadioButton batanesManila = new JRadioButton("Batanes to Manila");
			batanesManila.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(2);
					setDestination(getDestinationID(), "Batanes", "Manila", 9800, 12950, 3900, totalFarelbl, passengersOnBoard);
				}
			});
			batanesManila.setFont(new Font("Tahoma", Font.PLAIN, 11));
			batanesManila.setBounds(167, 43, 111, 23);
			contentPane.add(batanesManila);
			
			JRadioButton manilaPalawan = new JRadioButton("Manila to Palawan");
			manilaPalawan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(3);
					setDestination(getDestinationID(), "Manila", "Palawan", 9100, 10500, 3200, totalFarelbl, passengersOnBoard);
				}
			});
			manilaPalawan.setFont(new Font("Tahoma", Font.PLAIN, 11));
			manilaPalawan.setBounds(10, 69, 111, 23);
			contentPane.add(manilaPalawan);
			
			JRadioButton palawanManila = new JRadioButton("Palawan to Manila");
			palawanManila.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(4);
					setDestination(getDestinationID(), "Palawan", "Manila", 9850, 10975, 3575, totalFarelbl, passengersOnBoard);
				}
			});
			palawanManila.setFont(new Font("Tahoma", Font.PLAIN, 11));
			palawanManila.setBounds(167, 68, 111, 23);
			contentPane.add(palawanManila);
			
			JLabel lblNewLabel_1 = new JLabel("Passengers on board :");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_1.setBounds(10, 109, 126, 14);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Total Destination Fee :");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_2.setBounds(10, 134, 126, 14);
			contentPane.add(lblNewLabel_2);
			
			JButton btnNewButton = new JButton("Proceed");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {////////////
					if(capacityUnavailable(destinationID)) {
						JOptionPane.showMessageDialog(null, "Passenger/s will exceed maximum capacity!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						dispose();
						Transaction receipt = new Transaction();
						receipt.setVisible(true);
						receipt.setLocationRelativeTo(null);
					}
				}
			});
			btnNewButton.setBounds(188, 178, 89, 23);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Back");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setVisible(true);
					PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setLocationRelativeTo(null);
					dispose();
				}
			});
			btnNewButton_1.setBounds(10, 178, 89, 23);
			contentPane.add(btnNewButton_1);
			
			ButtonGroup group = new ButtonGroup(); //Grouping buttons 
			group.add(manilaBatanes);
			group.add(batanesManila);
			group.add(manilaPalawan);
			group.add(palawanManila);
		}
		else if(destinationType.equalsIgnoreCase("international")) { //INTERNATIONAL DESTINATION WINDOW
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 308, 268);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel passengersOnBoard = new JLabel();
					//String.valueOf(DB.getPassengersOnBoard(destinationID)) + "/" + String.valueOf(DB.getMaxCapacity()));
			passengersOnBoard.setFont(new Font("Tahoma", Font.PLAIN, 11));
			passengersOnBoard.setBounds(158, 132, 46, 14);
			contentPane.add(passengersOnBoard);
			
			JLabel totalFarelbl = new JLabel();
			totalFarelbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			totalFarelbl.setBounds(158, 157, 127, 14);
			contentPane.add(totalFarelbl);
			
			JLabel lblNewLabel_5 = new JLabel("International Destinations");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_5.setBounds(57, 11, 228, 14);
			contentPane.add(lblNewLabel_5);
			
			JRadioButton manilaSouthKorea = new JRadioButton("Manila to South Korea");
			manilaSouthKorea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(5);
					setDestination(getDestinationID(), "Manila", "South Korea", 27450, 37390, 12055, totalFarelbl, passengersOnBoard);
				}
			});
			manilaSouthKorea.setFont(new Font("Tahoma", Font.PLAIN, 11));
			manilaSouthKorea.setBounds(6, 32, 150, 23);
			contentPane.add(manilaSouthKorea);
			
			JRadioButton southKoreaManila = new JRadioButton("South Korea to Manila");
			southKoreaManila.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(6);
					setDestination(getDestinationID(), "South Korea", "Manila", 30890, 39650, 13100, totalFarelbl, passengersOnBoard);
				}
			});
			southKoreaManila.setFont(new Font("Tahoma", Font.PLAIN, 11));
			southKoreaManila.setBounds(158, 32, 131, 23);
			contentPane.add(southKoreaManila);
			
			JRadioButton manilaJapan = new JRadioButton("Manila to Japan");
			manilaJapan.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(7);
					setDestination(getDestinationID(), "Manila", "Japan", 40450, 45355, 27800, totalFarelbl, passengersOnBoard);
				}
			});
			manilaJapan.setFont(new Font("Tahoma", Font.PLAIN, 11));
			manilaJapan.setBounds(6, 58, 150, 23);
			contentPane.add(manilaJapan);
			
			JRadioButton japanManila = new JRadioButton("Japan to Manila");
			
			japanManila.setFont(new Font("Tahoma", Font.PLAIN, 11));
			japanManila.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(8);
					setDestination(getDestinationID(), "Japan", "Manila", 43855, 49780, 29400, totalFarelbl, passengersOnBoard);
				}
			});
			japanManila.setBounds(158, 58, 150, 23);
			contentPane.add(japanManila);
			
			JRadioButton manilaVietnam = new JRadioButton("Manila to Vietnam");
			manilaVietnam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(9);
					setDestination(getDestinationID(), "Manila", "Vietnam", 8505, 12345, 3200, totalFarelbl, passengersOnBoard);
				}
			});
			manilaVietnam.setFont(new Font("Tahoma", Font.PLAIN, 11));
			manilaVietnam.setBounds(6, 84, 150, 23);
			contentPane.add(manilaVietnam);
			
			JRadioButton vietnamManila = new JRadioButton("Vietnam to Manila");
			vietnamManila.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDestinationID(10);
					setDestination(getDestinationID(), "Vietnam", "Manila", 14300, 16320, 4600, totalFarelbl, passengersOnBoard);
				}
			});
			vietnamManila.setFont(new Font("Tahoma", Font.PLAIN, 11));
			vietnamManila.setBounds(158, 84, 127, 23);
			contentPane.add(vietnamManila);
			
			JLabel lblNewLabel_6 = new JLabel("Passengers on board :");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_6.setBounds(6, 132, 127, 14);
			contentPane.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Total destination fee :");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_7.setBounds(6, 157, 127, 14);
			contentPane.add(lblNewLabel_7);
			
			JButton btnNewButton_2 = new JButton("Proceed");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(capacityUnavailable(destinationID)) {
						JOptionPane.showMessageDialog(null, "Passenger/s will exceed maximum capacity!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						dispose();
						Transaction receipt = new Transaction();
						receipt.setVisible(true);
						receipt.setLocationRelativeTo(null);
					}
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnNewButton_2.setBounds(196, 200, 89, 23);
			contentPane.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("Back");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setVisible(true);
					PassengerList.passenger_list.get((PassengerList.passenger_list.size() - 1)).getAirplane().setLocationRelativeTo(null);
					dispose();
				}
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnNewButton_3.setBounds(6, 200, 89, 23);
			contentPane.add(btnNewButton_3);
			
			ButtonGroup group = new ButtonGroup(); //Grouping buttons
			group.add(manilaSouthKorea);
			group.add(southKoreaManila);
			group.add(manilaJapan);
			group.add(japanManila);
			group.add(manilaVietnam);
			group.add(vietnamManila);
		}
	}
	
	
	String checkAirplaneType() {
		for(Passenger x : PassengerList.passenger_list) {
			if(x.getAirplane() != null) {
				return x.getAirplane().getAirplaneType();
			}
		}
		return "nothing";
	}
	
	void computeTotalDestinationFee(String airplaneType, double privateFare, double businessFare, double regularFare) {
		this.totalFare = 0;
		if(airplaneType.equalsIgnoreCase("private")) {
			for(Passenger x : PassengerList.passenger_list) {
				if(x.getAge() > 59) {
					this.totalFare += (privateFare - (privateFare * .2));
				}
				else this.totalFare += privateFare;
			}
		}
		else if(airplaneType.equalsIgnoreCase("business")) {
			for(Passenger x : PassengerList.passenger_list) {
				if(x.getAge() > 59) {
					this.totalFare += (businessFare - (businessFare * .2));
				}
				else this.totalFare += businessFare;
			}
		}
		else if(airplaneType.equalsIgnoreCase("regular")) {
			for(Passenger x : PassengerList.passenger_list) {
				if(x.getAge() > 59) {
					this.totalFare += (regularFare - (regularFare * .2));
				}
				else this.totalFare += regularFare;
			}
		}
		else System.out.println("Type not initalized");	
	}
	
	public double getTotalFare() {
		return totalFare;
	}

	int getPassengersOnBoard(int destination_id) {
		Connection con;
		Database DB = new Database();
		int passengerCount = 0;
		try {
			con = DB.getConnection();
			Statement st = con.createStatement();
			String query = "SELECT passenger_on_board from capacity WHERE destination_id = "+ destination_id +" AND airplane_id = "+ DB.airplaneID() +";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				passengerCount = rs.getInt("passenger_on_board");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database Error : Destination ID");
		}
		return passengerCount;
	}
	
	public String getDestinationType() {
		return destinationType;
	}

	public void setDestination(int destinationID, String from, String to, double privateFare, double businessFare, double regularFare, JLabel fare, JLabel pob) {
		setDestination(from, to);
		computeTotalDestinationFee(checkAirplaneType(), privateFare, businessFare, regularFare);
		fare.setText("Php " + formatDecimal.format(totalFare));
		if(capacityUnavailable(destinationID)) {
			pob.setForeground(Color.RED);
		}
		else pob.setForeground(Color.BLACK);
		pob.setText(String.valueOf(DB.getPassengersOnBoard(destinationID)) + "/" + String.valueOf(DB.getMaxCapacity()));
	}
	
	public void setDestination(String from, String to) {
		this.destinationFrom = from;
		this.destinationTo = to;
		destination = from + " to " + to;
	}
	
	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public int getDestinationID() {
		return destinationID;
	}
	
	public String getDestinationFrom() {
		return destinationFrom;
	}
	
	public String getDestinationTo() {
		return destinationTo;
	}
	
	boolean capacityUnavailable(int destinationID) {
		if(DB.getPassengersOnBoard(destinationID) +  PassengerList.getPassengerCount() > DB.getMaxCapacity()) {
			return true;
		}
		return false;
	}
}

	
