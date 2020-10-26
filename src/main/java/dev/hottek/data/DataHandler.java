package dev.hottek.data;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataLoader {
    private String filePath;

    public DataLoader() {
        this.filePath = "test.fm";
    }

    public Account loadData() {
        InputStream is = getFileFromResourceAsStream();
        Account account = printInputStream(is);

        return account;
    }

    private InputStream getFileFromResourceAsStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        } else {
            return inputStream;
        }
    }

    private Account printInputStream(InputStream is) {
        String name = "";
        String balance = "";
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("n")) name = line;
                if (line.startsWith("b")) balance = line;
            }
            return new Account(name.substring(1), Float.parseFloat(balance.substring(1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Account(name, Float.parseFloat(balance));
    }
}
