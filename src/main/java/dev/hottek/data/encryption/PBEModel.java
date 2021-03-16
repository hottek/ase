package dev.hottek.data.encryption;

public class PBEModel {
    private final byte[] iv;
    private final byte[] ciphertext;

    public PBEModel(byte[] iv, byte[] ciphertext) {
        this.iv = iv;
        this.ciphertext = ciphertext;
    }

    public byte[] getIv() {
        return iv;
    }

    public byte[] getCiphertext() {
        return ciphertext;
    }
}
