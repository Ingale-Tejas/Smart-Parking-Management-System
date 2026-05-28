package service;


import database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class slotManager {
    Connection connection = DatabaseManager.getConnection();
    public ArrayList<String> getAllSlots() {

            ArrayList<String> slots = new ArrayList<>();

            //Floor1
        slots.add("A101");
        slots.add("A102");
        slots.add("A103");
        slots.add("A104");
        slots.add("A105");
        slots.add("A106");
            //Floor2
        slots.add("B201");
        slots.add("B202");
        slots.add("B203");
        slots.add("B204");
        slots.add("B205");

        return slots;
    }
    public String getAvailableSlot() {
        ArrayList<String> allSlots = getAllSlots();

        try{
            String query = "SELECT slot_number FROM active_parking";

            PreparedStatement ps = connection.prepareStatement(query); // this Prepare's SQL query for execution.

                ResultSet resultSet = ps.executeQuery();//imp jdbc concept this
            //here .executeQuery()-> Runs SELECT query.

                while(resultSet.next()){
                    String occupiedSlot = resultSet.getString("slot_number"); //Get current occupied slot from database row
                    allSlots.remove(occupiedSlot);
                }
            if (!allSlots.isEmpty()) {

                return allSlots.get(0);
                                //.get(0)->first item from ArrayList
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
