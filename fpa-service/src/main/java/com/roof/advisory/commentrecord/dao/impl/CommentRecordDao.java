package com.roof.advisory.commentrecord.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import com.roof.advisory.commentrecord.entity.CommentRecordVo;
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

import com.roof.advisory.commentrecord.dao.api.ICommentRecordDao;
import com.roof.advisory.commentrecord.entity.CommentRecord;

@Service
public class CommentRecordDao extends AbstractDao implements ICommentRecordDao {

    private PageQueryFactory<PageQuery> pageQueryFactory;

    @Override
    public Page page(Page page, CommentRecord commentRecord) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectCommentRecordPage", "selectCommentRecordCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentRecordPage", null);
        return pageQuery.select(commentRecord);
    }

    @Override
    public Page pageByVo(Page page, CommentRecordVo commentRecordVo) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectCommentRecordVoPage", "selectCommentRecordVoCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentRecordPage", null);
        return pageQuery.select(commentRecordVo);
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