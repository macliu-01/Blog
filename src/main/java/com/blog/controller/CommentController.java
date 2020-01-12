package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import com.blog.util.CommonResponse;

@RestController
@RequestMapping("/admin")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("saveComment")
	public CommonResponse saveComment(@RequestBody Comment comment) {
	   CommonResponse commonResponse  = commentService.saveComment(comment);
	   return commonResponse;
	}
	
	@GetMapping("getAllComment")
	public CommonResponse getAllComment(@RequestParam("blogId") Integer blogId) {
		CommonResponse commonResponse = commentService.getCommentByBlogId(blogId);
		return commonResponse;
	}
}
