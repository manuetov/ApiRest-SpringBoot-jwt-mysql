package com.blog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
// @UniqueConstraint => no permite titulo repetido
@Table(name ="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class PostBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String contenido;

    /* relación -> una publicación puede tener muchos comentarios */
    /* Cuando se elimine una publicación se eliminarán los comentarios asociados*/
    @OneToMany(mappedBy = "postBlog", cascade = CascadeType.ALL, orphanRemoval = true)
    /* lista de comentarios - HashSet almacena elementos únicos sin duplicados*/
    private Set<Comments> comments = new HashSet<>();

    public PostBlog() {};

    public PostBlog(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "PostBlog{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
