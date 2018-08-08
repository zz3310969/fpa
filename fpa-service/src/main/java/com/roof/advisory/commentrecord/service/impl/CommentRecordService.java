package com.roof.advisory.commentrecord.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.fpa.customer.entity.Customer;
import com.roof.fpa.customer.entity.CustomerVo;
import com.roof.fpa.customer.service.api.ICustomerService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.commentrecord.dao.api.ICommentRecordDao;
import com.roof.advisory.commentrecord.entity.CommentRecord;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import com.roof.advisory.commentrecord.service.api.ICommentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentRecordService implements ICommentRecordService {
    private ICommentRecordDao commentRecordDao;

    @Autowired
    private ICustomerService customerService;
    public Serializable save(CommentRecord commentRecord) {
        commentRecord.setState(1);
        return commentRecordDao.save(commentRecord);
    }

    public void delete(CommentRecord commentRecord) {
        commentRecordDao.delete(commentRecord);
    }

    public void deleteByExample(CommentRecord commentRecord) {
        commentRecordDao.deleteByExample(commentRecord);
    }

    public void update(CommentRecord commentRecord) {
        commentRecordDao.update(commentRecord);
    }

    public void updateIgnoreNull(CommentRecord commentRecord) {
        commentRecordDao.updateIgnoreNull(commentRecord);
    }

    public void updateByExample(CommentRecord commentRecord) {
        commentRecordDao.update("updateByExampleCommentRecord", commentRecord);
    }

    public CommentRecordVo load(CommentRecord commentRecord) {
        return (CommentRecordVo) commentRecordDao.reload(commentRecord);
    }

    public CommentRecordVo selectForObject(CommentRecord commentRecord) {
        return (CommentRecordVo) commentRecordDao.selectForObject("selectCommentRecord", commentRecord);
    }

    public List<CommentRecordVo> selectForList(CommentRecord commentRecord) {
        return (List<CommentRecordVo>) commentRecordDao.selectForList("selectCommentRecord", commentRecord);
    }

    public Page page(Page page, CommentRecord commentRecord) {
        return commentRecordDao.page(page, commentRecord);
    }

    public Page wechatPage(Page page, CommentRecord commentRecord) {
        Page r = commentRecordDao.page(page, commentRecord);
        List<CommentRecordVo> vos = (List<CommentRecordVo>) r.getDataList();
        for (CommentRecordVo vo : vos){
            Customer customer = new Customer(vo.getEvaluator());
            CustomerVo customerVo = customerService.load(customer);
            vo.setCustomer(customerVo != null? customerVo : customer);
        }
        r.setDataList(vos);
        return r ;
    }

    @Override
    public Page pageByVo(Page page, CommentRecordVo commentRecordVo) {
        return commentRecordDao.pageByVo(page, commentRecordVo);
    }

    @Autowired
    public void setICommentRecordDao(
            @Qualifier("commentRecordDao") ICommentRecordDao commentRecordDao
    ) {
        this.commentRecordDao = commentRecordDao;
    }


}
