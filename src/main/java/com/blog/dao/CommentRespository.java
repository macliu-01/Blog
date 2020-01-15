package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Comment;

public interface CommentRespository extends JpaRepository<Comment, Integer>{
	
	
	
	
	List<Comment> findByBlogIdAndParentCommentNull(Integer blogId);
	

}
