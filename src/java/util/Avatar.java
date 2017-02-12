package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jarosław Pawłowski
 */

public class Avatar {
    
    private final static String GRAVATAR_URL = "https://www.gravatar.com/avatar/";
    private String hash;

    private String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    private String md5Hex(String message) {
        try {
            MessageDigest md
                    = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return null;
    }
    
    public String getAvatar(String avatar) {
        if (avatar.isEmpty()) {
            hash = md5Hex("none");
            return GRAVATAR_URL + hash + ".jpg?forcedefault=1&s=100";
        }
        hash = md5Hex(avatar);
        return GRAVATAR_URL + hash + ".jpg?d=monsterid&s=100";
    }
}
