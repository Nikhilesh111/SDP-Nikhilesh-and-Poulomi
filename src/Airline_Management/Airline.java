package Airline_Management;

public class Airline {
    private final int flight_id;
    private String flight_name;

    public Airline(int flight_id, String flight_name) {
        this.flight_id = flight_id;
        this.flight_name = flight_name;
    }

    public int getID() {
        return flight_id;
    }

    public String getName() {
        return flight_name;
    }

    public void setName(String flight_name) {
        this.flight_name = flight_name;
    }

    @Override
    public String toString() {
        return "[" + flight_id + "] " + flight_name;
    }
}