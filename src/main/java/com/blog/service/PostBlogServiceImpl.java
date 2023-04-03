package com.blog.service;

import com.blog.entity.PostBlog;
import com.blog.DTO.PostBlogDTO;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.PostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostBlogServiceImpl implements PostBlogService{

    @Autowired
    public PostBlogRepository postBlogRepository;

    /* -------------- CREA UN POST --------------------------------*/
    @Override
    public PostBlogDTO createPost(PostBlogDTO postBlogDTO) {
        PostBlog postBlog = mapDTOtoEntity(postBlogDTO);

        // lo guardo en el repositorio ( persistencia en BD)
        PostBlog newPostblog = postBlogRepository.save(postBlog);

        PostBlogDTO postBlogResponseDTO = mapEntitytoDTO(newPostblog);

        return postBlogResponseDTO;
    }

    /* ------------------ DEVUELVE LISTADO DE POSTS ---------------*/
    @Override
    public List<PostBlogDTO> listAllPost() {
        // obtengo un listado de postBlog de la BBDD
        List<PostBlog> listPostBlog = postBlogRepository.findAll();
        return listPostBlog.stream().map(p -> mapEntitytoDTO(p)).collect(Collectors.toList());
    }

    /* ----------------- DEVUELE UN POST POR SU ID ----------------*/
    @Override
    public PostBlogDTO getPostBlogById(long id) {
        PostBlog singlePostBlog = postBlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PostBlog", "id", id));
        return mapEntitytoDTO(singlePostBlog);
    }

    /* ---------------- ACTUALIZA UN POST POR SU ID --------------*/
    @Override
    public PostBlogDTO updatePostBlogById(PostBlogDTO postBlogDTO, long id) {
        PostBlog singlePostBlog = postBlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PostBlog", "id", id));

        singlePostBlog.setTitulo(postBlogDTO.getTitulo());
        singlePostBlog.setDescripcion(postBlogDTO.getDescripcion());
        singlePostBlog.setContenido(postBlogDTO.getContenido());

        PostBlog updateSinglePostBlog = postBlogRepository.save(singlePostBlog);

        return mapEntitytoDTO(updateSinglePostBlog);
    }
    /* ---------------- BORRA UN POST POR SU ID ------------------*/
    @Override
    public void deletePostBlogById(long id) {
        PostBlog singlePostBlog = postBlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PostBlog", "id", id));

        postBlogRepository.delete(singlePostBlog);
    }

    // Convierto de DTO a entidad
    private PostBlog mapDTOtoEntity(PostBlogDTO postBlogDTO){
        PostBlog postblog = new PostBlog();
        postblog.setTitulo(postBlogDTO.getTitulo());
        postblog.setDescripcion(postBlogDTO.getDescripcion());
        postblog.setContenido(postBlogDTO.getContenido());

        return postblog;
    }

    // Convierto entidad en DTO
    private PostBlogDTO mapEntitytoDTO(PostBlog postBlog){
        PostBlogDTO postBlogDTO = new PostBlogDTO();

        postBlogDTO.setId(postBlog.getId());
        postBlogDTO.setTitulo(postBlog.getTitulo());
        postBlogDTO.setDescripcion(postBlog.getDescripcion());
        postBlogDTO.setContenido(postBlog.getContenido());

        return postBlogDTO;
    }




}
