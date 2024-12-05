package Airline_Management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MainView extends JFrame implements MainViewInterface{

    private JTextField AirlineflightIDText = new JTextField();
    private JTextField AirlineflightNameText = new JTextField();
    private JButton insertButton = new JButton("Create flight");
    private JButton deleteButton = new JButton("Delete flight");
    private JButton listButton = new JButton("List All");
    

    public MainView() {
        setTitle("Main");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel outerPanel = new JPanel();
        outerPanel.setBorder(new EmptyBorder(5,5,5,5));
        JPanel centerPanel = new JPanel( new GridLayout(3, 2) );
        outerPanel.add(centerPanel);
        JPanel bottomPanel = new JPanel( new GridLayout(3, 2) );
        addCenterComponent(centerPanel);
        addButtons(bottomPanel);
        add(topPanel, BorderLayout.NORTH);
        add(outerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private void addButtons(JPanel bottomPanel) {
        bottomPanel.setBorder(new EmptyBorder(10,10,10,10));
        bottomPanel.add(insertButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(listButton);
        
        JPanel selectioPanel = new JPanel();
        selectioPanel.add(deleteButton);
       
        bottomPanel.add(selectioPanel);
        JPanel buttonSelectionPanel = new JPanel();
        buttonSelectionPanel.add(listButton);
        
        bottomPanel.add(buttonSelectionPanel);
        
        toggleButtonEnabled(true);
    }

   

    private void addCenterComponent(JPanel centerPanel) {
        centerPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        centerPanel.add(new JLabel("Flight ID "));
        centerPanel.add(AirlineflightIDText);

        centerPanel.add(new JLabel("Flight Name "));
        centerPanel.add(AirlineflightNameText);

           
    }

    @Override
    public void setInsertButtonListener(ActionListener listener) {
        insertButton.addActionListener(listener);
    }

    @Override
    public int getflightId() {
        String number = AirlineflightIDText.getText();
        try {
            return Integer.parseInt(number);
        }
        catch (NumberFormatException e) {
            showMessage("Please enter a correct account number");
        }
        return 0;
    }

   
 
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error Report",
            JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void setAirlineID(int flightId) {
        AirlineflightIDText.setText( Integer.toString(flightId) );
    }

    @Override
    public void setFlightName(String flightName) {
        AirlineflightNameText.setText(flightName);
    }
    @Override
    public String getFlightName () {
        return  AirlineflightNameText.getText();
    }
    

    @Override
    public void clearFlightInfo() {
        AirlineflightNameText.setText("");
        
    }

    @Override
    public void toggleButtonEnabled(boolean enabled) {
        insertButton.setEnabled(enabled);
        listButton.setEnabled(enabled);
        
        deleteButton.setEnabled(enabled);
    }

    @Override
    public void setlistButtonListener(ActionListener listener) {
        listButton.addActionListener(listener);
    }

    

    @Override
    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    
}