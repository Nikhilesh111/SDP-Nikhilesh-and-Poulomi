package Airline_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirlineDBbySQL implements AirlineDAO {

    private static final String SQL_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String SQL_SERVER = "127.0.0.1";
    private static final String SQL_SERVER_PORT = "3306";
    private static final String SQL_DATABASE = "dbaccount";
    private static final String SQL_USERNAME = "dbaccount";
    private static final String SQL_PASSWORD = "secret123";

    private Connection sqlConnection = null;

    public AirlineDBbySQL() {
        // ensure, the SQL connector is loaded proberly
        try {
            Class.forName(SQL_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Oops, SQL connector not installed!");
            System.exit(-1);
        }
        openSQLConnection();
    }

    private void openSQLConnection() {
        try {
            sqlConnection = DriverManager.getConnection("jdbc:mariadb://" +
                    SQL_SERVER + ":" + SQL_SERVER_PORT + "/" +
                    SQL_DATABASE, SQL_USERNAME, SQL_PASSWORD);
            // sqlConnection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Got some problem when establishing SQL connection");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    @Override
    public boolean insertAirline(Airline airline) {
        // INSERT INTO airline (number, name) VALUES (?, ?, ?);
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                    "INSERT INTO Airline (flight_id, flight_name) VALUES (?, ?)");
            sqlQuery.setInt(1, airline.getID());
            sqlQuery.setString(2, airline.getName());
            return sqlQuery.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Airline getAccountByID(int flight_id) {
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                    "SELECT flight_id, flight_name FROM Airline WHERE flight_id=?");
            sqlQuery.setInt(1, flight_id);
            ResultSet sqlTable = sqlQuery.executeQuery();
            if (!sqlTable.next())
                return null;

            // int flight_id = sqlTable.getInt("flight_id");
            String flight_name = sqlTable.getString("flight_name");
            // double balance = sqlTable.getDouble("balance");
            return new Airline(flight_id, flight_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airline getAccountByName(String accountOwner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountByName'");
    }

    @Override
    public List<Airline> getAllAccounts() {
        // SELECT number, name FROM airline;
        List<Airline> accountList = new ArrayList<>();
        try {
            Statement sqlQuery = sqlConnection.createStatement();
            ResultSet sqlTable = sqlQuery.executeQuery("SELECT flight_id, flight_name FROM Airline");
            while (sqlTable.next()) {
                int flight_id = sqlTable.getInt("flight_id");
                String flight_name = sqlTable.getString("flight_name");
                accountList.add(new Airline(flight_id, flight_name));
            }
        } catch (SQLException e) {
            // maybe some method later to report all errors...
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public boolean changeAirline(int flight_id, String flight_name) {
        removeAirline(flight_id);
        return insertAirline(new Airline(flight_id, flight_name));
    }

    @Override
    public boolean removeAirline(int flight_id) {
        // DELETE FROM airline WHERE number = ?
        try {
            PreparedStatement sqlQuery = sqlConnection.prepareStatement(
                    "DELETE FROM Airline WHERE flight_id = ?");
            sqlQuery.setInt(1, flight_id);
            return sqlQuery.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}