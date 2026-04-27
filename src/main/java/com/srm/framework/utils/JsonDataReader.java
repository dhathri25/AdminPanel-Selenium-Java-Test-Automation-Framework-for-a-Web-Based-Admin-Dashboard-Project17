
package com.srm.framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srm.framework.model.FormData;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class JsonDataReader {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonDataReader() {
    }

    public static Object[][] getFormData(String resourceName) {
        List<FormData> formDataList = readFormData(resourceName);
        Object[][] data = new Object[formDataList.size()][1];
        for (int index = 0; index < formDataList.size(); index++) {
            data[index][0] = formDataList.get(index);
        }
        return data;
    }

    private static List<FormData> readFormData(String resourceName) {
        try (InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to locate JSON resource: " + resourceName);
            }
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<List<FormData>>() { });
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read JSON resource: " + resourceName, exception);
        }
    }
}
