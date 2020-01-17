package com.blog.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.BlogRespository;
import com.blog.dao.TagRespository;
import com.blog.dao.TypeRespository;
import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.BlogService;
import com.blog.util.CommonResponse;
import com.blog.util.MyBeanUtils;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRespository blogRespository;
	
	@Autowired
	private TagRespository tagRespository;
	
	@Autowired
	private TypeRespository typeRespository;

	
	@Override
	public CommonResponse getAllBlogs(Blog blog) {
	
		
//		Page<Blog> blogs = getAllBlog(blog, PageRequest.of(0, 10));
		
		Page<Blog> blogs = blogRespository.findAll(getAllBlog1(blog), PageRequest.of(0, 10));
		
		long totalCount = blogs.getTotalElements();
		
		if(totalCount<0) {
			return new CommonResponse(HttpStatus.SC_OK,"no data");
		}
		
		return new CommonResponse(HttpStatus.SC_OK,"get data success",blogs.getContent());
		
	}

	

    @Transactional
	@Override
	public CommonResponse saveBlog(Blog blog) {
		try {			
			List<Tag> taglist = new ArrayList<Tag>();
			for (Tag tag : blog.getTags()) {				
				 Tag tags = tagRespository.findBytagName(tag.getTagName());
				 taglist.add(tags);
			}
			
			blog.setType(typeRespository.findBytypeName(blog.getType().getTypeName()));
			blog.setTags(taglist);
			blogRespository.save(blog);
			return new CommonResponse(HttpStatus.SC_OK,"save success");
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
		}
		
	}


    
	@Override
	public CommonResponse updateBlog(Blog blog) {
		Optional<Blog>  targetBlog = blogRespository.findById(blog.getId());	
		
		Blog reslut = targetBlog.get();

		Date time  = reslut.getCreateTimeLoc();
		
		if(targetBlog.isPresent()) {
			BeanUtils.copyProperties(blog,reslut,MyBeanUtils.getNullPropertyNames(blog));
		
		}		
		reslut.setModify_time(new Date());
		reslut.setCreateTimeLoc(time);
		
	    blogRespository.save(reslut);
	    
	    return new CommonResponse(HttpStatus.SC_OK,"Update success",reslut);
		
		
	}
	
	@Transactional
	@Override
	public CommonResponse deleteBlog(int id) {
		try {
			blogRespository.deleteById(id);
			return new CommonResponse(HttpStatus.SC_OK,"Delete success");
		} catch (Exception e) {
			return new CommonResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal_server_error");
		}
		
	} 

	
	private Page<Blog> getAllBlog(Blog blog, Pageable page) {
		
		
		
		return blogRespository.findAll(new Specification<Blog>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				 List<Predicate> predicates = new ArrayList<>();
//					 Join<Blog,Type> join = root.join("id",JoinType.INNER);
//					 predicates.add(criteriaBuilder.equal(join.get("type").get("id"),blog.getType().getId()));

	                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
	                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
	                }
	                if (blog.getType().getId() != null) {
	                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getType().getId()));
	                }	                       
	                
	                if (blog.isRecommend()) {
	                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
	                }
	                query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
	                
	                return query.getRestriction();
	            }
		},page);
		
	  
 
	}
	
		private Specification<Blog> getAllBlog1(Blog blog){
			Specification<Blog> blog1 = (Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> {
				 List<Predicate> predicates = new ArrayList<>();
	//				 Join<Blog,Type> join = root.join("id",JoinType.INNER);
	//				 predicates.add(criteriaBuilder.equal(join.get("type").get("id"),blog.getType().getId()));
	
		           if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
		               predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
		           }
		           if (blog.getType().getId() != null) {
		               predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getType().getId()));
		           }	                       
		           
		           if (blog.isRecommend()) {
		               predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
		           }
		           query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		           
		           return query.getRestriction();
		       };
	
	
		       return blog1;
		}
		
	
	
}
