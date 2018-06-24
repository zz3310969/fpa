package com.roof.advisory.advisoryorderrecord.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.advisory.advisoryorder.entity.AdvisoryOrder;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryorderrecord.dao.api.IAdvisoryOrderRecordDao;
import com.roof.advisory.advisoryorderrecord.entity.AdvisoryOrderRecord;
import com.roof.advisory.advisoryorderrecord.entity.AdvisoryOrderRecordVo;
import com.roof.advisory.advisoryorderrecord.service.api.IAdvisoryOrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AdvisoryOrderRecordService implements IAdvisoryOrderRecordService {
    private IAdvisoryOrderRecordDao advisoryOrderRecordDao;

    @Override
    @Async
    public void saveOrderChange(AdvisoryOrder oldOrder, AdvisoryOrder newOrder) {


    }

    @Override
    public Serializable save(AdvisoryOrderRecord advisoryOrderRecord) {
        return advisoryOrderRecordDao.save(advisoryOrderRecord);
    }

    public void delete(AdvisoryOrderRecord advisoryOrderRecord) {
        advisoryOrderRecordDao.delete(advisoryOrderRecord);
    }

    public void deleteByExample(AdvisoryOrderRecord advisoryOrderRecord) {
        advisoryOrderRecordDao.deleteByExample(advisoryOrderRecord);
    }

    public void update(AdvisoryOrderRecord advisoryOrderRecord) {
        advisoryOrderRecordDao.update(advisoryOrderRecord);
    }

    public void updateIgnoreNull(AdvisoryOrderRecord advisoryOrderRecord) {
        advisoryOrderRecordDao.updateIgnoreNull(advisoryOrderRecord);
    }

    public void updateByExample(AdvisoryOrderRecord advisoryOrderRecord) {
        advisoryOrderRecordDao.update("updateByExampleAdvisoryOrderRecord", advisoryOrderRecord);
    }

    public AdvisoryOrderRecordVo load(AdvisoryOrderRecord advisoryOrderRecord) {
        return (AdvisoryOrderRecordVo) advisoryOrderRecordDao.reload(advisoryOrderRecord);
    }

    public AdvisoryOrderRecordVo selectForObject(AdvisoryOrderRecord advisoryOrderRecord) {
        return (AdvisoryOrderRecordVo) advisoryOrderRecordDao.selectForObject("selectAdvisoryOrderRecord", advisoryOrderRecord);
    }

    public List<AdvisoryOrderRecordVo> selectForList(AdvisoryOrderRecord advisoryOrderRecord) {
        return (List<AdvisoryOrderRecordVo>) advisoryOrderRecordDao.selectForList("selectAdvisoryOrderRecord", advisoryOrderRecord);
    }

    public Page page(Page page, AdvisoryOrderRecord advisoryOrderRecord) {
        return advisoryOrderRecordDao.page(page, advisoryOrderRecord);
    }

    @Autowired
    public void setIAdvisoryOrderRecordDao(
            @Qualifier("advisoryOrderRecordDao") IAdvisoryOrderRecordDao advisoryOrderRecordDao
    ) {
        this.advisoryOrderRecordDao = advisoryOrderRecordDao;
    }


}
