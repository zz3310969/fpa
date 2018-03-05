package com.roof.advisory.commentrecord.dao.api;

import com.roof.advisory.commentrecord.entity.CommentRecordVo;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.Page;

import com.roof.advisory.commentrecord.entity.CommentRecord;

public interface ICommentRecordDao extends IDaoSupport {
    Page page(Page page, CommentRecord commentRecord);

    public Page pageByVo(Page page, CommentRecordVo commentRecordVo);
}