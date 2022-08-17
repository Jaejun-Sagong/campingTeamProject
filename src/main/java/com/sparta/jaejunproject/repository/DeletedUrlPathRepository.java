package com.sparta.jaejunproject.repository;

import com.sparta.jaejunproject.model.DeletedUrlPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedUrlPathRepository extends JpaRepository<DeletedUrlPath,Long> {
}
