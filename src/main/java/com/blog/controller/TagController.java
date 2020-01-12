package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.pojo.Tag;
import com.blog.service.TagService;
import com.blog.util.CommonResponse;

@RestController
@RequestMapping("/admin")
public class TagController {
	
	public static Logger logger = LoggerFactory.getLogger(TagController.class);
	
	@Autowired
	private TagService tagService;
	
	
	@PostMapping("/insertTag")
	public CommonResponse insertTags(@RequestBody Tag tag) {
		logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("the param  is {}",tag.getTagName());
		
		CommonResponse commonResponse = tagService.insertTags(tag);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;
	}
	
	@GetMapping("deleteTag")
	public CommonResponse deleteTags(Integer id) {
		logger.info("Enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("the param is {}", id);
		
		CommonResponse commonResponse = tagService.deleteTags(id);
		
		logger.info("Finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;
		
	}
	
	@GetMapping("findAllTag")
	public CommonResponse findAllTag() {
		logger.info("Enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = tagService.findAllTags();
		
		logger.info("Finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;

	}

}
