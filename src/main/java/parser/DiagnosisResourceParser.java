package main.java.parser;

import main.java.api.Diagnosis;
import main.java.api.Resource;

import java.util.List;
import java.util.Map;

public class DiagnosisResourceParser implements ResourceParser {
    @Override
    public String getResourceType() {
        return "Diagnosis";
    }

    @Override
    public Diagnosis parseResource(Map<String, Object> resourceData) {
        String id = (String) resourceData.get(Resource.ID_FIELD);
        Diagnosis diagnosis = new Diagnosis(id);
        if (resourceData.containsKey(Diagnosis.CODE_FIELD)) {
            Map<String, Object> codeData = (Map<String, Object>) resourceData.get(Diagnosis.CODE_FIELD);
            if (codeData.containsKey(Diagnosis.CODING_FIELD)) {
                List<Map<String, Object>> coding = (List<Map<String, Object>>) codeData.get(Diagnosis.CODING_FIELD);
                coding.forEach(codingData -> {
                    String system = (String) codingData.get(Diagnosis.Code.SYSTEM_FIELD);
                    String code = (String) codingData.get(Diagnosis.Code.CODE_FIELD);
                    String name = (String) codingData.get(Diagnosis.Code.NAME_FIELD);
                    diagnosis.addCode(new Diagnosis.Code(system, code, name));
                });
            }
        }
        return diagnosis;
    }
}
