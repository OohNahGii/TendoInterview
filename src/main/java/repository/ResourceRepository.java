package main.java.repository;

import main.java.api.Resource;

import java.io.IOException;
import java.util.UUID;

/**
 * Interface for storing and retrieving a specified Resource to and from a data store
 * @param <T> The target Resource type
 */
public interface ResourceRepository<T extends Resource> {
    /**
     * Generate id for a Resource. Intended to be used to set a Resource id on save.
     * @return Randomly generated id
     */
    default String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * Save Resource to data store
     * @param resource
     * @return Id of saved Resource
     * @throws IOException on error writing to data store
     */
    String saveResource(T resource) throws IOException;

    /**
     * Retrieve Resource from data store
     * @param id Id of Resource to retrieve
     * @return Retrieved Resource
     */
    T getResource(String id);
}
