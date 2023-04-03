package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entity.Comments;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    /*public List<Comments> findByBlogPostId(long blogPostId);
*/
}
