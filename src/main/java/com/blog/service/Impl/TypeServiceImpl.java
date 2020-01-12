package com.blog.service.Impl;

import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.TypeRespository;
import com.blog.pojo.Type;
import com.blog.service.TypeService;
import com.blog.util.CommonResponse;

@Service
public class TypeServiceImpl implements TypeService {

	public static Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);
	
	@Autowired
	private TypeRespository typeRespository;
	@Override
	public CommonResponse findAllTypes() {
		 try {
				List<Type> types = typeRespository.findAll();
				if(types.size()>0) {
					return new CommonResponse(HttpStatus.SC_OK,"Get data success",types);
				}else {
					return new CommonResponse(HttpStatus.SC_OK,"Get data success,but hava no data");
				}
			} catch (Exception e) {
				return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
			}
	}

	@Transactional
	@Override
	public CommonResponse insertTypes(Type type) {
		try {
			Type hastype = typeRespository.findBytypeName(type.getTypeName());
			if(hastype!=null) {
				logger.info("******The type {} insert fail ,because it has exists",type.getTypeName());
				return new CommonResponse(HttpStatus.SC_CONFLICT,"The type has exists");
			}
			
			typeRespository.save(type);
			return new CommonResponse(HttpStatus.SC_OK,"Insert type success");
			
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
		}
	}

	@Override
	public CommonResponse deleteTypes(Integer id) {
		try {
			typeRespository.deleteById(id);
			return new CommonResponse(HttpStatus.SC_OK,"Delete type success");
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");

		}
	}

}
