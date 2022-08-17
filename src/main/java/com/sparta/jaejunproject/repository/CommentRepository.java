package com.sparta.jaejunproject.repository;

import com.sparta.jaejunproject.model.Camp;
import com.sparta.jaejunproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    List<Comment> findAllByCamp(Camp camp);

    List<Comment> findAllByMemberName(String nickname);

}