package be.joshuaschroijen.alfrescito.utils;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class FilePathSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Path path = Paths.get(value);
        String fileName = path.getFileName().toString();
        jsonGenerator.writeString(fileName);
    }
}