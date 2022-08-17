package com.sparta.jaejunproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.jaejunproject.dto.CampRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "tbl_camp")
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Camp { // 생성,수정 시간을 자동으로 만들어줍니다.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String location;

    @Column(nullable = false)
    private String review;
    // 추가됨
//    @Column
//    private String urlPath;
//    //
    @Column
    private String nickname;

    @OneToMany(mappedBy = "camp", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)  //부모가 삭제될 때 자식들도 다 삭제되는 어노테이션
    @JsonManagedReference //DB연관관계 무한회귀 방지
    private List<Comment> commentList;

    public Camp(CampRequestDto requestDto, String nickName) {
        this.nickname = nickName;
        this.title = requestDto.getTitle();
        this.location = requestDto.getLocation();
        this.review = requestDto.getReview();
    }
//    public Camp(CampRequestDto requestDto, String nickName, String urlPath) {
//        this.nickname = nickName;
//        this.title = requestDto.getTitle();
//        this.review = requestDto.getReview();
//        this.urlPath = urlPath;
//    }
    public void update(CampRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.location = requestDto.getLocation();
        this.review = requestDto.getReview();
    }
    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
    public void deleteComment(Comment comment) {
        commentList.remove(comment);
    }

//    public void update(CampRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.review = requestDto.getReview();
//        this.location = requestDto.getLocation();
//    }
}
