package com.blog.controller;

import com.blog.DTO.CommentsDTO;
import com.blog.entity.Comments;
import com.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

/*    @GetMapping("/post/{postBlog_id}/comment")
    public List<CommentsDTO> getCommentsById(@PathVariable(value = "postBlog_id") long id) {
        return commentsService.listCommentsById(id);
    }*/

    @PostMapping("/post/{postBlog_id}/comment")
    public ResponseEntity<CommentsDTO> saveComment(@PathVariable(value = "postBlog_id") Long id,
                                                   @Valid @RequestBody CommentsDTO commentsDTO) {
        return new ResponseEntity<>(commentsService.createComment(id, commentsDTO), HttpStatus.CREATED);
    }

}
