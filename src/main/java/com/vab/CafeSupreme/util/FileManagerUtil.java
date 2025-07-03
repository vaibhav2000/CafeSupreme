package com.vab.CafeSupreme.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManagerUtil {

    public static void saveFile(MultipartFile file, String filename) {

        try {
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(filename + ".webp");
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteFile(String filename) {

        try {

            File file = new File("uploads\\"+filename + ".webp");
            file.delete();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
