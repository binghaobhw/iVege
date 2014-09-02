package wbh.wilfred.ivege.service;

import java.security.MessageDigest;

public class ImageServiceImpl implements ImageService {
    @Override
    public String addImage(byte[] bytes) {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        StringBuilder hash = new StringBuilder();
        for (int i = 0 ; i < digest.length; ++i) {
            hash.append(Integer.toString((digest[i] & 0xff) + 0x100,
                    16).substring(1));
        }
        return hash.toString();
    }
}
