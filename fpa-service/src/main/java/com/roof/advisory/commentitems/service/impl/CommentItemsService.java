package com.roof.advisory.commentitems.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.commentitems.dao.api.ICommentItemsDao;
import com.roof.advisory.commentitems.entity.CommentItems;
import com.roof.advisory.commentitems.entity.CommentItemsVo;
import com.roof.advisory.commentitems.service.api.ICommentItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentItemsService implements ICommentItemsService {
	private ICommentItemsDao commentItemsDao;

	public Serializable save(CommentItems commentItems){
		return commentItemsDao.save(commentItems);
	}

	public void delete(CommentItems commentItems){
		commentItemsDao.delete(commentItems);
	}
	
	public void deleteByExample(CommentItems commentItems){
		commentItemsDao.deleteByExample(commentItems);
	}

	public void update(CommentItems commentItems){
		commentItemsDao.update(commentItems);
	}
	
	public void updateIgnoreNull(CommentItems commentItems){
		commentItemsDao.updateIgnoreNull(commentItems);
	}
		
	public void updateByExample(CommentItems commentItems){
		commentItemsDao.update("updateByExampleCommentItems", commentItems);
	}

	public CommentItemsVo load(CommentItems commentItems){
		return (CommentItemsVo)commentItemsDao.reload(commentItems);
	}
	
	public CommentItemsVo selectForObject(CommentItems commentItems){
		return (CommentItemsVo)commentItemsDao.selectForObject("selectCommentItems",commentItems);
	}
	
	public List<CommentItemsVo> selectForList(CommentItems commentItems){
		return (List<CommentItemsVo>)commentItemsDao.selectForList("selectCommentItems",commentItems);
	}
	
	public Page page(Page page, CommentItems commentItems) {
		return commentItemsDao.page(page, commentItems);
	}

	@Autowired
	public void setICommentItemsDao(
			@Qualifier("commentItemsDao") ICommentItemsDao  commentItemsDao) {
		this.commentItemsDao = commentItemsDao;
	}
	

}
