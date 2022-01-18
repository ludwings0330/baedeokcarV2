package com.ludwings.baedeokcarv2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    String MY_PATH = "/Users/ludwings/study/baedeokcarV2/src/main/resources/static/images/";

    public void getFileInfo(MultipartFile file) {

        System.out.println("file.getcontentType() = " + file.getContentType());
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        System.out.println("file.getSize() = " + file.getSize());
    }

    // target 파일 서버에 추가하기
    public void saveImageFileToServer(MultipartFile file, String originFileName, String savedFileName) {

        Path copyOfLocation = Paths.get(MY_PATH + savedFileName);

        try {
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // target 파일 서버에서 삭제하기
    public void deleteImageFileFromServer(String savedFileName) {

        File file = new File(MY_PATH + savedFileName);

        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
