package com.tecnologia.blogspringbootapirestjwtmysql.repository;

import com.tecnologia.blogspringbootapirestjwtmysql.entity.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<T, ID>
public interface PostBlogRepository extends JpaRepository<PostBlog, Long> {
}
