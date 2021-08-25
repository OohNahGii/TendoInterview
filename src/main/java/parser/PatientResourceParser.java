package main.java.parser;

import main.java.api.Name;
import main.java.api.Patient;
import main.java.api.Resource;

import java.util.List;
import java.util.Map;

public class PatientResourceParser implements ResourceParser {

    @Override
    public String getResourceType() {
        return "Patient";
    }

    @Override
    public Patient parseResource(Map<String, Object> resourceData) {
        String id = (String) resourceData.get(Resource.ID_FIELD);
        Patient patient = new Patient(id);
        if (resourceData.containsKey(Name.NAME_FIELD)) {
            List<Map<String, Object>> names = (List<Map<String, Object>>) resourceData.get(Name.NAME_FIELD);
            names.forEach(name -> {
                String text = (String) name.get(Name.TEXT_FIELD);
                String family = (String) name.get(Name.FAMILY_FIELD);
                List<String> given = (List<String>) name.get(Name.GIVEN_FIELD);
                patient.addName(new Name(text, family, given));
            });
        }
        return patient;
    }
}
