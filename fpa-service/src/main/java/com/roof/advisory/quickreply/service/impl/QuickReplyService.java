package com.roof.advisory.quickreply.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.DefaultStateEnum;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.quickreply.dao.api.IQuickReplyDao;
import com.roof.advisory.quickreply.entity.QuickReply;
import com.roof.advisory.quickreply.entity.QuickReplyVo;
import com.roof.advisory.quickreply.service.api.IQuickReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QuickReplyService implements IQuickReplyService {
	private IQuickReplyDao quickReplyDao;

	public Serializable save(QuickReply quickReply){
		quickReply.setState(DefaultStateEnum.usable.getCode());
		return quickReplyDao.save(quickReply);
	}

	public void delete(QuickReply quickReply){
		quickReplyDao.delete(quickReply);
	}
	
	public void deleteByExample(QuickReply quickReply){
		quickReplyDao.deleteByExample(quickReply);
	}

	public void update(QuickReply quickReply){
		quickReplyDao.update(quickReply);
	}
	
	public void updateIgnoreNull(QuickReply quickReply){
		quickReplyDao.updateIgnoreNull(quickReply);
	}
		
	public void updateByExample(QuickReply quickReply){
		quickReplyDao.update("updateByExampleQuickReply", quickReply);
	}

	public QuickReplyVo load(QuickReply quickReply){
		return (QuickReplyVo)quickReplyDao.reload(quickReply);
	}
	
	public QuickReplyVo selectForObject(QuickReply quickReply){
		return (QuickReplyVo)quickReplyDao.selectForObject("selectQuickReply",quickReply);
	}
	
	public List<QuickReplyVo> selectForList(QuickReply quickReply){
		return (List<QuickReplyVo>)quickReplyDao.selectForList("selectQuickReply",quickReply);
	}
	
	public Page page(Page page, QuickReply quickReply) {
		return quickReplyDao.page(page, quickReply);
	}

	@Autowired
	public void setIQuickReplyDao(
			@Qualifier("quickReplyDao") IQuickReplyDao  quickReplyDao) {
		this.quickReplyDao = quickReplyDao;
	}
	

}
