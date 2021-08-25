package main.java.parser;

import main.java.api.Feedback;
import main.java.api.PatientFeedback;
import main.java.api.Resource;

import java.util.List;
import java.util.Map;

public class PatientFeedbackResourceParser implements ResourceParser {
    @Override
    public String getResourceType() {
        return "PatientFeedback";
    }

    @Override
    public PatientFeedback parseResource(Map<String, Object> resourceData) {
        PatientFeedback patientFeedback = new PatientFeedback();
        patientFeedback.setId((String) resourceData.get(Resource.ID_FIELD));
        patientFeedback.setSummary((String) resourceData.get(PatientFeedback.SUMMARY_FIELD));
        if (resourceData.containsKey(PatientFeedback.FEEDBACK_FIELD)) {
            List<Map<String, Object>> feedbackData =
                    (List<Map<String, Object>>) resourceData.get(PatientFeedback.FEEDBACK_FIELD);
            feedbackData.forEach(f -> {
                Feedback feedback = new Feedback(
                        (String) f.get(Feedback.QUESTION_FIELD), (String) f.get(Feedback.ANSWER_TYPE_FIELD));
                feedback.setErrorMessage((String) f.get(Feedback.ERROR_MESSAGE_FIELD));
                feedback.setMinValue(convertNumberToInteger(f.get(Feedback.MIN_VALUE_FIELD)));
                feedback.setMaxValue(convertNumberToInteger(f.get(Feedback.MAX_VALUE_FIELD)));
                feedback.setTrueValue((String) f.get(Feedback.TRUE_VALUE_FIELD));
                feedback.setFalseValue((String) f.get(Feedback.FALSE_VALUE_FIELD));
                patientFeedback.addFeedback(feedback);

            });
        }
        return patientFeedback;
    }

    private Integer convertNumberToInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else if (obj instanceof Double) {
            return ((Double) obj).intValue();
        } else {
            return null;
        }
    }
}
