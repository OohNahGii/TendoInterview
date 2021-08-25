package main.java.api;

/**
 * Skeletal definition of a Resource, containing only the required fields
 */
public abstract class Resource {
    public static final String ID_FIELD = "id";
    public static final String REFERENCE_FIELD = "reference";
    public static final String TYPE_FIELD = "resourceType";

    private String id;

    public Resource() {}

    public Resource(String id) {
        this.id = id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static String createReferenceId(String type, String id) {
        return type + "/" + id;
    }

    public abstract ResourceType getType();
}
