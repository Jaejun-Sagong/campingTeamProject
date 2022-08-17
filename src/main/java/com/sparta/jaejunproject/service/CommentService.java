package com.sparta.jaejunproject.service;

import com.sparta.jaejunproject.dto.CommentDto;
import com.sparta.jaejunproject.dto.CommentRequestDto;
import com.sparta.jaejunproject.model.Comment;
import com.sparta.jaejunproject.model.Camp;
import com.sparta.jaejunproject.repository.CampRepository;
import com.sparta.jaejunproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor //final로 선언한 변수가 있으면 꼭 생성해달라는 것
@Service
public class CommentService {


    private final CommentRepository commentRepository; // [2번]update메소드 작성 전에 id에 맞는 값을 찾으려면 find를 써야하는데 find를 쓰기위해서는 Repository가 있어야한다.
    private final CampRepository campRepository;
    private final CampService campService;

    @Secured("ROLE_USER")
    @Transactional
    public Comment addComment(Long id, CommentRequestDto commentRequestDto) {
        Camp camp = campRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        String memberName = campService.getNickname();
        Comment comment = new Comment(camp, memberName, commentRequestDto);
        return commentRepository.save(comment); //DB 저장과 동시에 comment 돌려줘요
//        camp.addComment(comment);
    }

    @Transactional
    public Comment updateComment(Long id, Long commentId, CommentRequestDto commentRequestDto) {
        Camp camp = campRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if(!campService.getNickname().equals(camp.getNickname())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        comment.setComment(commentRequestDto);
        return comment;
    }

    @Transactional
    public Boolean deleteComment(Long id, Long commentId) {
        Camp camp = campRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        if(!campService.getNickname().equals(camp.getNickname())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        List<Comment> commentList = camp.getCommentList();

        camp.deleteComment(comment);
        commentRepository.delete(comment);
        return true;
    }






}

