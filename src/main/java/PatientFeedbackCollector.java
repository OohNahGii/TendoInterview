package main.java;

import main.java.api.PatientFeedback;

import java.io.PrintStream;
import java.util.Scanner;

public class PatientFeedbackCollector {
    private PatientFeedback patientFeedback;
    private PrintStream printStream;
    private Scanner scanner;

    public PatientFeedbackCollector(PrintStream printStream, Scanner scanner) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public void addFeedbackToCollect(PatientFeedback patientFeedback) {
        this.patientFeedback = patientFeedback;
    }

    public void collectFeedback() {
        patientFeedback.getFeedback().forEach(f -> {
            printStream.println(f.getQuestion());
            String answerType = f.getAnswerType();
            if (Integer.class.getSimpleName().equals(answerType)) {
                f.setAnswer(scanInteger(f.getMinValue(), f.getMaxValue(), f.getErrorMessage()));
            } else if (Boolean.class.getSimpleName().equals(answerType)) {
                f.setAnswer(scanBoolean(f.getTrueValue(), f.getFalseValue(), f.getErrorMessage()));
            } else {
                f.setAnswer(scanner.nextLine());
            }
        });
    }

    private Integer scanInteger(Integer min, Integer max, String errorMessage) {
        Integer val = null;
        while (val == null) {
            try {
                String input = scanner.nextLine();
                int parsedInput = Integer.parseInt(input);
                if ((min != null && parsedInput < min) || (max != null && parsedInput > max)) {
                    if (errorMessage != null && !errorMessage.isEmpty()) {
                        printStream.println(errorMessage);
                    }
                } else {
                    val = parsedInput;
                }
            } catch (NumberFormatException e) {
                if (errorMessage != null && !errorMessage.isEmpty()) {
                    printStream.println(errorMessage);
                }
            }
        }
        return val;
    }

    private Boolean scanBoolean(String trueValue, String falseValue, String errorMessage) {
        Boolean val = null;
        while (val == null) {
            String input = scanner.nextLine();
            if (input.equals(trueValue) || input.equals(falseValue)) {
                val = input.equals(trueValue);
            } else {
                if (errorMessage != null && !errorMessage.isEmpty()) {
                    printStream.println(errorMessage);
                }
            }
        }
        return val;
    }

    public void displaySummary() {
        printStream.println(patientFeedback.getSummary());
        patientFeedback.getFeedback().forEach(f -> {
            printStream.println(f.getQuestion());
            printStream.println(f.getAnswer());
        });
    }

    public void run() {
        collectFeedback();
        displaySummary();
    }

    public PatientFeedback getPatientFeedback() {
        return patientFeedback;
    }
}
