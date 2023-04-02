package com.tecnologia.blogspringbootapirestjwtmysql.controller;


import com.tecnologia.blogspringbootapirestjwtmysql.DTO.PostBlogDTO;
import com.tecnologia.blogspringbootapirestjwtmysql.entity.PostBlog;
import com.tecnologia.blogspringbootapirestjwtmysql.service.PostBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostBlogController {

    @Autowired
    public PostBlogService postBlogService;

    @PostMapping
    public ResponseEntity<PostBlogDTO> savePost(@RequestBody PostBlogDTO postBlogDTO){
        return new ResponseEntity<>(postBlogService.createPost(postBlogDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostBlogDTO>> listPost() {
        return new ResponseEntity<>(postBlogService.listAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostBlogDTO> getSinglePost(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postBlogService.getPostBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostBlogDTO> updateSinglePost(@RequestBody PostBlogDTO postBlogDTO,
                                                        @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postBlogService.updatePostBlogById(postBlogDTO, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSinglePost(@PathVariable(name = "id") long id) {
        postBlogService.deletePostBlogById(id);
        return new ResponseEntity("Post elmiminado con exito",HttpStatus.OK);
    }

}
