package main;
import database.DatabaseManager;
import model.Vehicle;
import service.ParkingService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        ParkingService ps = new ParkingService(); //object creation sysntax

        Vehicle v = new Vehicle(
                "Tejas","9834769354","Car","BMW","MH12GF8553","A101",
                1, LocalDateTime.now().toString(), "TKT101" );
        ps.parkVehicle(v);
    }
}
