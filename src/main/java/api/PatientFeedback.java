package main.java.api;

import java.util.ArrayList;
import java.util.List;

public class PatientFeedback extends Resource {
    public static final String APPOINTMENT_FIELD = "appointment";
    public static final String FEEDBACK_FIELD = "feedback";
    public static final String SUMMARY_FIELD = "summary";

    private String appointmentId;
    private List<Feedback> feedback;
    private String summary;

    public PatientFeedback() {
        super();
        feedback = new ArrayList<>();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public void addFeedback(Feedback feedback) {
        this.feedback.add(feedback);
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public ResourceType getType() {
        return ResourceType.PATIENT_FEEDBACK;
    }
}
