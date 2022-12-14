package com.sparta.jaejunproject.controller;

import com.sparta.jaejunproject.dto.CommentRequestDto;
import com.sparta.jaejunproject.model.Comment;
import com.sparta.jaejunproject.repository.CommentRepository;
import com.sparta.jaejunproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", exposedHeaders = "*", allowedHeaders = "*")
@RequestMapping("/api/auth/comment")
public class CommentController {

    private final CommentService commentService;  // 필수적인 요소이기 때문에 final 선언
    private final CommentRepository commentRepository;

    @Secured("ROLE_USER")
    @PostMapping("/{id}")
    public Comment addComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(id, commentRequestDto);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}/{commentId}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentId, commentRequestDto);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{id}/{commentId}")
    public Boolean deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        return commentService.deleteComment(id, commentId);
    }




}


//    @GetMapping("/api/memos")
//    public List<Memo> readMemo(){
//        return memoRepository.findAllByOrderByModifiedAtDesc();
//    }
//}
