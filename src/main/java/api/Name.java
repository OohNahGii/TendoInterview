package main.java.api;

import java.util.ArrayList;
import java.util.List;

public class Name {
    public static final String FAMILY_FIELD = "family";
    public static final String GIVEN_FIELD = "given";
    public static final String NAME_FIELD = "name";
    public static final String TEXT_FIELD = "text";

    private String text;
    private String family;
    private List<String> given;

    public Name(String text, String family, List<String> given) {
        this.text = text;
        this.family = family;
        this.given = given;
    }

    public void addGivenNames(List<String> given) {
        this.given.addAll(given);
    }

    public String getFullName() {
        return text;
    }

    public String getFamilyName() {
        return family;
    }

    public String getPreferredGivenName() {
        return given.get(0);
    }
}
