package challange.coding.cic.ibm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * View receives and outputs Changes / Data 
 * & serves as the main interface between program & User
 */
public class View {

	JFrame frmElevatorSimulation;
	private JTextField textFieldCurrFloor;
	private JTextField textFieldDestFloor;
	private JLabel lblRequestError;
	private JButton btnCallElevator;
	
	// Elevator Current Floor Label
	private JLabel lblCurrFloorOfElevatorOne;
	private JLabel lblCurrFloorOfElevatorTwo;
	private JLabel lblCurrFloorOfElevatorThree;
	private JLabel lblCurrFloorOfElevatorFour;
	private JLabel lblCurrFloorOfElevatorFive;
	private JLabel lblCurrFloorOfElevatorSix;
	private JLabel lblCurrFloorOfElevatorSeven;
	
	// Elevator Person List
	private JTextArea txtAreaPeopleInElevatorOne;
	private JTextArea txtAreaPeopleInElevatorTwo;
	private JTextArea txtAreaPeopleInElevatorThree;
	private JTextArea txtAreaPeopleInElevatorFour;
	private JTextArea txtAreaPeopleInElevatorFive;
	private JTextArea txtAreaPeopleInElevatorSix;
	private JTextArea txtAreaPeopleInElevatorSeven;
	
	// Setter of Elevator-Current-Floor-Labels
	public void setLblCurrFloorOfElevatorOne(String lbl) {
		lblCurrFloorOfElevatorOne.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorTwo(String lbl) {
		lblCurrFloorOfElevatorTwo.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorThree(String lbl) {
		lblCurrFloorOfElevatorThree.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorFour(String lbl) {
		lblCurrFloorOfElevatorFour.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorFive(String lbl) {
		lblCurrFloorOfElevatorFive.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorSix(String lbl) {
		lblCurrFloorOfElevatorSix.setText(lbl);
	}
	public void setLblCurrFloorOfElevatorSeven(String lbl) {
		lblCurrFloorOfElevatorSeven.setText(lbl);
	}

	// Setter of People in Elevator - Label
	public void setTxtAreaPeopleInElevatorOne(String lbl) {
		txtAreaPeopleInElevatorOne.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorTwo(String lbl) {
		txtAreaPeopleInElevatorTwo.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorThree(String lbl) {
		txtAreaPeopleInElevatorThree.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorFour(String lbl) {
		txtAreaPeopleInElevatorFour.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorFive(String lbl) {
		txtAreaPeopleInElevatorFive.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorSix(String lbl) {
		txtAreaPeopleInElevatorSix.setText(lbl);
	}
	public void setTxtAreaPeopleInElevatorSeven(String lbl) {
		txtAreaPeopleInElevatorSeven.setText(lbl);
	}
	
	// Getter of Labels in View for Controller
	public JButton getBtnCallElevator() {
		return btnCallElevator;
	}
	
	public String getTxtFieldCurrFloor() {
		return textFieldCurrFloor.getText();
	}
	
	public String getTxtFieldDestFloor() {
		return textFieldDestFloor.getText();
	}
	
	public void setMessage(String message) {
		lblRequestError.setText(message);
	}
	
	public String getLblRequestError() {
		return lblRequestError.getText();
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElevatorSimulation = new JFrame();
		frmElevatorSimulation.setTitle("Elevator Simulation");
		frmElevatorSimulation.setBounds(100, 100, 692, 763);
		frmElevatorSimulation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmElevatorSimulation.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelSouth = new JPanel();
		frmElevatorSimulation.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[] {200, 20, 30, 20, 200};
		gbl_panelSouth.rowHeights = new int[] {30, 30, 40, 30, 40};
		gbl_panelSouth.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_panelSouth.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panelSouth.setLayout(gbl_panelSouth);
		
		lblRequestError = new JLabel("");
		GridBagConstraints gbc_lblRequestError = new GridBagConstraints();
		gbc_lblRequestError.gridwidth = 3;
		gbc_lblRequestError.insets = new Insets(10, 0, 5, 5);
		gbc_lblRequestError.gridx = 1;
		gbc_lblRequestError.gridy = 0;
		panelSouth.add(lblRequestError, gbc_lblRequestError);
		
		btnCallElevator = new JButton("Call Elevator");

		GridBagConstraints gbc_btnCallElevator = new GridBagConstraints();
		gbc_btnCallElevator.gridwidth = 3;
		gbc_btnCallElevator.insets = new Insets(0, 0, 5, 5);
		gbc_btnCallElevator.gridx = 1;
		gbc_btnCallElevator.gridy = 1;
		panelSouth.add(btnCallElevator, gbc_btnCallElevator);
		
		JLabel lblCurrFloor = new JLabel("Current Floor");
		GridBagConstraints gbc_lblCurrFloor = new GridBagConstraints();
		gbc_lblCurrFloor.weightx = 1.0;
		gbc_lblCurrFloor.insets = new Insets(5, 5, 5, 5);
		gbc_lblCurrFloor.gridx = 1;
		gbc_lblCurrFloor.gridy = 2;
		panelSouth.add(lblCurrFloor, gbc_lblCurrFloor);
		
		JLabel lblDestFloor = new JLabel("Destination Floor");
		GridBagConstraints gbc_lblDestFloor = new GridBagConstraints();
		gbc_lblDestFloor.weightx = 1.0;
		gbc_lblDestFloor.insets = new Insets(5, 5, 5, 5);
		gbc_lblDestFloor.gridx = 3;
		gbc_lblDestFloor.gridy = 2;
		panelSouth.add(lblDestFloor, gbc_lblDestFloor);
		
		textFieldCurrFloor = new JTextField();
		GridBagConstraints gbc_textFieldCurrFloor = new GridBagConstraints();
		gbc_textFieldCurrFloor.weightx = 1.0;
		gbc_textFieldCurrFloor.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCurrFloor.gridx = 1;
		gbc_textFieldCurrFloor.gridy = 3;
		panelSouth.add(textFieldCurrFloor, gbc_textFieldCurrFloor);
		textFieldCurrFloor.setColumns(10);
		
		textFieldDestFloor = new JTextField();
		GridBagConstraints gbc_textFieldDestFloor = new GridBagConstraints();
		gbc_textFieldDestFloor.weightx = 1.0;
		gbc_textFieldDestFloor.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDestFloor.gridx = 3;
		gbc_textFieldDestFloor.gridy = 3;
		panelSouth.add(textFieldDestFloor, gbc_textFieldDestFloor);
		textFieldDestFloor.setColumns(10);
		
		JPanel panelCenter = new JPanel();
		frmElevatorSimulation.getContentPane().add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] {30, 0, 0, 20, 0, 0, 30, 0, 0, 30, 0, 0, 30, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		
		lblCurrFloorOfElevatorOne = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorOne = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorOne.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorOne.insets = new Insets(5, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorOne.gridx = 1;
		gbc_lblCurrFloorOfElevatorOne.gridy = 1;
		panelCenter.add(lblCurrFloorOfElevatorOne, gbc_lblCurrFloorOfElevatorOne);
		
		lblCurrFloorOfElevatorTwo = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorTwo = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorTwo.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorTwo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorTwo.gridx = 4;
		gbc_lblCurrFloorOfElevatorTwo.gridy = 1;
		panelCenter.add(lblCurrFloorOfElevatorTwo, gbc_lblCurrFloorOfElevatorTwo);
		
		lblCurrFloorOfElevatorThree = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorThree = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorThree.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorThree.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorThree.gridx = 7;
		gbc_lblCurrFloorOfElevatorThree.gridy = 1;
		panelCenter.add(lblCurrFloorOfElevatorThree, gbc_lblCurrFloorOfElevatorThree);
		
		lblCurrFloorOfElevatorFour = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorFour = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorFour.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorFour.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorFour.gridx = 10;
		gbc_lblCurrFloorOfElevatorFour.gridy = 1;
		panelCenter.add(lblCurrFloorOfElevatorFour, gbc_lblCurrFloorOfElevatorFour);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.weightx = 1.0;
		gbc_textPane.weighty = 5.0;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 2;
		panelCenter.add(textPane, gbc_textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
		gbc_textPane_1.weightx = 1.0;
		gbc_textPane_1.weighty = 4.0;
		gbc_textPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_1.gridx = 2;
		gbc_textPane_1.gridy = 2;
		panelCenter.add(textPane_1, gbc_textPane_1);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		GridBagConstraints gbc_textPane_3 = new GridBagConstraints();
		gbc_textPane_3.weightx = 1.0;
		gbc_textPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3.fill = GridBagConstraints.BOTH;
		gbc_textPane_3.gridx = 4;
		gbc_textPane_3.gridy = 2;
		panelCenter.add(textPane_3, gbc_textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		GridBagConstraints gbc_textPane_4 = new GridBagConstraints();
		gbc_textPane_4.weightx = 1.0;
		gbc_textPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_4.fill = GridBagConstraints.BOTH;
		gbc_textPane_4.gridx = 5;
		gbc_textPane_4.gridy = 2;
		panelCenter.add(textPane_4, gbc_textPane_4);
		
		JTextPane textPane_3_1 = new JTextPane();
		textPane_3_1.setEditable(false);
		GridBagConstraints gbc_textPane_3_1 = new GridBagConstraints();
		gbc_textPane_3_1.weightx = 1.0;
		gbc_textPane_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_3_1.gridx = 7;
		gbc_textPane_3_1.gridy = 2;
		panelCenter.add(textPane_3_1, gbc_textPane_3_1);
		
		JTextPane textPane_3_1_1 = new JTextPane();
		textPane_3_1_1.setEditable(false);
		GridBagConstraints gbc_textPane_3_1_1 = new GridBagConstraints();
		gbc_textPane_3_1_1.weightx = 1.0;
		gbc_textPane_3_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3_1_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_3_1_1.gridx = 8;
		gbc_textPane_3_1_1.gridy = 2;
		panelCenter.add(textPane_3_1_1, gbc_textPane_3_1_1);
		
		JTextPane textPane_3_1_1_1 = new JTextPane();
		textPane_3_1_1_1.setEditable(false);
		GridBagConstraints gbc_textPane_3_1_1_1 = new GridBagConstraints();
		gbc_textPane_3_1_1_1.weightx = 1.0;
		gbc_textPane_3_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_3_1_1_1.gridx = 10;
		gbc_textPane_3_1_1_1.gridy = 2;
		panelCenter.add(textPane_3_1_1_1, gbc_textPane_3_1_1_1);
		
		JTextPane textPane_3_1_1_2 = new JTextPane();
		textPane_3_1_1_2.setEditable(false);
		GridBagConstraints gbc_textPane_3_1_1_2 = new GridBagConstraints();
		gbc_textPane_3_1_1_2.weightx = 1.0;
		gbc_textPane_3_1_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3_1_1_2.fill = GridBagConstraints.BOTH;
		gbc_textPane_3_1_1_2.gridx = 11;
		gbc_textPane_3_1_1_2.gridy = 2;
		panelCenter.add(textPane_3_1_1_2, gbc_textPane_3_1_1_2);
		
		txtAreaPeopleInElevatorOne = new JTextArea(5, 20);
		txtAreaPeopleInElevatorOne.setLineWrap(true);
		txtAreaPeopleInElevatorOne.setWrapStyleWord(true);
		txtAreaPeopleInElevatorOne.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorOne = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorOne.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorOne.weighty = 1.0;
		gbc_txtAreaPeopleInElevatorOne.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorOne.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorOne.gridx = 1;
		gbc_txtAreaPeopleInElevatorOne.gridy = 3;
		panelCenter.add(txtAreaPeopleInElevatorOne, gbc_txtAreaPeopleInElevatorOne);
		
		txtAreaPeopleInElevatorTwo = new JTextArea(5, 20);
		txtAreaPeopleInElevatorTwo.setLineWrap(true);
		txtAreaPeopleInElevatorTwo.setWrapStyleWord(true);
		txtAreaPeopleInElevatorTwo.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorTwo = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorTwo.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorTwo.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorTwo.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorTwo.gridx = 4;
		gbc_txtAreaPeopleInElevatorTwo.gridy = 3;
		panelCenter.add(txtAreaPeopleInElevatorTwo, gbc_txtAreaPeopleInElevatorTwo);
		
		txtAreaPeopleInElevatorThree = new JTextArea(5, 20);
		txtAreaPeopleInElevatorThree.setLineWrap(true);
		txtAreaPeopleInElevatorThree.setWrapStyleWord(true);
		txtAreaPeopleInElevatorThree.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorThree = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorThree.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorThree.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorThree.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorThree.gridx = 7;
		gbc_txtAreaPeopleInElevatorThree.gridy = 3;
		panelCenter.add(txtAreaPeopleInElevatorThree, gbc_txtAreaPeopleInElevatorThree);
		
		txtAreaPeopleInElevatorFour = new JTextArea(5, 20);
		txtAreaPeopleInElevatorFour.setLineWrap(true);
		txtAreaPeopleInElevatorFour.setWrapStyleWord(true);
		txtAreaPeopleInElevatorFour.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorFour = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorFour.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorFour.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorFour.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorFour.gridx = 10;
		gbc_txtAreaPeopleInElevatorFour.gridy = 3;
		panelCenter.add(txtAreaPeopleInElevatorFour, gbc_txtAreaPeopleInElevatorFour);
		
		lblCurrFloorOfElevatorFive = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorFive = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorFive.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorFive.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorFive.gridx = 1;
		gbc_lblCurrFloorOfElevatorFive.gridy = 5;
		panelCenter.add(lblCurrFloorOfElevatorFive, gbc_lblCurrFloorOfElevatorFive);
		
		lblCurrFloorOfElevatorSix = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorSix = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorSix.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorSix.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorSix.gridx = 4;
		gbc_lblCurrFloorOfElevatorSix.gridy = 5;
		panelCenter.add(lblCurrFloorOfElevatorSix, gbc_lblCurrFloorOfElevatorSix);
		
		lblCurrFloorOfElevatorSeven = new JLabel(" ------------- ");
		GridBagConstraints gbc_lblCurrFloorOfElevatorSeven = new GridBagConstraints();
		gbc_lblCurrFloorOfElevatorSeven.gridwidth = 2;
		gbc_lblCurrFloorOfElevatorSeven.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrFloorOfElevatorSeven.gridx = 7;
		gbc_lblCurrFloorOfElevatorSeven.gridy = 5;
		panelCenter.add(lblCurrFloorOfElevatorSeven, gbc_lblCurrFloorOfElevatorSeven);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		GridBagConstraints gbc_textPane_2 = new GridBagConstraints();
		gbc_textPane_2.weighty = 5.0;
		gbc_textPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_2.fill = GridBagConstraints.BOTH;
		gbc_textPane_2.gridx = 1;
		gbc_textPane_2.gridy = 6;
		panelCenter.add(textPane_2, gbc_textPane_2);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		GridBagConstraints gbc_textPane_5 = new GridBagConstraints();
		gbc_textPane_5.weighty = 4.0;
		gbc_textPane_5.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_5.fill = GridBagConstraints.BOTH;
		gbc_textPane_5.gridx = 2;
		gbc_textPane_5.gridy = 6;
		panelCenter.add(textPane_5, gbc_textPane_5);
		
		JTextPane textPane_2_1 = new JTextPane();
		textPane_2_1.setEditable(false);
		GridBagConstraints gbc_textPane_2_1 = new GridBagConstraints();
		gbc_textPane_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_2_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_2_1.gridx = 4;
		gbc_textPane_2_1.gridy = 6;
		panelCenter.add(textPane_2_1, gbc_textPane_2_1);
		
		JTextPane textPane_5_1 = new JTextPane();
		textPane_5_1.setEditable(false);
		GridBagConstraints gbc_textPane_5_1 = new GridBagConstraints();
		gbc_textPane_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_5_1.fill = GridBagConstraints.BOTH;
		gbc_textPane_5_1.gridx = 5;
		gbc_textPane_5_1.gridy = 6;
		panelCenter.add(textPane_5_1, gbc_textPane_5_1);
		
		JTextPane textPane_5_2 = new JTextPane();
		textPane_5_2.setEditable(false);
		GridBagConstraints gbc_textPane_5_2 = new GridBagConstraints();
		gbc_textPane_5_2.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_5_2.fill = GridBagConstraints.BOTH;
		gbc_textPane_5_2.gridx = 7;
		gbc_textPane_5_2.gridy = 6;
		panelCenter.add(textPane_5_2, gbc_textPane_5_2);
		
		JTextPane textPane_5_3 = new JTextPane();
		textPane_5_3.setEditable(false);
		GridBagConstraints gbc_textPane_5_3 = new GridBagConstraints();
		gbc_textPane_5_3.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_5_3.fill = GridBagConstraints.BOTH;
		gbc_textPane_5_3.gridx = 8;
		gbc_textPane_5_3.gridy = 6;
		panelCenter.add(textPane_5_3, gbc_textPane_5_3);
		
		txtAreaPeopleInElevatorFive = new JTextArea(5, 20);
		txtAreaPeopleInElevatorFive.setLineWrap(true);
		txtAreaPeopleInElevatorFive.setWrapStyleWord(true);
		txtAreaPeopleInElevatorFive.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorFive = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorFive.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorFive.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorFive.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorFive.gridx = 1;
		gbc_txtAreaPeopleInElevatorFive.gridy = 7;
		panelCenter.add(txtAreaPeopleInElevatorFive, gbc_txtAreaPeopleInElevatorFive);
		
		txtAreaPeopleInElevatorSix = new JTextArea(5, 20);
		txtAreaPeopleInElevatorSix.setLineWrap(true);
		txtAreaPeopleInElevatorSix.setWrapStyleWord(true);
		txtAreaPeopleInElevatorSix.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorSix = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorSix.weighty = 1.0;
		gbc_txtAreaPeopleInElevatorSix.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorSix.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorSix.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorSix.gridx = 4;
		gbc_txtAreaPeopleInElevatorSix.gridy = 7;
		panelCenter.add(txtAreaPeopleInElevatorSix, gbc_txtAreaPeopleInElevatorSix);
		
		txtAreaPeopleInElevatorSeven = new JTextArea(5, 20);
		txtAreaPeopleInElevatorSeven.setLineWrap(true);
		txtAreaPeopleInElevatorSeven.setWrapStyleWord(true);
		txtAreaPeopleInElevatorSeven.setEditable(false);
		GridBagConstraints gbc_txtAreaPeopleInElevatorSeven = new GridBagConstraints();
		gbc_txtAreaPeopleInElevatorSeven.weighty = 1.0;
		gbc_txtAreaPeopleInElevatorSeven.fill = GridBagConstraints.BOTH;
		gbc_txtAreaPeopleInElevatorSeven.gridwidth = 2;
		gbc_txtAreaPeopleInElevatorSeven.insets = new Insets(0, 0, 5, 5);
		gbc_txtAreaPeopleInElevatorSeven.gridx = 7;
		gbc_txtAreaPeopleInElevatorSeven.gridy = 7;
		panelCenter.add(txtAreaPeopleInElevatorSeven, gbc_txtAreaPeopleInElevatorSeven);
	}
}
