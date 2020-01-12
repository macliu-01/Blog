package com.blog.service.Impl;

import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.TagRespository;
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import com.blog.util.CommonResponse;

@Service
public class TagServiceImpl implements TagService {

	public static Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
	
	@Autowired
	private TagRespository tagRespository;

	@Override
	public CommonResponse findAllTags() {
	    try {
			List<Tag> tags = tagRespository.findAll();
			if(tags.size()>0) {
				return new CommonResponse(HttpStatus.SC_OK,"Get data success",tags);
			}else {
				return new CommonResponse(HttpStatus.SC_OK,"Get data success,but hava no data");
			}
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
		}
	}

	@Transactional
	@Override
	public CommonResponse insertTags(Tag tag) {
		try {
			Tag hasTag = tagRespository.findBytagName(tag.getTagName());
			if(hasTag!=null) {
				logger.info("******The tag {} insert fail ,because it has exists",tag.getTagName());
				return new CommonResponse(HttpStatus.SC_CONFLICT,"The tag has exists");
			}
			
			tagRespository.save(tag);
			return new CommonResponse(HttpStatus.SC_OK,"Insert tag success");
			
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
		}
	}

	@Override
	public CommonResponse deleteTags(Integer id) {
		try {
			tagRespository.deleteById(id);
            return new CommonResponse(HttpStatus.SC_OK, "Delete tag successfully");
        } catch (Exception e) {
            return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal_server_error");
        }
	}
	
	

}
