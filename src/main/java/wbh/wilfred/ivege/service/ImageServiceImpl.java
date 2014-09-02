package wbh.wilfred.ivege.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String addImage(byte[] bytes) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        StringBuilder hash = new StringBuilder();
        for (byte aDigest : digest) {
            hash.append(Integer.toString((aDigest & 0xff) + 0x100,
                    16).substring(1));
        }
        return hash.toString();
    }
}
