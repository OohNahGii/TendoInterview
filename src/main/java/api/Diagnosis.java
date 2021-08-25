package main.java.api;

import java.util.ArrayList;
import java.util.List;

public class Diagnosis extends Resource {
    public static final String CODE_FIELD = "code";
    public static final String CODING_FIELD = "coding";

    private List<Code> coding;

    public Diagnosis(String id) {
        super(id);
        coding = new ArrayList<>();
    }

    public void addCode(Code code) {
        coding.add(code);
    }

    public List<Code> getCoding() {
        return coding;
    }

    @Override
    public ResourceType getType() {
        return ResourceType.DIAGNOSIS;
    }

    public static class Code {
        public static final String CODE_FIELD = "code";
        public static final String NAME_FIELD = "name";
        public static final String SYSTEM_FIELD = "system";

        private String system;
        private String code;
        private String name;

        public Code(String system, String code, String name) {
            this.system = system;
            this.code = code;
            this.name = name;
        }

        public String getSystem() {
            return system;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
