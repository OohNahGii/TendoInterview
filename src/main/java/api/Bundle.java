package main.java.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bundle extends Resource {
    public static final String ENTRY_FIELD = "entry";
    public static final String RESOURCE_FIELD = "resource";
    public static final String TIMESTAMP_FIELD = "timestamp";

    private String timestamp;
    private List<Resource> entry;

    public Bundle(String id, String timestamp) {
        super(id);
        this.timestamp = timestamp;
        entry = new ArrayList<>();
    }

    @Override
    public ResourceType getType() {
        return ResourceType.BUNDLE;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void addResource(Resource resource) {
        entry.add(resource);
    }

    public List<Resource> getResourcesByType(ResourceType type) {
        return entry.stream()
                .filter(resource -> resource.getType().equals(type))
                .collect(Collectors.toList());
    }
}
