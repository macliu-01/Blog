package com.blog.service;

import com.blog.pojo.Comment;
import com.blog.util.CommonResponse;

/**
 * @author mac
 */
public interface CommentService {

	CommonResponse saveComment(Comment comment);
	
	CommonResponse getCommentByBlogId(Integer blogId);
}
