package service;
import database.DatabaseManager;
import model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ParkingService {
    Connection connection = DatabaseManager.getConnection();

public void parkVehicle (Vehicle v) {

    String query = "INSERT INTO active_parking " + "(ticket_id, owner_name, owner_mobile, vehicle_type, vehicle_model, vehicle_number, slot_number, floor_number, entry_time ) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";  //? -> this are "Placeholders"
                                            //This Prevents SQL Injection

    try{
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, v.getTicket_ID());
        ps.setString(2, v.getOwner_Name());
        ps.setString(3, v.getOwner_Mobile_No());
        ps.setString(4, v.getVehicle_Type());
        ps.setString(5, v.getVehicle_Model());
        ps.setString(6, v.getVehicle_Number());
        ps.setString(7, v.getSlot_Number());
        ps.setInt(8, v.getFloor_Number());
        ps.setString(9, v.getEntry_Time());

        int rowsInserted = ps.executeUpdate(); // this will actually insert data in DB

        if (rowsInserted > 0) {
            System.out.println("Vehicle data inserted Successfully");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
