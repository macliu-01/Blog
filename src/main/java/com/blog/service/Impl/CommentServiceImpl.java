package com.blog.service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.BlogRespository;
import com.blog.dao.CommentRespository;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import com.blog.util.CommonResponse;

/**
 * @author mac
 */
@Service
public class CommentServiceImpl implements CommentService{

	public static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	@Autowired
	private CommentRespository commentRespository;
	@Autowired
	private BlogRespository blogRespository;

	@Transactional
	@Override
	public CommonResponse saveComment(Comment comment) {
		
		Integer parentCommentId = comment.getParentComment().getId();
		
		//如果父评论等于-1,则把ParentComment置空,主要是把ParentId置为空，评论为一级评论。
		if(parentCommentId !=-1) {
			comment.setParentComment(commentRespository.findById(parentCommentId).get());
		}else {
			comment.setParentComment(null);
		}
		comment.setBlog(blogRespository.findById(comment.getBlog().getId()).get());
		commentRespository.save(comment);

		return new CommonResponse(HttpStatus.SC_OK,"Comment success");

		
	}

	@Override
	public CommonResponse getCommentByBlogId(Integer blogId) {
		logger.info("###### The blogId is {}",blogId);
		List<Comment> comments = commentRespository.findByBlogIdAndParentCommentNull(blogId);
		
		return new CommonResponse(HttpStatus.SC_OK,"Get data success",eachComment(comments));
	}
	
	
	   /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
	
	
}
