package com.roof.advisory.commenttemplate.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.commenttemplate.dao.api.ICommentTemplateDao;
import com.roof.advisory.commenttemplate.entity.CommentTemplate;
import com.roof.advisory.commenttemplate.entity.CommentTemplateVo;
import com.roof.advisory.commenttemplate.service.api.ICommentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentTemplateService implements ICommentTemplateService {
	private ICommentTemplateDao commentTemplateDao;

	public Serializable save(CommentTemplate commentTemplate){
		commentTemplate.setState(1);
		return commentTemplateDao.save(commentTemplate);
	}

	public void delete(CommentTemplate commentTemplate){
		commentTemplateDao.delete(commentTemplate);
	}
	
	public void deleteByExample(CommentTemplate commentTemplate){
		commentTemplateDao.deleteByExample(commentTemplate);
	}

	public void update(CommentTemplate commentTemplate){
		commentTemplateDao.update(commentTemplate);
	}
	
	public void updateIgnoreNull(CommentTemplate commentTemplate){
		commentTemplateDao.updateIgnoreNull(commentTemplate);
	}
		
	public void updateByExample(CommentTemplate commentTemplate){
		commentTemplateDao.update("updateByExampleCommentTemplate", commentTemplate);
	}

	public CommentTemplateVo load(CommentTemplate commentTemplate){
		return (CommentTemplateVo)commentTemplateDao.reload(commentTemplate);
	}
	
	public CommentTemplateVo selectForObject(CommentTemplate commentTemplate){
		return (CommentTemplateVo)commentTemplateDao.selectForObject("selectCommentTemplate",commentTemplate);
	}
	
	public List<CommentTemplateVo> selectForList(CommentTemplate commentTemplate){
		return (List<CommentTemplateVo>)commentTemplateDao.selectForList("selectCommentTemplate",commentTemplate);
	}
	
	public Page page(Page page, CommentTemplate commentTemplate) {
		return commentTemplateDao.page(page, commentTemplate);
	}

	@Autowired
	public void setICommentTemplateDao(
			@Qualifier("commentTemplateDao") ICommentTemplateDao  commentTemplateDao) {
		this.commentTemplateDao = commentTemplateDao;
	}
	

}
