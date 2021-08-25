package main.java.parser;

import main.java.api.Doctor;
import main.java.api.Name;
import main.java.api.Resource;

import java.util.List;
import java.util.Map;

public class DoctorResourceParser implements ResourceParser {
    @Override
    public String getResourceType() {
        return "Doctor";
    }

    @Override
    public Doctor parseResource(Map<String, Object> resourceData) {
        String id = (String) resourceData.get(Resource.ID_FIELD);
        Doctor doctor = new Doctor(id);
        if (resourceData.containsKey(Name.NAME_FIELD)) {
            List<Map<String, Object>> names = (List<Map<String, Object>>) resourceData.get(Name.NAME_FIELD);
            names.forEach(name -> {
                String family = (String) name.get(Name.FAMILY_FIELD);
                List<String> given = (List<String>) name.get(Name.GIVEN_FIELD);
                doctor.addName(new Name(null, family, given));
            });
        }
        return doctor;
    }
}
