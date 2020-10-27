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
    private final String ciphertextFilePath;
    private final String ivFilePath;
    private final PasswordBasedEncryption passwordBasedEncryption;

    public DataHandler(String password) {
        this.ciphertextFilePath = "test.fm";
        this.ivFilePath = "iv.fm";
        this.passwordBasedEncryption = new PasswordBasedEncryption(password.toCharArray());
    }

    public Account loadData() {
        PBEModel pbeModel = readEncryptedFromFile();
        byte[] bytes = new byte[0];
        try {
            bytes = passwordBasedEncryption.decrypt(pbeModel);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return convertBytesToData(bytes);
    }

    public void saveData(Account account) {
        byte[] accountBytes = convertDataToBytes(account);
        PBEModel pbeModel = null;
        try {
            pbeModel = passwordBasedEncryption.encrypt(accountBytes);
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

    private Account convertBytesToData(byte[] bytes) {
        String data = new String(bytes);
        String[] strings = data.split("\n");
        return new Account(strings[0].substring(1), Float.parseFloat(strings[1].substring(1)));
    }

    private byte[] convertDataToBytes(Account account) {
        String accountString = "n" + account.getName() + "\nb" + account.getBalance();
        return accountString.getBytes();
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
