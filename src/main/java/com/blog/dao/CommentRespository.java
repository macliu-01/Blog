package com.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.pojo.Comment;

public interface CommentRespository extends JpaRepository<Comment, Integer>{
	
	
	@Query("select t from t_comment t where t.blog.id=?1")
	Page<Comment> findCommentByBlogId(Integer blogId,Pageable page);

}
