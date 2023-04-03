package com.blog.service;

import com.blog.DTO.CommentsDTO;
import com.blog.entity.Comments;
import com.blog.entity.PostBlog;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CommentsRepository;
import com.blog.repository.PostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    /* inyecto los repositorios que se van a asociar */
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private PostBlogRepository postBlogRepository;

    /* -------------- CREO UN COMENTARIO ----------- */
    @Override
    public CommentsDTO createComment(Long postBlogId, CommentsDTO commentsDTO) {
        //1. mapear comentario (json)
        Comments comments = mapDTOToEntity(commentsDTO);
        //2. buscar id del comentario
        PostBlog singlePostBlog = postBlogRepository.findById(postBlogId)
                .orElseThrow(() -> new ResourceNotFoundException("PostBlog", "id", postBlogId));
        //3. asigno comentario a la publicación
        comments.setPostBlog(singlePostBlog);
        //4. guardo comentario en el repositorio
        Comments newComments = commentsRepository.save(comments);

        return mapEntityToDTO(newComments);
    }

    /* ------- DEVUELVO UNA LISTA DE COMENTARIOS POR SU ID -----*/
/*    @Override
    public List<CommentsDTO> listCommentsById(Long postBlogId) {
        List<Comments> commentsList = commentsRepository.findByBlogPostId(postBlogId);

        // mapeo un comentario para convertirlo a DTO y enviarlo
        return commentsList.stream().map(c -> mapEntityToDTO(c)).collect(Collectors.toList());
    }*/

    // Convierto de DTO a entidad
    private Comments mapDTOToEntity(CommentsDTO commentsDTO){
        Comments comments = new Comments();

        comments.setNombre(commentsDTO.getNombre());
        comments.setEmail(commentsDTO.getEmail());
        comments.setComentario(commentsDTO.getComentario());

        return comments;
    }
    // Convierto entidad en DTO
    private CommentsDTO mapEntityToDTO(Comments comments){
        CommentsDTO commentsDTO = new CommentsDTO();

        commentsDTO.setId(comments.getId());
        commentsDTO.setNombre(comments.getNombre());
        commentsDTO.setEmail(comments.getEmail());
        commentsDTO.setComentario(comments.getComentario());

        return commentsDTO;
    }
}

