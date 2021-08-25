package main.java.parser;

import main.java.api.Resource;

import java.util.Map;

/**
 * Interface for parsing unstructured data into a specified Resource
 */
public interface ResourceParser {
    /**
     * Get resource type associated with parser
     * @return Name of resource type associated with parser
     */
    String getResourceType();

    /**
     * Parse resourceData map and convert to associated resource
     * @param resourceData Map containing data to parse
     * @return Resource with field values set by data specified in resourceData map
     */
    Resource parseResource(Map<String, Object> resourceData);
}
