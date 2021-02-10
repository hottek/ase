package dev.hottek.data;

import com.google.gson.Gson;
import dev.hottek.data.model.Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    private final Gson gson;

    public JsonReader() {
        this.gson = new Gson();
    }

    public void readJsonFromFile(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(filePath)
            );
            List<Account> account = Arrays.asList(gson.fromJson(bufferedReader, Account[].class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
