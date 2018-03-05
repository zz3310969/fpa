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

	@Override
    public Serializable save(CommentItems commentItems){
		commentItems.setState(1);
		return commentItemsDao.save(commentItems);
	}

	@Override
    public void delete(CommentItems commentItems){
		commentItemsDao.delete(commentItems);
	}
	
	@Override
    public void deleteByExample(CommentItems commentItems){
		commentItemsDao.deleteByExample(commentItems);
	}

	@Override
    public void update(CommentItems commentItems){
		commentItemsDao.update(commentItems);
	}
	
	@Override
    public void updateIgnoreNull(CommentItems commentItems){
		commentItemsDao.updateIgnoreNull(commentItems);
	}
		
	@Override
    public void updateByExample(CommentItems commentItems){
		commentItemsDao.update("updateByExampleCommentItems", commentItems);
	}

	@Override
    public CommentItemsVo load(CommentItems commentItems){
		return (CommentItemsVo)commentItemsDao.reload(commentItems);
	}
	
	@Override
    public CommentItemsVo selectForObject(CommentItems commentItems){
		return (CommentItemsVo)commentItemsDao.selectForObject("selectCommentItems",commentItems);
	}
	
	@Override
    public List<CommentItemsVo> selectForList(CommentItems commentItems){
		return (List<CommentItemsVo>)commentItemsDao.selectForList("selectCommentItems",commentItems);
	}
	
	@Override
    public Page page(Page page, CommentItems commentItems) {
		return commentItemsDao.page(page, commentItems);
	}

	@Autowired
	public void setICommentItemsDao(
			@Qualifier("commentItemsDao") ICommentItemsDao  commentItemsDao) {
		this.commentItemsDao = commentItemsDao;
	}
	

}
