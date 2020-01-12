package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Type;

public interface TypeRespository extends JpaRepository<Type, Integer>{

	Type findBytypeName(String typeName);
	
}
