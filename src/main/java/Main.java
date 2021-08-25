package main.java;

import main.java.api.*;
import main.java.parser.InvalidResourceTypeException;
import main.java.parser.NoResourceParserFoundException;
import main.java.parser.ResourceParser;
import main.java.parser.ResourceParserFactory;
import main.java.repository.PatientFeedbackFileRepository;
import main.java.repository.ResourceRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String FEEDBACK_TEMPLATE_FILE = "datastore/PatientFeedbackTemplates/appointment-feedback.json";
    private static final Gson GSON = new Gson();
    private static final String INPUT_JSON_FILE = "input/patient-feedback-raw-data.json";
    private static final TypeToken<Map<String, Object>> MAP_TYPE_TOKEN = new TypeToken<>(){};

    public static void main(String[] args) throws IOException, InvalidResourceTypeException, NoResourceParserFoundException {
        Bundle bundle = (Bundle) getResourceFromFile(INPUT_JSON_FILE);
        PatientFeedbackCollector feedbackCollector = createPatientFeedbackCollector(bundle);
        feedbackCollector.run();
        savePatientFeedback(bundle, feedbackCollector.getPatientFeedback());
    }

    private static Resource getResourceFromFile(String fileName)
            throws IOException, InvalidResourceTypeException, NoResourceParserFoundException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        Map<String, Object> inputJsonData = GSON.fromJson(reader, MAP_TYPE_TOKEN.getType());
        ResourceParser parser = ResourceParserFactory.getResourceParser(inputJsonData);
        return parser.parseResource(inputJsonData);
    }

    private static PatientFeedbackCollector createPatientFeedbackCollector(Bundle bundle)
            throws InvalidResourceTypeException, IOException, NoResourceParserFoundException {
        // For the purposes of this exercise we can assume there exists only one instance of each resource type
        // in the bundle
        Patient patient = (Patient) bundle.getResourcesByType(ResourceType.PATIENT).get(0);
        Doctor doctor = (Doctor) bundle.getResourcesByType(ResourceType.DOCTOR).get(0);
        Diagnosis diagnosis = (Diagnosis) bundle.getResourcesByType(ResourceType.DIAGNOSIS).get(0);

        PatientFeedback patientFeedback = (PatientFeedback) getResourceFromFile(FEEDBACK_TEMPLATE_FILE);
        List<Feedback> feedback = patientFeedback.getFeedback();

        String doctorFamilyName = doctor.getPreferredName().getFamilyName();
        String diagnosisName = diagnosis.getCoding().get(0).getName();
        feedback.get(0).formatQuestion(patient.getPreferredName().getPreferredGivenName(), doctorFamilyName);
        feedback.get(1).formatQuestion(diagnosisName, doctorFamilyName);
        feedback.get(2).formatQuestion(diagnosisName);

        PatientFeedbackCollector feedbackCollector = new PatientFeedbackCollector(System.out, new Scanner(System.in));
        feedbackCollector.addFeedbackToCollect(patientFeedback);
        return feedbackCollector;
    }

    private static void savePatientFeedback(Bundle patientFeedbackBundle, PatientFeedback patientFeedback) throws IOException {
        // For the purposes of this exercise we can assume there exists only one instance of this resource type
        // in the bundle
        Appointment appointment = (Appointment) patientFeedbackBundle.getResourcesByType(ResourceType.APPOINTMENT).get(0);
        patientFeedback.setAppointmentId(appointment.getId());
        ResourceRepository<PatientFeedback> repository = new PatientFeedbackFileRepository();
        repository.saveResource(patientFeedback);
    }
}
