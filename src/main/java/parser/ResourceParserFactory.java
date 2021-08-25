package main.java.parser;

import main.java.api.Resource;

import java.util.Map;
import java.util.Set;

public class ResourceParserFactory {
    private static final AppointmentResourceParser APPOINTMENT_RESOURCE_PARSER = new AppointmentResourceParser();
    private static final BundleResourceParser BUNDLE_RESOURCE_PARSER = new BundleResourceParser();
    private static final DiagnosisResourceParser DIAGNOSIS_RESOURCE_PARSER = new DiagnosisResourceParser();
    private static final DoctorResourceParser DOCTOR_RESOURCE_PARSER = new DoctorResourceParser();
    private static final PatientFeedbackResourceParser PATIENT_FEEDBACK_RESOURCE_PARSER = new PatientFeedbackResourceParser();
    private static final PatientResourceParser PATIENT_RESOURCE_PARSER = new PatientResourceParser();

    private static final Map<String, ResourceParser> SUPPORTED_RESOURCE_PARSERS = Map.of(
            APPOINTMENT_RESOURCE_PARSER.getResourceType(), APPOINTMENT_RESOURCE_PARSER,
            BUNDLE_RESOURCE_PARSER.getResourceType(), BUNDLE_RESOURCE_PARSER,
            DIAGNOSIS_RESOURCE_PARSER.getResourceType(), DIAGNOSIS_RESOURCE_PARSER,
            DOCTOR_RESOURCE_PARSER.getResourceType(), DOCTOR_RESOURCE_PARSER,
            PATIENT_FEEDBACK_RESOURCE_PARSER.getResourceType(), PATIENT_FEEDBACK_RESOURCE_PARSER,
            PATIENT_RESOURCE_PARSER.getResourceType(), PATIENT_RESOURCE_PARSER);

    public static ResourceParser getResourceParser(Map<String, Object> resourceData)
            throws InvalidResourceTypeException, NoResourceParserFoundException {
        if (resourceData == null || resourceData.isEmpty()) {
            throw new IllegalArgumentException("resourceData must be non-empty");
        }
        Object typeObj = resourceData.get(Resource.TYPE_FIELD);
        if (!(typeObj instanceof String)) {
            throw new InvalidResourceTypeException(Resource.TYPE_FIELD + " must be of type String");
        }
        String type = (String) typeObj;
        if (SUPPORTED_RESOURCE_PARSERS.containsKey(type)) {
            return SUPPORTED_RESOURCE_PARSERS.get(type);
        }
        throw new NoResourceParserFoundException("No ResourceParser found for " + type);
    }

    public static Set<String> getSupportedResourceTypes() {
        return SUPPORTED_RESOURCE_PARSERS.keySet();
    }
}
