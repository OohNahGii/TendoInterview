package test.java.parser;

import main.java.api.Resource;
import main.java.parser.InvalidResourceTypeException;
import main.java.parser.NoResourceParserFoundException;
import main.java.parser.ResourceParserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class ResourceParserFactoryTest {
    @Test
    public void testGetResourceParsers() {
        ResourceParserFactory.getSupportedResourceTypes().forEach(type -> {
            Map<String, Object> resourceData = Map.of(Resource.TYPE_FIELD, type);
            try {
                Assertions.assertNotNull(ResourceParserFactory.getResourceParser(resourceData));
            } catch (InvalidResourceTypeException | NoResourceParserFoundException e) {
                Assertions.fail("Exception should not have been thrown");
            }
        });
    }

    @Test
    public void testGetResourceParser_nullResourceData() throws InvalidResourceTypeException, NoResourceParserFoundException {
        try {
            ResourceParserFactory.getResourceParser(null);
            Assertions.fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testGetResourceParser_emptyResourceData() throws InvalidResourceTypeException, NoResourceParserFoundException {
        try {
            ResourceParserFactory.getResourceParser(Collections.emptyMap());
            Assertions.fail("IllegalArgumentException should have been thrown");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testGetResourceParser_invalidResourceData() throws NoResourceParserFoundException {
        Map<String, Object> resourceData = Map.of("resourceType", 123);
        try {
            ResourceParserFactory.getResourceParser(resourceData);
            Assertions.fail("InvalidResourceTypeException should have been thrown");
        } catch (InvalidResourceTypeException e) {}
    }

    @Test
    public void testGetResourceParser_noParserFound() throws InvalidResourceTypeException {
        Map<String, Object> resourceData = Map.of("resourceType", "InvalidResource");
        try {
            ResourceParserFactory.getResourceParser(resourceData);
            Assertions.fail("NoResourceParserFoundException should have been thrown");
        } catch (NoResourceParserFoundException e) {}
    }
}
