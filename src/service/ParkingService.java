package service;
import database.DatabaseManager;
import model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import service.slotManager;

import java.time.*; // .Duration & .LocalDateTime

public class ParkingService {
    Connection connection = DatabaseManager.getConnection();

    public void parkVehicle(Vehicle v) {

        String query = "INSERT INTO active_parking " + "(ticket_id, owner_name, owner_mobile, vehicle_type, vehicle_model, vehicle_number, slot_number, floor_number, entry_time ) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";  //? -> this are "Placeholders"
        //This Prevents SQL Injection

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchVehicle(String SN) {
        String query = "SELECT * FROM active_parking WHERE vehicle_number = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, SN);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n======== VEHICLE FOUND =========");
                System.out.println("Owner Name: " + rs.getString("owner_name"));
                System.out.println("Vehicle Type : " + rs.getString("vehicle_type"));
                System.out.println("Vehicle Number : " + rs.getString("vehicle_number"));
                System.out.println("Slot Number : " + rs.getString("slot_number"));
                System.out.println("Floor Number : " + rs.getInt("floor_number"));
            }
            else {
                System.out.println("Vehicle Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitVehicle(String exitNumber) {
        String searchquery = "Select * FROM active_parking WHERE vehicle_number = ?";

        try{
            PreparedStatement ps = connection.prepareStatement(searchquery);
            ps.setString(1, exitNumber);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                System.out.println("VEHICLE FOUND, Proceeding EXIT ...");

                String entryTimeString = rs.getString("entry_time");
                LocalDateTime entryTime = LocalDateTime.parse( entryTimeString.replace(" ", "T")); // convert string time into Date-Time *OBJECT*
                LocalDateTime exitTime = LocalDateTime.now();

                long hours = Duration.between( entryTime, exitTime).toHours();
                if (hours == 0){
                    hours = 1;
                }
                int totalFee;
                totalFee = (int) hours * 10;
                String entryTimeText =  entryTime.toString();
                String exitTimeText = exitTime.toString();
                String parkingDuration = hours + " hour(s)";
                System.out.println("Parking Duration: " + parkingDuration);
                System.out.println("Total Fee: rs" + totalFee);
        String insertHistoryQuery = "INSERT INTO parking_history" +
                        "(ticket_id, owner_name, vehicle_type, vehicle_number, entry_time, exit_time, parking_duration, total_fee) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement historyStatement =
                        connection.prepareStatement(insertHistoryQuery);

                historyStatement.setString(1, rs.getString("ticket_id"));
                historyStatement.setString(2, rs.getString("owner_name"));
                historyStatement.setString(3, rs.getString("vehicle_type"));
                historyStatement.setString(4, rs.getString("vehicle_number"));
                historyStatement.setString(5, entryTimeText);
                historyStatement.setString(6, exitTimeText);
                historyStatement.setString(7, parkingDuration);
                historyStatement.setInt(8, totalFee);

                historyStatement.executeUpdate(); //execute query
                System.out.println("Vehicle Data stored in History_Table Successfully!");

        String deleteQuery = "DELETE FROM active_parking WHERE vehicle_number = ?";
        PreparedStatement delete = connection.prepareStatement(deleteQuery);
        delete.setString(1, rs.getString("vehicle_number"));

        int rowsDeleted = delete.executeUpdate();
        if (rowsDeleted > 0 ){
            System.out.println("Vehicle Exited Successfully!");
        }

            }
            else {
                System.out.println("VEHICLE NOT FOUND! ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void displayParkingStatus() {
        String query = "SELECT COUNT(*) AS occupied_count FROM active_parking";

            try{
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int occupiedSlots = rs.getInt("occupied_count");
                    slotManager sm = new slotManager();

                    int totalSlots = sm.getAllSlots().size();
                    int availableSlots = totalSlots - occupiedSlots;

                    System.out.println("\n=======PARKING STATUS=======");
                    System.out.println("Total Slots: " + totalSlots);
                    System.out.println("Occupied Slots: " + occupiedSlots);
                    System.out.println("Available Slots: " + availableSlots);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}