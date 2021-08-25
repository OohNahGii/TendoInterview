package main.java.api;

import java.text.MessageFormat;
import java.util.List;

public class Feedback {
    public static final String ANSWER_FIELD = "answer";
    public static final String ANSWER_TYPE_FIELD = "answerType";
    public static final String ERROR_MESSAGE_FIELD = "errorMessage";
    public static final String FALSE_VALUE_FIELD = "falseValue";
    public static final String MAX_VALUE_FIELD = "maxValue";
    public static final String MIN_VALUE_FIELD = "minValue";
    public static final String QUESTION_FIELD = "question";
    public static final String TRUE_VALUE_FIELD = "trueValue";

    private String question;
    private Object answer;
    private String answerType;

    private Integer minValue;
    private Integer maxValue;
    private String trueValue;
    private String falseValue;
    private String errorMessage;

    public Feedback(String question, String answerType) {
        this.question = question;
        this.answerType = answerType;
    }

    public void formatQuestion(Object... args) {
        question = MessageFormat.format(question, args);
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public Object getAnswer() {
        return answer;
    }

    public String getAnswerType() {
        return answerType;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public String getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }

    public String getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
