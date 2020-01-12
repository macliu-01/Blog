package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.util.CommonResponse;

public interface BlogService {
	

	CommonResponse getAllBlogs(Blog blog);
	
	CommonResponse saveBlog(Blog blog);
	
	CommonResponse updateBlog(Blog blog);
	
	CommonResponse deleteBlog(int id);

}
