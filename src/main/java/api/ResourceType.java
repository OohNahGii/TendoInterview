package main.java.api;

public enum ResourceType {
    BUNDLE("Bundle"),
    PATIENT("Patient"),
    DOCTOR("Doctor"),
    APPOINTMENT("Appointment"),
    DIAGNOSIS("Diagnosis"),
    PATIENT_FEEDBACK("PatientFeedback");

    private String name;

    ResourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
