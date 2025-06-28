package com.piramidacafe.website.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
@RestController
@RequestMapping("/admin/api")
public class FileDownloadController {
    @Value("${app.image.upload.dir}")
    private String uploadDir;



    @GetMapping("/downloadAll")
    public void downloadAllFiles(HttpServletResponse response) throws IOException {
        String zipFileName = "images.zip";

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);

        Path folderPath = Paths.get(uploadDir);

        try (
                ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
                Stream<Path> paths = Files.walk(folderPath)
        ) {
            log.info("file download start... :"+ folderPath);
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        ZipEntry zipEntry = new ZipEntry(folderPath.relativize(filePath).toString());
                        try {
                            zos.putNextEntry(zipEntry);
                            Files.copy(filePath, zos);
                            zos.closeEntry();
                        } catch (IOException e) {
                        log.error("something went wrong while downloading files from volume");
                        }
                    });
        }
        log.info("files successfully downloaded from corresponding docker volume :"+ folderPath);
    }

}
