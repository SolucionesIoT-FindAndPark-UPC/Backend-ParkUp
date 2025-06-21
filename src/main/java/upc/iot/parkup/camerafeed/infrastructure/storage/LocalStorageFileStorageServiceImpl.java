package upc.iot.parkup.camerafeed.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import upc.iot.parkup.camerafeed.application.internal.outboundservices.storage.FileStorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalStorageFileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    public LocalStorageFileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String save(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = "";
        try {
            if (originalFilename == null || originalFilename.isEmpty()) {
                throw new FileStorageException("Failed to store empty file.");
            }
            if (originalFilename.contains("..")) {
                throw new FileStorageException("Cannot store file with relative path outside current directory " + originalFilename);
            }
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                fileExtension = originalFilename.substring(dotIndex);
            }

            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFilename);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            // Return the path relative to the application or a public URL if applicable
            // For local storage, returning the filename or a relative path might be sufficient
            // depending on how files are served. Here, we return the unique filename.
            // If files are served statically, this might be part of a URL.
            return uniqueFilename; // Or targetLocation.toString() if absolute path is needed internally

        } catch (IOException ex) {
            throw new FileStorageException("Failed to store file " + originalFilename, ex);
        }
    }
}
