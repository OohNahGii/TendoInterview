package test.java;

import main.java.PatientFeedbackCollector;
import main.java.api.Feedback;
import main.java.api.PatientFeedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Scanner;

public class PatientFeedbackCollectorTest {
    @Test
    public void testCollectFeedback_integer() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine()).thenReturn("1");

        PatientFeedback patientFeedback = new PatientFeedback();
        Feedback feedback = new Feedback("Enter Integer", Integer.class.getSimpleName());
        patientFeedback.addFeedback(feedback);

        PatientFeedbackCollector feedbackCollector = new PatientFeedbackCollector(printStream, scanner);
        feedbackCollector.addFeedbackToCollect(patientFeedback);
        feedbackCollector.collectFeedback();

        Assertions.assertEquals(1, feedback.getAnswer());
    }

    @Test
    public void testCollectFeedback_integerInvalidNumber() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Mockito.doNothing().when(printStream).println(Mockito.anyString());
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine()).thenReturn("Invalid").thenReturn("1");

        PatientFeedback patientFeedback = new PatientFeedback();
        Feedback feedback = new Feedback("Enter Integer", Integer.class.getSimpleName());
        String errorMessage = "Invalid Integer";
        feedback.setErrorMessage(errorMessage);
        patientFeedback.addFeedback(feedback);

        PatientFeedbackCollector feedbackCollector = new PatientFeedbackCollector(printStream, scanner);
        feedbackCollector.addFeedbackToCollect(patientFeedback);
        feedbackCollector.collectFeedback();

        Assertions.assertEquals(1, feedback.getAnswer());
        Mockito.verify(printStream, Mockito.times(1)).println(errorMessage);
    }

    @Test
    public void testCollectFeedback_integerWithMinMax() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Mockito.doNothing().when(printStream).println(Mockito.anyString());
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextLine()).thenReturn("11").thenReturn("-1").thenReturn("1");

        PatientFeedback patientFeedback = new PatientFeedback();
        Feedback feedback = new Feedback("Enter Integer", Integer.class.getSimpleName());
        String errorMessage = "Invalid Integer";
        feedback.setErrorMessage(errorMessage);
        feedback.setMinValue(1);
        feedback.setMaxValue(10);
        patientFeedback.addFeedback(feedback);

        PatientFeedbackCollector feedbackCollector = new PatientFeedbackCollector(printStream, scanner);
        feedbackCollector.addFeedbackToCollect(patientFeedback);
        feedbackCollector.collectFeedback();

        Assertions.assertEquals(1, feedback.getAnswer());
        Mockito.verify(printStream, Mockito.times(2)).println(errorMessage);
    }

    // TODO - test boolean feedback, string feedback, list of feedback
}
