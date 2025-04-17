package Airline_Management;

import java.awt.event.ActionListener;

public interface MainViewInterface {

    void showMessage(String message);

    void setInsertButtonListener(ActionListener listener);

    void setlistButtonListener(ActionListener listener);

    void setDeleteButtonListener(ActionListener listener);

    void toggleButtonEnabled(boolean enabled);

    void setEnabled(boolean enabled);

    void requestFocus();

    int getflightId();

    String getFlightName();

    void setAirlineID(int accountNumber);

    void setFlightName(String owner);

    void clearFlightInfo();

    void setUpdateButtonListener(ActionListener listener);

    void setSearchButtonListener(ActionListener listener);
}