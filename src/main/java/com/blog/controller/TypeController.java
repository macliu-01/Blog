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
import com.blog.pojo.Type;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.blog.util.CommonResponse;

@RestController
@RequestMapping("/admin")
public class TypeController {
public static Logger logger = LoggerFactory.getLogger(TagController.class);
	
	@Autowired
	private TypeService typeService;
	
	
	@PostMapping("/insertType")
	public CommonResponse insertType(@RequestBody Type type) {
		logger.info("enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("the param  is {}",type.getTypeName());
		
		CommonResponse commonResponse = typeService.insertTypes(type);
		
		logger.info("finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;
	}
	
	@GetMapping("deleteType")
	public CommonResponse deleteType(Integer id) {
		logger.info("Enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("the param is {}", id);
		
		CommonResponse commonResponse = typeService.deleteTypes(id);
		
		logger.info("Finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;
		
	}
	
	@GetMapping("findAllType")
	public CommonResponse findAllType() {
		logger.info("Enter into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonResponse commonResponse = typeService.findAllTypes();
		
		logger.info("Finish into {}.{}()",this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return commonResponse;

	}

}
