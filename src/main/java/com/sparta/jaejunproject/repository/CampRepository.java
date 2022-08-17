package com.sparta.jaejunproject.repository;

import com.sparta.jaejunproject.model.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampRepository extends JpaRepository<Camp, Long> {

}
