package main.java.parser;

import main.java.api.Bundle;
import main.java.api.Resource;
import main.java.api.ResourceType;

import java.util.List;
import java.util.Map;

public class BundleResourceParser implements ResourceParser {
    @Override
    public String getResourceType() {
        return ResourceType.BUNDLE.getName();
    }

    @Override
    public Bundle parseResource(Map<String, Object> resourceData) {
        String id = (String) resourceData.get(Resource.ID_FIELD);
        String timestamp = (String) resourceData.get(Bundle.TIMESTAMP_FIELD);
        Bundle bundle = new Bundle(id, timestamp);
        if (resourceData.containsKey(Bundle.ENTRY_FIELD)) {
            List<Map<String, Object>> resources = (List<Map<String, Object>>) resourceData.get(Bundle.ENTRY_FIELD);
            resources.forEach(resource -> {
                if (resource.containsKey(Bundle.RESOURCE_FIELD)) {
                    try {
                        Map<String, Object> data = (Map<String, Object>) resource.get(Bundle.RESOURCE_FIELD);
                        ResourceParser parser = ResourceParserFactory.getResourceParser(data);
                        bundle.addResource(parser.parseResource(data));
                    } catch (InvalidResourceTypeException | NoResourceParserFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return bundle;
    }
}
