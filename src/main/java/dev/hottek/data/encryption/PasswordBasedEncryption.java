package dev.hottek.data.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

// https://stackoverflow.com/a/992413/12141469
public class PasswordBasedEncryption {

    private final int DERIVATION_ITERATION_COUNT = 65536;
    private final int KEY_SIZE = 256;
    private final byte[] salt = {
            (byte)0xc8, (byte)0x73, (byte)0x41, (byte)0x8c,
            (byte)0x7c, (byte)0xd8, (byte)0xe3, (byte)0x90
    };
    private SecretKey secretKey;
    private Cipher cipher;

    public PasswordBasedEncryption(char[] password) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(password, salt, DERIVATION_ITERATION_COUNT, KEY_SIZE);
            byte[] secret = keyFactory.generateSecret(keySpec).getEncoded();
            this.secretKey = new SecretKeySpec(secret, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public PBEModel encrypt(byte[] plaintext) throws InvalidKeyException, InvalidParameterSpecException,
                                                    IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        AlgorithmParameters algorithmParameters = this.cipher.getParameters();
        byte[] iv = algorithmParameters.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] ciphertext = this.cipher.doFinal(plaintext);
        return new PBEModel(iv, ciphertext);
    }

    public byte[] decrypt(PBEModel pbeModel) throws InvalidAlgorithmParameterException, InvalidKeyException,
                                                    BadPaddingException, IllegalBlockSizeException {
        this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey, new IvParameterSpec(pbeModel.getIv()));
        return this.cipher.doFinal(pbeModel.getCiphertext());
    }
}
