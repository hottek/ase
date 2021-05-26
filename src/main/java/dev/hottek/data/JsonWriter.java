package dev.hottek.data;

import com.google.gson.Gson;
import dev.hottek.data.model.SafeFormat;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWriter {

    private final Gson gson;

    public JsonWriter() {
        this.gson = new Gson();
    }

    public void writeToFile(SafeFormat data, String filePath) {
        Writer writer;
        try {
            writer = Files.newBufferedWriter(Paths.get(filePath));
            gson.toJson(data, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeToString(SafeFormat data) {
        return gson.toJson(data);
    }
}
