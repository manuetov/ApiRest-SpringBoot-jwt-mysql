package com.blog.service;

import com.blog.DTO.CommentsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentsService {

    /* le paso el id del post y el comentario */
    public CommentsDTO createComment(Long postBlogId, CommentsDTO commentsDTO );
    /* listado de comentarios de un post del blog*/
    /*public List<CommentsDTO> listCommentsById(Long postBlogId);*/

}
