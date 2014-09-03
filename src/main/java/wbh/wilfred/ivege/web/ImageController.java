package wbh.wilfred.ivege.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wbh.wilfred.ivege.service.ImageService;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestPart("image") MultipartFile image) throws Exception {
        if (image.isEmpty()) {
            throw new RuntimeException();
        }
        byte[] bytes = image.getBytes();
        return imageService.addImage(bytes, image.getOriginalFilename());
    }
}
