package upc.iot.parkup.camerafeed.application.internal.outboundservices.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String save(MultipartFile file);
}
