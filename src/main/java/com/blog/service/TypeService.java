package com.blog.service;

import com.blog.pojo.Type;
import com.blog.util.CommonResponse;

public interface TypeService {

    CommonResponse findAllTypes();
	
	CommonResponse insertTypes(Type type);
	
	CommonResponse deleteTypes(Integer id);
}
