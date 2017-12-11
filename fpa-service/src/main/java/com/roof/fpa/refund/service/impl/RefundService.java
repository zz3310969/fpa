package com.roof.fpa.refund.service.impl;

import java.io.Serializable;
import java.util.List;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.refund.dao.api.IRefundDao;
import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;
import com.roof.fpa.refund.service.api.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RefundService implements IRefundService {
	private IRefundDao refundDao;

	public Serializable save(Refund refund){
		return refundDao.save(refund);
	}

	public void delete(Refund refund){
		refundDao.delete(refund);
	}
	
	public void deleteByExample(Refund refund){
		refundDao.deleteByExample(refund);
	}

	public void update(Refund refund){
		refundDao.update(refund);
	}
	
	public void updateIgnoreNull(Refund refund){
		refundDao.updateIgnoreNull(refund);
	}
		
	public void updateByExample(Refund refund){
		refundDao.update("updateByExampleRefund", refund);
	}

	public RefundVo load(Refund refund){
		return (RefundVo)refundDao.reload(refund);
	}
	
	public RefundVo selectForObject(Refund refund){
		return (RefundVo)refundDao.selectForObject("selectRefund",refund);
	}
	
	public List<RefundVo> selectForList(Refund refund){
		return (List<RefundVo>)refundDao.selectForList("selectRefund",refund);
	}
	
	public Page page(Page page, Refund refund) {
		return refundDao.page(page, refund);
	}

	@Autowired
	public void setIRefundDao(
			@Qualifier("refundDao") IRefundDao  refundDao) {
		this.refundDao = refundDao;
	}
	

}
