package com.shubham.Third.Project.service;

import com.shubham.Third.Project.entity.FileEntity;
import com.shubham.Third.Project.entity.UserEntity;
import com.shubham.Third.Project.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(MultipartFile multipartFile, UserEntity user) throws Exception {
        String storedFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        Path path = Paths.get(uploadDir + storedFileName);
        Files.createDirectories(path.getParent());
        Files.write(path, multipartFile.getBytes());

        FileEntity file = new FileEntity(); 
        file.setOriginalFilename(multipartFile.getOriginalFilename());
        file.setStoredFilename(storedFileName);
        file.setFilePath(path.toString());
        file.setUser(user);
        return fileRepository.save(file);
    }

    public List<FileEntity> getFilesByUser(UserEntity user) {
        return fileRepository.findByUser(user);
    }

    public FileEntity getFile(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(() -> new Exception("File not found"));
    }

    public void deleteFile(Long id) throws Exception {
        FileEntity file = getFile(id);
        Files.deleteIfExists(Paths.get(file.getFilePath()));
        fileRepository.delete(file);
    }
}
