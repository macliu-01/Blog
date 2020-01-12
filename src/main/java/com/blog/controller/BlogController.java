package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.pojo.Blog;
import com.blog.service.BlogService;
import com.blog.util.CommonResponse;

@RestController
@RequestMapping("/admin")
public class BlogController {

	public static Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	private BlogService blogService;
	
	@PostMapping("/getAllBlog")
	public CommonResponse getAllBlog(@RequestBody(required = false) Blog blog){
		
		logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = blogService.getAllBlogs(blog);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());

		return commonResponse;
	}
	
	@PostMapping("saveBlog")
	public CommonResponse saveBlogs(@RequestBody Blog blog) {
        logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = blogService.saveBlog(blog);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());

		return commonResponse;
		
	}
	
	@GetMapping("deleteBlog")
	public CommonResponse deleteBlog(@RequestParam("id") int id) {
        logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = blogService.deleteBlog(id);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());

		return commonResponse;
		
	}
	
	@PostMapping("updateBlog")
	public CommonResponse updateBlog(@RequestBody Blog blog) {
        logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = blogService.updateBlog(blog);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());

		return commonResponse;
		
	}
}
