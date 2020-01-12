package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Tag;

public interface TagRespository extends JpaRepository<Tag, Integer> {

	Tag findBytagName(String tagName);
}
