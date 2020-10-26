package dev.hottek.data;

import dev.hottek.data.encryption.PBEModel;
import dev.hottek.data.encryption.PasswordBasedEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;


public class DataHandler {
    private String ciphertextFilePath;
    private String ivFilePath;
    private PasswordBasedEncryption passwordBasedEncryption;

    public DataHandler(String password) {
        this.ciphertextFilePath = "test.fm";
        this.ivFilePath = "iv.fm";
        this.passwordBasedEncryption = new PasswordBasedEncryption(password.toCharArray());
    }

    public String loadData() {
        PBEModel pbeModel = readEncryptedFromFile();
        byte[] bytes = new byte[0];
        try {
            bytes = passwordBasedEncryption.decrypt(pbeModel);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return resolveInputStream(bytes);
    }

    public void saveData(String account) {
        PBEModel pbeModel = null;
        try {
            pbeModel = passwordBasedEncryption.encrypt(account.getBytes());
        } catch (InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert pbeModel != null;
        writeEncryptedToFile(pbeModel);
    }

    private PBEModel readEncryptedFromFile() {
        PBEModel pbeModel = null;
        try {
            InputStream ciphertextInputStream = new FileInputStream(ciphertextFilePath);
            InputStream ivInputStream = new FileInputStream(ivFilePath);
            byte[] ciphertextBytes = readBytesFromInputStream(ciphertextInputStream);
            byte[] ivBytes = readBytesFromInputStream(ivInputStream);
            pbeModel = new PBEModel(ivBytes, ciphertextBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pbeModel;
    }

    private void writeEncryptedToFile(PBEModel pbeModel) {
        byte[] ciphertextBytes = pbeModel.getCiphertext();
        byte[] ivBytes = pbeModel.getIv();
        try {
            OutputStream ciphertextOutputStream = new FileOutputStream(ciphertextFilePath);
            OutputStream ivOutputStream = new FileOutputStream(ivFilePath);
            ciphertextOutputStream.write(ciphertextBytes);
            ivOutputStream.write(ivBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readBytesFromInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        try {
            int i;
            do {
                i = inputStream.read(buffer);
            } while (i != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Byte[] bufferWithoutZeros = reduceBuffer(buffer);
        return toPrimitives(bufferWithoutZeros);
    }

    private String resolveInputStream(byte[] bytes) {
        String data = new String(bytes);
        return data;
        /*String name = "";
        String balance = "";
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("n")) name = line;
                if (line.startsWith("b")) balance = line;
            }
            return new Account("l", 127.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Account(name, Float.parseFloat(balance));*/
    }

    private Byte[] reduceBuffer(byte[] buffer) {
        ArrayList<Byte> bytes = new ArrayList<>();
        for (byte b : buffer) {
            if (b != 0) {
                bytes.add(b);
            }
        }
        return bytes.toArray(new Byte[bytes.size()]);
    }

    private byte[] toPrimitives(Byte[] oBytes)
    {
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }
}
