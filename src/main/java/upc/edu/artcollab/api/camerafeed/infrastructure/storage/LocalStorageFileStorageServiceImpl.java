package upc.edu.artcollab.api.camerafeed.infrastructure.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upc.edu.artcollab.api.camerafeed.application.internal.outboundservices.storage.FileStorageService;
import upc.edu.artcollab.api.camerafeed.infrastructure.storage.exceptions.FileStorageException;

import java.io.IOException;
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
        if (file.getOriginalFilename() == null) {
            throw new FileStorageException("File name cannot be null");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        int i = originalFilename.lastIndexOf('.');
        if (i > 0) {
            extension = originalFilename.substring(i);
        }
        String uniqueFilename = UUID.randomUUID().toString() + extension;

        try {
            if (uniqueFilename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + uniqueFilename);
            }

            Path targetLocation = this.fileStorageLocation.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + uniqueFilename + ". Please try again!", ex);
        }
    }
}
