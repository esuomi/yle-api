package io.induct.yle.api.media;

import io.induct.yle.api.media.domain.DecryptedPlayout;
import io.induct.yle.api.media.domain.EncryptedPlayout;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @since 2015-08-25
 */
public class Decrypter {

    private final String streamKey;
    private final Cipher cipher;

    @Inject
    public Decrypter(@Named("yle.api.streamKey") String streamKey) {
        this.streamKey = streamKey;

        try {
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            throw new RuntimeException("Failed to instantiate AES/CBC/PKCS5Padding cipher, are you sure you are using a supported JVM?");
        }
    }

    public DecryptedPlayout decryptPlayout(EncryptedPlayout playout) {
        return new DecryptedPlayout(
                playout.getHeight(),
                playout.getWidth(),
                playout.getProtocol(),
                playout.isMultibitrate(),
                playout.isLive(),
                playout.getFormatOf(),
                playout.getType(),
                playout.getProtectionType(),
                decryptUrl(playout.getUrl()));
    }

    private String decryptUrl(String url) {
        byte[] baseDecoded = Base64.getDecoder().decode(url);
        byte[] iv = Arrays.copyOfRange(baseDecoded, 0, 16);
        byte[] msg = Arrays.copyOfRange(baseDecoded, 16, baseDecoded.length);

        SecretKeySpec secretKeySpec;
        try {
            secretKeySpec = new SecretKeySpec(streamKey.getBytes("UTF-8"), "AES");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to get bytes of stream decryption secret key using UTF-8 encoding, are you sure you are using a supported JVM?");
        }
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        String decrypted;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] resultBytes = cipher.doFinal(msg);
            decrypted = new String(resultBytes);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException("Failed to decrypt URL", e);
        }
        return decrypted;
    }
}
