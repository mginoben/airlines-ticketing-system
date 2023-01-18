package com.papsi.airlines;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class Airplane extends JFrame{
	private String airplaneType;
	private int pilot;
	private int assistantPilot;
	private int stewardess;
	private int capacity;
	private double travelTaxCharge;
	private double baggageFee;
	private double travelInsurance = 0;
	private double processingFee;
	private Destination destinationWindow;
	private JPanel contentPane;
	private NumberFormat formatDecimal = new DecimalFormat("#,##0.00");
	
	public Airplane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 175, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose airplane type :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(25, 11, 134, 14);
		getContentPane().add(lblNewLabel);
		
		JButton private_btn = new JButton("Private");
		private_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Initialize Values
				airplaneType = "Private";
				pilot = 2;
				assistantPilot = 0;
				stewardess = 4;
				capacity = 20;
				travelTaxCharge = 4260;
				baggageFee = 1250;
				
				//Show Airplane Details
				
				if(showAirplaneDetails() == 1) {//proceed
					promptTravelInsurance(4500);
					dispose();
					proceedToDestination();	
				}
			}
		});
		private_btn.setBounds(35, 36, 89, 23);
		getContentPane().add(private_btn);
		
		JButton business_btn = new JButton("Business");
		business_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				airplaneType = "Business";
				pilot = 2;
				assistantPilot = 1;
				stewardess = 4;
				capacity = 30;
				travelTaxCharge = 5700;
				baggageFee = 2850;
				
				//Show Airplane Details
				
				if(showAirplaneDetails() == 1) {//proceed
					promptTravelInsurance(6500);
					dispose();
					proceedToDestination();	
				}
			}
		});
		business_btn.setBounds(35, 70, 89, 23);
		getContentPane().add(business_btn);
		
		JButton regular_btn = new JButton("Regular");
		regular_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				airplaneType = "Regular";
				pilot = 2;
				assistantPilot = 2;
				stewardess = 8;
				capacity = 100;
				travelTaxCharge = 2500;
				baggageFee = 950;
				
				//Show Airplane Details
			
				if(showAirplaneDetails() == 1) {//proceed
					promptTravelInsurance(950);
					dispose();
					proceedToDestination();	
				}
			}
		});
		regular_btn.setBounds(35, 104, 89, 23);
		getContentPane().add(regular_btn);
		
		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.setBounds(35, 138, 89, 23);
		getContentPane().add(cancel_btn);
	}
	
	int showAirplaneDetails() {
		String[] options = {"Close", "Proceed"};
		int airplaneOption = JOptionPane.showOptionDialog(null, "Type : " + airplaneType + "\nPilot : " + pilot + "\nAssistant Pilot :" + assistantPilot
				+ "\nStewardess : " + stewardess + "\nCapacity : " + capacity + "\nTravel Tax Charge : " + travelTaxCharge
				+ "\nBaggage Fee : " + baggageFee, "\nAirplane Details", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
				options, 1);
		return airplaneOption;
	}
	
	void proceedToDestination() {
		String[] options_destinationType = {"Local", "International"};
		int destinationType = JOptionPane.showOptionDialog(null, "Choose Destination Type", null, JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options_destinationType, null);
		if(destinationType == 0) {//Local Window
			destinationWindow = new Destination("Local");
			destinationWindow.setVisible(true);
			destinationWindow.setLocationRelativeTo(null);
		}
		else if(destinationType == 1) {//International Window
			destinationWindow = new Destination("International");
			destinationWindow.setVisible(true);
			destinationWindow.setLocationRelativeTo(null);
		}
	}
	
	void promptTravelInsurance(double x) {
		String[] option = {"Yes","No"}; //Prompt Travel Insurance 
		int insuranceWindow = JOptionPane.showOptionDialog(null, "Do you want to avail Travel Insurance?" + 
				"\nAmount : Php " + formatDecimal.format(x) + " per pax" + "\n\nNote: Seniors are free", "Travel Insurance",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
		if(insuranceWindow == 0) travelInsurance = x;
	}

	
//SETTERS AND GETTERS
	public Destination getDestination() {
		return destinationWindow;
	}
	
	public String getAirplaneType() {
		return airplaneType;
	}

	public void setAirplaneType(String airplaneType) {
		this.airplaneType = airplaneType;
	}

	public int getPilot() {
		return pilot;
	}

	public void setPilot(int pilot) {
		this.pilot = pilot;
	}

	public int getAssistantPilot() {
		return assistantPilot;
	}

	public void setAssistantPilot(int assistantPilot) {
		this.assistantPilot = assistantPilot;
	}

	public int getStewardess() {
		return stewardess;
	}

	public void setStewardess(int stewardess) {
		this.stewardess = stewardess;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getTravelTaxCharge() {
		return travelTaxCharge;
	}

	public void setTravelTaxCharge(double travelTaxCharge) {
		this.travelTaxCharge = travelTaxCharge;
	}

	public double getBaggageFee() {
		return baggageFee;
	}

	public void setBaggageFee(double baggageFee) {
		this.baggageFee = baggageFee;
	}

	public double getTravelInsurance() {
		return travelInsurance;
	}

	public void setTravelInsurance(double travelInsurance) {
		this.travelInsurance = travelInsurance;
	}

	public double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(double processingFee) {
		this.processingFee = processingFee;
	}
}
