package com.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.blog.pojo.Blog;

public interface BlogRespository extends PagingAndSortingRepository<Blog, Integer> {

	Page<Blog> findAll(Specification<Blog> blog, Pageable page);
	

}
