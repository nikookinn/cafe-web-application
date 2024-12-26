package com.piramidacafe.website.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${app.image.upload.dir:./static/images}")
    private String uploadDir;

    // Store the file after processing (cropping and resizing)
    public String storeFile(MultipartFile file, String subDirectory) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null.");
        }

        // Full target directory
        String targetDir = uploadDir + "/" + subDirectory;

        // Create a unique file name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(targetDir, fileName);

        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get(targetDir));

            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // If it's not "app_image" directory, crop and resize the image
            if (!subDirectory.equals("app_images")) {
                BufferedImage croppedImage = cropToSquare(originalImage);
                originalImage = resizeImage(croppedImage, 800);
            } else {
                // Only resize if it's "app_image" directory, no cropping
                originalImage = resizeImage(originalImage, 800);
            }

            // Compress and save the image
            saveCompressedImage(originalImage, filePath);

            // Return the file's URL
            return "/images/" + subDirectory + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not save file: " + file.getOriginalFilename(), e);
        }
    }

    // Crop the image to a square (centered)
    private BufferedImage cropToSquare(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Get the smaller of the two dimensions
        int size = Math.min(width, height);

        // Calculate the coordinates to crop the image from the center
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        // Create and return the cropped image
        return originalImage.getSubimage(x, y, size, size);
    }

    // Resize the image while maintaining the aspect ratio
    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth) {
        // Get the size of the cropped image
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Calculate the new height to maintain the aspect ratio
        int newHeight = (height * newWidth) / width;

        // Create a scaled instance of the image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new buffered image with the correct size
        BufferedImage bufferedScaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedScaledImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bufferedScaledImage;
    }

    // Compress and save the image as a JPEG
    private void saveCompressedImage(BufferedImage image, Path path) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            // Save the image in JPEG format with compression
            ImageIO.write(image, "JPEG", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Write the image to the file system
            Files.write(path, imageBytes);
            logger.info("Image successfully stored on path " + path);
        }
    }

    // Delete an existing image from the file system
    public void deleteOldImage(String existingImageUrl, String subDirectory) {
        String fileName = null;
        if (existingImageUrl != null && !existingImageUrl.isEmpty()) {
            if (subDirectory.equals("app_images")){
                 fileName = existingImageUrl.replace("/images/app_images", "");
            } else if (subDirectory.equals("menu_images")) {
                fileName = existingImageUrl.replace("/images/menu_images", "");
            }


            // Construct the file path
            String filePath = uploadDir + "/" + subDirectory + "/" + fileName;

            File file = new File(filePath);

            // Check if the file exists and delete it
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    logger.info("Failed to delete old image: " + filePath);
                } else {
                    logger.info("Image Successfully deleted on path: " + filePath);
                }
            } else {
                logger.info("File does not exist: " + filePath);
            }
        }
    }
}
