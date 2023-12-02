<<<<<<< HEAD:src/main/java/Salvation/Clinic/services/Others/FileStorageService.java
package Salvation.Clinic.services.Others;
=======
package delivery.management.services.others;
>>>>>>> origin/master:src/main/java/delivery/management/services/others/FileStorageService.java

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
<<<<<<< HEAD:src/main/java/Salvation/Clinic/services/Others/FileStorageService.java
    private final String uploadDir = "C:/Users/USER/Documents/uploads";
=======
    private final String uploadDir = "C:/Users/SAMSUNG/Documents/uploads";
>>>>>>> origin/master:src/main/java/delivery/management/services/others/FileStorageService.java

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path targetLocation = Paths.get(uploadDir).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
<<<<<<< HEAD:src/main/java/Salvation/Clinic/services/Others/FileStorageService.java
}
=======
}
>>>>>>> origin/master:src/main/java/delivery/management/services/others/FileStorageService.java
