package com.shubham.Third.Project.repository;

import com.shubham.Third.Project.entity.FileEntity;
import com.shubham.Third.Project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByUser(UserEntity user);
}
