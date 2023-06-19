package be.joshuaschroijen.alfrescito.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import be.joshuaschroijen.alfrescito.model.AlDoc;
import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;
import be.joshuaschroijen.alfrescito.repository.AlDocRepository;
import be.joshuaschroijen.alfrescito.repository.AlfrescitoUserRepository;

@RestController
@RequestMapping("/aldocs")
public class AlDocController {
    private AlfrescitoUserRepository userRepository;
    private AlDocRepository alDocRepository;

    public AlDocController(AlfrescitoUserRepository userRepository, AlDocRepository alDocRepository) {
        this.userRepository = userRepository;
        this.alDocRepository = alDocRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<AlDoc>> getAlDocsList() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AlfrescitoUser user = userRepository.findOneByUsername(userDetails.getUsername());
        return ResponseEntity.ok(this.alDocRepository.findByOwner(user));
    }

    @PostMapping("/")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        writeFileToDisk(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) throws IOException {
        Optional<AlDoc> optionalAlDoc = alDocRepository.findById(id);
        if (!optionalAlDoc.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AlDoc alDoc = optionalAlDoc.get();
        String filePath = alDoc.getFilePath();
        Path path = Paths.get(filePath);

        Resource resource = new FileSystemResource(path);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlDoc(@PathVariable Long id) throws Exception {
        this.alDocRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();        
    }

    private void writeFileToDisk(MultipartFile file) throws Exception {
        String uploadDir = "/path/to/uploads";
        Files.createDirectories(Path.of(uploadDir));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Path.of(uploadDir, fileName);

        Files.write(filePath, file.getBytes(), StandardOpenOption.WRITE);
    }
}
