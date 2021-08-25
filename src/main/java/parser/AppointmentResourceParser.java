package main.java.parser;

import main.java.api.Appointment;
import main.java.api.Resource;

import java.util.Map;

public class AppointmentResourceParser implements ResourceParser {
    @Override
    public String getResourceType() {
        return "Appointment";
    }

    @Override
    public Appointment parseResource(Map<String, Object> resourceData) {
        String id = (String) resourceData.get(Resource.ID_FIELD);
        return new Appointment(id);
    }
}
