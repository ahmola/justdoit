package com.example.prac.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class postController {

    @PostMapping("/picture/send")
    public String imageUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println("Get Image!");

        if(!multipartFile.isEmpty()){
            String fullPath = "/Users/park/Documents/study/prac 2/src/main/resources/static/Image/"
                    + multipartFile.getOriginalFilename();
            System.out.println("파일 저장!");
            multipartFile.transferTo(new File(fullPath));
        }

        return "success!";
    }
}
