package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("saveComment")
	public CommonResponse saveComment( @RequestBody Comment comment){
	   logger.info("####### The param is {}",comment); 
	   CommonResponse commonResponse  = commentService.saveComment(comment);
	   return commonResponse;
	}
	
	@GetMapping("getAllComment")
	public CommonResponse getAllComment(@RequestParam("blogId") Integer blogId) {
		CommonResponse commonResponse = commentService.getCommentByBlogId(blogId);
		return commonResponse;
	}
}
