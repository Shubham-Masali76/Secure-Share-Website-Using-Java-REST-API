package com.shubham.Third.Project.controller;

import com.shubham.Third.Project.entity.FileEntity;
import com.shubham.Third.Project.entity.UserEntity;
import com.shubham.Third.Project.service.FileService;
import com.shubham.Third.Project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File; 
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws Exception {
        UserEntity user = userService.getById(userId).orElseThrow(() -> new Exception("User not found"));
        fileService.saveFile(file, user);
        return "File uploaded successfully!";
    }

    @GetMapping("/user/{userId}")
    public List<FileEntity> listUserFiles(@PathVariable Long userId) throws Exception {
        UserEntity user = userService.getById(userId).orElseThrow(() -> new Exception("User not found"));
        return fileService.getFilesByUser(user);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws Exception {
        FileEntity fileEntity = fileService.getFile(id); 
        File systemFile = new File(fileEntity.getFilePath()); 

        if (!systemFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + fileEntity.getOriginalFilename() + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(systemFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(systemFile));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        fileService.deleteFile(id);
        return "File deleted successfully!";
    }
}
