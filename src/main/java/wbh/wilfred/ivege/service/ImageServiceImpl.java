package wbh.wilfred.ivege.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    final static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public String addImage(byte[] bytes, String originalName) throws Exception {
        String fileName = hash(bytes) + extension(originalName);
        String pathName = pathName(fileName);
        File file = new File(pathName);
        logger.debug("File path: {}", file.getAbsolutePath());
        save(bytes, file);
        return fileName;
    }

    private void save(byte[] bytes, File file) throws IOException {
        if (!file.exists()) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                out.write(bytes);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } else {
            logger.debug("File exists");
        }
    }

    private String extension(String fileName) {
        return StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, '.'));
    }

    private String pathName(String fileName) {
        return "images/" + fileName;
    }

    private String hash(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }
}
