package com.sparta.jaejunproject.dto;

import com.sparta.jaejunproject.model.Comment;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;

    private String content;

    private String memberName;


    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberName = comment.getMemberName();
    }
}