package com.blog.service;

import com.blog.DTO.PostBlogDTO;

import java.util.List;

public interface PostBlogService {

    public PostBlogDTO createPost(PostBlogDTO postBlogDTO);

    public List<PostBlogDTO> listAllPost();

    public PostBlogDTO getPostBlogById(long id);

    public PostBlogDTO updatePostBlogById(PostBlogDTO postBlogDTO, long id);

    public void deletePostBlogById(long id);

}
