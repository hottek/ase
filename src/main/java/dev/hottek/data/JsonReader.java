package dev.hottek.data;

import com.google.gson.Gson;
import dev.hottek.data.model.Account;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    private final Gson gson;

    public JsonReader() {
        this.gson = new Gson();
    }

    public List<Account> readJsonFromFile(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(filePath)
            );
            return Arrays.asList(gson.fromJson(bufferedReader, Account[].class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> readJsonFromString(String data) {
        Reader stringReader = new StringReader(data);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        return Arrays.asList(gson.fromJson(bufferedReader, Account[].class));
    }
}
