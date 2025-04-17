package Airline_Management;

import Airline_Manangement.AirlineDAO;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;

public class MainController {
    private AirlineDAO airlineDB;
    private MainViewInterface mainView;
    private boolean isWithdrawActive;
    private Airline airline;

    public MainController(AirlineDAO airlineDB, MainViewInterface mainView) {
        this.airlineDB = airlineDB;
        this.mainView = mainView;

        mainView.setInsertButtonListener(this::insertButtonOnClick);
        mainView.setlistButtonListener(this::listButtonOnClick);
        mainView.setDeleteButtonListener(this::deleteButtonOnClick);
        mainView.setUpdateButtonListener(this::updateButtonOnClick);
        mainView.setSearchButtonListener(this::searchButtonOnClick);
    }

    private void listButtonOnClick(ActionEvent e) {
        ListAllView listAllView = new ListAllView();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (Airline flight : airlineDB.getAllAccounts()) {
            defaultListModel.addElement(flight.toString());
        }
        listAllView.setAllAccountListDefaultModel(defaultListModel);
        mainView.setEnabled(false);

        listAllView.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                mainView.setEnabled(true);
                mainView.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    private void insertButtonOnClick(ActionEvent e) {
        int accountNumber = mainView.getflightId();
        String name = mainView.getFlightName();

        airlineDB.insertAirline(new Airline(accountNumber, name));
    }

    private void deleteButtonOnClick(ActionEvent e) {
        int flightNumber = mainView.getflightId();
        Airline account = airlineDB.getAccountByID(flightNumber);
        if (account != null) {
            airlineDB.removeAirline(flightNumber);

            // mainView.toggleButtonEnabled(true);
        } else {
            mainView.clearFlightInfo();
            // mainView.toggleButtonEnabled(false);
        }
    }

    private void updateButtonOnClick(ActionEvent e) {
        int id = mainView.getflightId();
        String newName = mainView.getFlightName();
        if (airlineDB.changeAirline(id, newName)) {
            mainView.showMessage(" Flight updated successfully!");
        } else {
            mainView.showMessage("Flight update failed. Flight may not exist.");
        }
    }

    private void searchButtonOnClick(ActionEvent e) {
        try {
            int flightId = mainView.getflightId(); // reads from the Flight ID field
            Airline airline = airlineDB.getAccountByID(flightId);

            if (airline != null) {
                mainView.showMessage(" Flight Found:\nID: " + airline.getID() + "\nName: " +
                        airline.getName());
            } else {
                mainView.showMessage(" No flight found with ID: " + flightId);
            }
        } catch (NumberFormatException ex) {
            mainView.showMessage(" Invalid input! Please enter a numeric Flight ID.");
        }
    }

    public static void main(String[] args) {
        new MainController(

                new AirlineDBbySQL(),
                new MainView());
    }
}