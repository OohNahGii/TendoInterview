package main.java.repository;

import main.java.api.Feedback;
import main.java.api.PatientFeedback;
import main.java.api.Resource;
import main.java.api.ResourceType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientFeedbackFileRepository implements ResourceRepository<PatientFeedback> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String REPOSITORY_DIR = "datastore/" + ResourceType.PATIENT_FEEDBACK.getName();

    @Override
    public String saveResource(PatientFeedback patientFeedback) throws IOException {
        patientFeedback.setId(generateId());
        List<Map<String, Object>> feedback = patientFeedback.getFeedback().stream()
                .map(f -> Map.of(Feedback.QUESTION_FIELD, f.getQuestion(), Feedback.ANSWER_FIELD, f.getAnswer()))
                .collect(Collectors.toList());
        Map<String, Object> output = Map.of(
                Resource.ID_FIELD, patientFeedback.getId(),
                Resource.TYPE_FIELD, patientFeedback.getType().getName(),
                PatientFeedback.FEEDBACK_FIELD, feedback,
                PatientFeedback.APPOINTMENT_FIELD, Map.of(
                        Resource.REFERENCE_FIELD, Resource.createReferenceId(
                                ResourceType.APPOINTMENT.getName(), patientFeedback.getAppointmentId())));
        String serializedPatientFeedback = GSON.toJson(output);
        File outputFile = new File(REPOSITORY_DIR + "/" + patientFeedback.getId() + ".json");
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();
        FileWriter writer = new FileWriter(outputFile);
        writer.write(serializedPatientFeedback);
        writer.close();
        return patientFeedback.getId();
    }

    @Override
    public PatientFeedback getResource(String id) {
        return null;
    }
}
