package com.dongne.dongnebe.global.service;

import com.dongne.dongnebe.global.exception.user.ForbiddenException;
import com.dongne.dongnebe.global.exception.user.ProfileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class GlobalService {

    @Value("${file.path}")
    private static String uploadFolder;
    public static void validatePermission(String userId, Authentication authentication) {
        if (!authentication.getName().equals(userId)) {
            throw new ForbiddenException("Access Is Forbidden");
        }
    }

    public static void uploadFile(MultipartFile file) {
        String imgFilePath = getImgFilePath(file);
        Path path;
        try {
            path = Paths.get(imgFilePath);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new ProfileUploadException(e.getMessage());
        }
    }

    public static String getImgFilePath(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + file.getOriginalFilename();
        return uploadFolder + imgFileName;
    }
}
