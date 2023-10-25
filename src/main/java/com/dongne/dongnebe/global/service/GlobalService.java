package com.dongne.dongnebe.global.service;

import com.dongne.dongnebe.global.exception.user.ForbiddenException;
import com.dongne.dongnebe.global.exception.user.ProfileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class GlobalService {
    private static String uploadFolder;

    public static int getTotalPageCount(int boardSize, Pageable pageable) {
        return (boardSize % pageable.getPageSize()) == 0 ?
                (boardSize / pageable.getPageSize()) : (boardSize / pageable.getPageSize()) + 1;
    }

    @Value("${file.path}")
    public void setUploadFolder(String value) {
        uploadFolder = value;
    }
    public static void validatePermission(String userId, Authentication authentication) {
        if (!authentication.getName().equals(userId)) {
            throw new ForbiddenException("Access Is Forbidden");
        }
    }

    public static void uploadFile(MultipartFile file) {
        if (!file.isEmpty()) {
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
    }

    public static String getImgFilePath(MultipartFile file) {
        if (!file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String imgFileName = uuid + "_" + file.getOriginalFilename();
            return uploadFolder + imgFileName;
        }
        return null;
    }

    public static String formatLocalDateTimeToString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
