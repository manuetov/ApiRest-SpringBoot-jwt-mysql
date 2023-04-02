package com.tecnologia.blogspringbootapirestjwtmysql.service;

import com.tecnologia.blogspringbootapirestjwtmysql.DTO.PostBlogDTO;
import com.tecnologia.blogspringbootapirestjwtmysql.entity.PostBlog;

import java.util.List;

public interface PostBlogService {

    public PostBlogDTO createPost(PostBlogDTO postBlogDTO);

    public List<PostBlogDTO> listAllPost();

    public PostBlogDTO getPostBlogById(long id);

    public PostBlogDTO updatePostBlogById(PostBlogDTO postBlogDTO, long id);

    public void deletePostBlogById(long id);

}
