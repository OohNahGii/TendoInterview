package main.java.api;

public class Appointment extends Resource {
    public Appointment(String id) {
        super(id);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.APPOINTMENT;
    }
}