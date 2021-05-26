package dev.hottek.data;

import com.google.gson.Gson;
import dev.hottek.data.model.SafeFormat;

import java.io.*;

public class JsonReader {

    private final Gson gson;

    public JsonReader() {
        this.gson = new Gson();
    }

    public SafeFormat readJsonFromFile(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(filePath)
            );
            return gson.fromJson(bufferedReader, SafeFormat.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SafeFormat readJsonFromString(String data) {
        Reader stringReader = new StringReader(data);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        return gson.fromJson(bufferedReader, SafeFormat.class);
    }
}
