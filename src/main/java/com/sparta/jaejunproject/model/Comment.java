package com.sparta.jaejunproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.jaejunproject.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tml_comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String memberName;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;


    public Comment(Camp camp, String memberName, CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.camp = camp;
        this.memberName = memberName;
    }


    public void setComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
