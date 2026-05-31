package report;

import database.DatabaseManager;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReportManager {
    Connection connection = DatabaseManager.getConnection();

    public void generateEndDayReport() {
        String query = "SELECT * FROM parking_history";
        try {
            FileWriter writer = new FileWriter("reports/todays_report.txt");
            writer.write("=========== TODAY'S PARKING REPORT ===========\n\n");
            writer.write("Date: " + LocalDate.now() + "\n\n");

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            int totalVehicles = 0;
            int totalRevenue = 0;
            int serialNumber = 1;

            while (resultSet.next()) {
                totalVehicles++;
                totalRevenue += resultSet.getInt("total_fee");

                writer.write(serialNumber + " | " +

                                resultSet.getString("owner_name") + " | " +
                                resultSet.getString("vehicle_type") + " | " +
                                resultSet.getString("vehicle_number") + " | " +
                                resultSet.getString("entry_time") + " | " +
                                resultSet.getString("exit_time") + " | " +
                                resultSet.getString("parking_duration") + " | ₹" +
                                resultSet.getInt("total_fee") + "\n"
                );
                serialNumber++;
            }
            writer.write("\n\nTotal Vehicles Visited: " + totalVehicles);
            writer.write("\nTotal Revenue: ₹" + totalRevenue);

            writer.close();

            System.out.println("End-Day Report Generated Successfully!");
    }catch (Exception e){
        e.printStackTrace();
        }
    }

}
