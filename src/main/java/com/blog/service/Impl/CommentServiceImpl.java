package com.blog.service.Impl;


import com.blog.dao.BlogRespository;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.CommentRespository;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import com.blog.util.CommonResponse;

/**
 * @author mac
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRespository commentRespository;
	@Autowired
	private BlogRespository blogRespository;

	@Transactional
	@Override
	public CommonResponse saveComment(Comment comment) {
		
		Integer parentCommentId = comment.getParentComment().getId();
		
		//如果父评论等于-1,则把ParentComment置空,主要是把ParentId置为空，评论为一级评论。
		if(parentCommentId !=-1) {
			comment.setParentComment(commentRespository.findById(parentCommentId).get());
		}else {
			comment.setParentComment(null);
		}
		comment.setBlog(blogRespository.findById(comment.getBlog().getId()).get());
		commentRespository.save(comment);

		return new CommonResponse(HttpStatus.SC_OK,"Comment success");

		
	}

	@Override
	public CommonResponse getCommentByBlogId(Integer blogId) {
		Page<Comment> comments = commentRespository.findCommentByBlogId(blogId,PageRequest.of(0, 10));
		System.out.println(comments);
		return null;
	}
}
