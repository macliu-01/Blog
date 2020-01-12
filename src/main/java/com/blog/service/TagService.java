package com.blog.service;

import com.blog.pojo.Tag;
import com.blog.util.CommonResponse;

public interface TagService {

	CommonResponse findAllTags();
	
	CommonResponse insertTags(Tag tag);
	
	CommonResponse deleteTags(Integer id);
}
