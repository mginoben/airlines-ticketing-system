package com.papsi.airlines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;


public class Database {
	public static final String USERNAME = "root";
	public static final String PASSWORD = "";
	public static final String CONN = "jdbc:mysql://localhost/airlines?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private Connection con;
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
	}
	
	public Database() {
		 try {
			con = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void sendToDatabase(Transaction receipt) {
		//INSERT Receipt
		insertReceipt(receipt);
						
		//INSERT Passengers
		insertPassengers(receipt);
				
		//UPDATE Capacity
		updateCapacity();
	}
	
	void insertReceipt(Transaction receipt) {
		String insert_receipt = "INSERT INTO receipts(airplane_id, destination_id, baggage_fee, travel_tax_charge, destination_fee, travel_insurance_fee) VALUES(?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement add = con.prepareStatement(insert_receipt);
				add.setInt(1, airplaneID());
				add.setInt(2, destinationID());
				add.setDouble(3, receipt.getTotalBaggageFee());
				add.setDouble(4, receipt.getTotalTravelTaxCharge());
				add.setDouble(5, receipt.getTotalDestinationFare());
				add.setDouble(6, receipt.getTotalTravelInsurance());
				add.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Database Error: Receipts");
			e.printStackTrace();
		}
	}

	void insertPassengers(Transaction receipt) {
		String insert_passenger = "INSERT INTO passengers(firstname, middlename, lastname, age, receipt_id) VALUES(?, ?, ?, ?, ?);";
		try {
			PreparedStatement add = con.prepareStatement(insert_passenger);
			for(Passenger x: PassengerList.passenger_list) {
				add.setString(1, x.getFirstname());
				add.setString(2, x.getMiddlename());
				add.setString(3, x.getLastname());
				add.setInt(4, x.getAge());
				add.setInt(5, receiptID(receipt));
				add.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.println("Database Error: Passengers");
		}
	}
	
	void updateCapacity() {
		try {
			String update_capacity = "UPDATE capacity SET passenger_on_board = ? WHERE airplane_id = ? AND destination_id = ?;";
			PreparedStatement add = con.prepareStatement(update_capacity);
			add.setInt(1, (PassengerList.passenger_list.size() + getPassengersOnBoard(destinationID())));
			add.setInt(2, airplaneID());
			add.setInt(3, destinationID());
			add.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	int airplaneID() {
		int id = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM airplanes WHERE type = '"+ 
			PassengerList.passenger_list.get(PassengerList.passenger_list.size() - 1).getAirplane().getAirplaneType() 
			+"';";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 id = rs.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("Database Error: airplane ID");
			//e.printStackTrace();
		}
		return id;
	}

	int destinationID() {
		int id = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM destinations WHERE type = '"+ 
					PassengerList.passenger_list.get(PassengerList.passenger_list.size() - 1).getAirplane().getDestination().getDestinationType() +
					"' AND from_this = '"+ 
					PassengerList.passenger_list.get(PassengerList.passenger_list.size() - 1).getAirplane().getDestination().getDestinationFrom() +
					"' AND to_this = '"+ 
					PassengerList.passenger_list.get(PassengerList.passenger_list.size() - 1).getAirplane().getDestination().getDestinationTo() +"';";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database Error : Destination ID");
			//e.printStackTrace();
		}
		return id;
	}

	int receiptID(Transaction receipt) {
		int id = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM receipts WHERE " +
					"airplane_id = '" +
					airplaneID() +
					"' AND destination_id = '"+
					destinationID() +
					"' AND baggage_fee = '"+ 
					receipt.getTotalBaggageFee() +
					"' AND travel_tax_charge = '"+ 
					receipt.getTotalTravelTaxCharge() +
					"' AND destination_fee = '"+ 
					receipt.getTotalDestinationFare() +
					"' AND travel_insurance_fee = '"+
					receipt.getTotalTravelInsurance() + "';";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 id = rs.getInt("receipt_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database Error : Receipts ID");
			//e.printStackTrace();
		}
		return id;
	}
	
	int getPassengersOnBoard(int destinationID) {
		int passengers = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT passenger_on_board FROM capacity WHERE airplane_id = "+ airplaneID() +" AND destination_id = "+ destinationID +";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 passengers = rs.getInt("passenger_on_board");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengers;
	}
	
	int getPassengersOnBoard(int destinationID, int airplaneID) {
		int passengers = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT passenger_on_board FROM capacity WHERE airplane_id = "+ airplaneID +" AND destination_id = "+ destinationID +";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 passengers = rs.getInt("passenger_on_board");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengers;
	}

	int getMaxCapacity() {
		int max = 0;
		try {
			Statement st = con.createStatement();
			String query = "SELECT * FROM capacity WHERE airplane_id = "+ airplaneID() +" AND destination_id = "+ destinationID() +";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) { 
				 max = rs.getInt("maximum_capacity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}
}
