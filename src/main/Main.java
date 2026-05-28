package main;
import database.DatabaseManager;
import model.Vehicle;
import service.ParkingService;
import java.time.LocalDateTime;

import java.util.Scanner;

import service.slotManager;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        slotManager sm = new slotManager();
        ParkingService ps = new ParkingService(); //object creation syntax

        // MENU DRIVEN CODE
        int choice;
        while(true) {
            System.out.println("=========MAll Parking System=========");
            System.out.println("1. Park Vehicle ");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Exit Vehicle");
            System.out.println("4. Display Parking Status");
            System.out.println("5. Generate End Day Report");
            System.out.println("6. Exit");

            System.out.println("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Vehicle v = takeVehicleInput(sc, sm);
                    if (v!= null) {
                        ps.parkVehicle(v);
                    }
                    break;
                case 2:
                    System.out.println("Enter Vehicle Number: ");
                    String searchNumber = sc.nextLine();
                    ps.searchVehicle(searchNumber);
                    break;

                case 3:
                    System.out.println("Enter Vehicle Number: ");
                    String exitNumber = sc.nextLine();
                    ps.exitVehicle(exitNumber);
                    break;

                case 4:
                    System.out.println("Parking Status: ");
                    ps.displayParkingStatus();
                        break;
                case 5:
                    System.out.println("Currently Working....");
                    break;

                case 6:
                    System.out.println("Exiting....");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }

        // ps.parkVehicle(v);
        // ps.searchVehicle(vehicleNumber);
        // ps.exitVehicle(vehicleNumber);
    }

    public static Vehicle takeVehicleInput (Scanner sc, slotManager sm) {
        System.out.println("=========Vehicle Entry =============");
        System.out.println("Enter Owner Name: ");
        String owner = sc.nextLine();
        System.out.println("Enter Owner Mobile Number: ");
        String mobile = sc.nextLine();
        System.out.println("Enter Vehicle Type");
        String vehicleType = sc.nextLine();
        System.out.println("Enter Vehicle Name");
        String vehicleName = sc.nextLine();
        System.out.println("Enter Vehicle Number");
        String vehicleNumber = sc.nextLine();

        String slot = sm.getAvailableSlot();
        if (slot == null){
            System.out.println("Parking Full");
            return null;
        }
        int floor_Number;
        if(slot.startsWith("A")){
            floor_Number = 1;
        }
        else {
            floor_Number = 2;
        }
        String entryTime = LocalDateTime.now().toString();
        String ticketId = "TKT" + System.currentTimeMillis(); //it calculates in milliseconds from 1 january 1970 called (EPOCH TIME)
        // hence we get very huge number and  Adding TKT makes it look like ticket ID,
        // since time always change , the id is always unique

        Vehicle v = new Vehicle(
                owner,
                mobile,
                vehicleType,
                vehicleNumber,
                vehicleName,
                slot,
                floor_Number,
                entryTime,
                ticketId
        );
        return v;
    }
}


