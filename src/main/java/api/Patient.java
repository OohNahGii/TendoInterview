package main.java.api;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Resource {
    private List<Name> names;

    public Patient(String id) {
        super(id);
        names = new ArrayList<>();
    }

    public void addName(Name name) {
        names.add(name);
    }

    public Name getPreferredName() {
        return names.get(0);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.PATIENT;
    }
}
