package com.blog.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "t_comment")
public class Comment{

    
	/**
	 * 
	 */


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    
     private Date createTimeLoc = new Date();

    
    
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<Comment>();

    @JsonBackReference(value ="parentComment")
    @ManyToOne
    private Comment parentComment;

    
    private boolean adminComment;
    
    @JsonBackReference(value ="blog")
    @ManyToOne
    private Blog blog;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

   

    public Date getCreateTimeLoc() {
		return createTimeLoc;
	}

	public void setCreateTimeLoc(Date createTimeLoc) {
		this.createTimeLoc = createTimeLoc;
	}

	
	public Blog getBlog() {
        return blog;
    }

	
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    
    public Comment getParentComment() {
        return parentComment;
    }

    
    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

	@Override
	public String toString() {
		return "Comment [id=" + id + ", nickname=" + nickname + ", email=" + email + ", content=" + content
				+ ", avatar=" + avatar + ", createTimeLoc=" + createTimeLoc + ", blog=" + blog + ", replyComments="
				+ replyComments + ", parentComment=" + parentComment + ", adminComment=" + adminComment + "]";
	}

   
}
