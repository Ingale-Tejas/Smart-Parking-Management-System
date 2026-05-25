package model;

public class Vehicle {
    private String owner_Name;
    private String owner_Mobile_No;
    private String vehicle_Type;
    private String vehicle_Model;
    private String vehicle_Number;
    private String slot_Number;
    private int floor_Number;
    private String entry_Time;
    private String ticket_ID;

    //Constructor
    public Vehicle(String owner_Name, String owner_Mobile_No, String vehicle_Type,String vehicle_Model, String vehicle_Number,
                   String slot_Number, int floor_Number, String entry_Time,String ticket_ID)
    {
        this.owner_Name = owner_Name;
        this.owner_Mobile_No = owner_Mobile_No;
        this.vehicle_Type = vehicle_Type;
        this.vehicle_Model = vehicle_Model;
        this.vehicle_Number = vehicle_Number;
        this.slot_Number = slot_Number;
        this.floor_Number = floor_Number;
        this.entry_Time = entry_Time;
        this.ticket_ID = ticket_ID;
    }
    //Getter
    public String getOwner_Name() {
        return owner_Name;
    }
    public String getOwner_Mobile_No() {
        return owner_Mobile_No;
    }
    public String getVehicle_Type() {
        return vehicle_Type;
    }
    public String getVehicle_Model() {
        return vehicle_Model;
    }
    public String getVehicle_Number() {
        return vehicle_Number;
    }
    public String getSlot_Number() {
        return slot_Number;
    }
    public int getFloor_Number() {
        return floor_Number;
    }
    public String getEntry_Time() {
        return entry_Time;
    }
    public String getTicket_ID() {
        return ticket_ID;
    }
}
