package com.shubham.Third.Project.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFilename;
    private String storedFilename;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getOriginalFilename() { 
        return originalFilename; 
    }

    public void setOriginalFilename(String originalFilename) { 
        this.originalFilename = originalFilename; 
    }

    public String getStoredFilename() { 
        return storedFilename; 
    }

    public void setStoredFilename(String storedFilename) { 
        this.storedFilename = storedFilename; 
    }

    public String getFilePath() { 
        return filePath; 
    }

    public void setFilePath(String filePath) { 
        this.filePath = filePath; 
    }

    public UserEntity getUser() { 
        return user; 
    }

    public void setUser(UserEntity user) { 
        this.user = user; 
    }
}
 