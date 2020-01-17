package com.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity(name = "t_tag")
public class Tag {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(columnDefinition = "varchar(200)",name = "tag_name",nullable = false)
	private String tagName;
    
    @JsonBackReference(value = "blogs")
    @ManyToMany(mappedBy = "tags",cascade = {CascadeType.PERSIST})
    private List<Blog> blogs = new ArrayList<>();
    
   
    @Column(name="create_time_loc")
    private Date createTimeLoc = new Date();
        

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	

	public Date getCreateTimeLoc() {
		return createTimeLoc;
	}

	public void setCreateTimeLoc(Date createTimeLoc) {
		this.createTimeLoc = createTimeLoc;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "Tag{" +
				"id=" + id +
				", tagName='" + tagName + '\'' +
				", blogs=" + blogs +
				", createTimeLoc=" + createTimeLoc +
				'}';
	}
}
