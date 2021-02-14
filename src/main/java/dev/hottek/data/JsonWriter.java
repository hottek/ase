package dev.hottek.data;

import com.google.gson.Gson;
import dev.hottek.data.model.Account;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonWriter {

    private final Gson gson;

    public JsonWriter() {
        this.gson = new Gson();
    }

    public void writeToFile(List<Account> accounts, String directoryPath) {
        String filePath = directoryPath + "\\test1.fm";
        Writer writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(filePath));
            gson.toJson(accounts, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
