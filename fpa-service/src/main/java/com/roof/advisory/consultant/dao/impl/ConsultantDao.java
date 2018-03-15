package com.roof.advisory.consultant.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import com.roof.advisory.consultant.entity.ConsultantWechatVo;
import org.apache.commons.lang3.StringUtils;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.advisory.consultant.dao.api.IConsultantDao;
import com.roof.advisory.consultant.entity.Consultant;

@Service
public class ConsultantDao extends AbstractDao implements IConsultantDao {

    private PageQueryFactory<PageQuery> pageQueryFactory;

    public Page page(Page page, Consultant consultant) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectConsultantPage", "selectConsultantCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectConsultantPage", null);
        return pageQuery.select(consultant);
    }

    @Override
    public Page pageWechat(Page page, ConsultantWechatVo consultantWechatVo) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectConsultantWechatPage", "selectConsultantWechatCount");
        return pageQuery.select(consultantWechatVo);
    }

    @Autowired
    public void setPageQueryFactory(
            @Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory
    ) {
        this.pageQueryFactory = pageQueryFactory;
    }

    @Autowired
    public void setDaoSupport(
            @Qualifier("roofDaoSupport") IDaoSupport daoSupport
    ) {
        this.daoSupport = daoSupport;
    }

}