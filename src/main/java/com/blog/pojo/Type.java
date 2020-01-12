package com.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name="t_type")
public class Type {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(columnDefinition = "varchar(200)",name = "type_name",nullable = false)
	private String typeName;
    
   


	@OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
    

    @Column(name="create_time_loc")
    private Date createTimeLoc = new Date();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
		return "Type{" +
				"id=" + id +
				", typeName='" + typeName + '\'' +
				", blogs=" + blogs +
				", createTimeLoc=" + createTimeLoc +
				'}';
	}
}
