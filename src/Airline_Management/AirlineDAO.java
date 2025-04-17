package Airline_Management;

import Airline_Management.model.Airline;
import java.util.List;

// this is the Airline Data Access Object
public interface AirlineDAO {

    // create aka insert
    public boolean insertAirline(Airline account);

    // read
    public Airline getAccountByID(int flight_id);

    public Airline getAccountByName(String flight_name);

    public List<Airline> getAllAccounts();

    // update
    public boolean changeAirline(int flight_id, String flight_name);

    // delete
    public boolean removeAirline(int accountNumber);
}