package com.roof.advisory.commentrecord.service.api;

import java.util.List;
import java.io.Serializable;

import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.commentrecord.entity.CommentRecord;
import com.roof.advisory.commentrecord.entity.CommentRecordVo;

public interface ICommentRecordService {

    /**
     * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
     */
    public abstract Serializable save(CommentRecord commentRecord);

    /**
     * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
     */
    public abstract void delete(CommentRecord commentRecord);

    /**
     * 按对象中的非空属性作为条件，进行删除
     */
    public abstract void deleteByExample(CommentRecord commentRecord);

    /**
     * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
     */
    public abstract void update(CommentRecord commentRecord);

    /**
     * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
     */
    public abstract void updateIgnoreNull(CommentRecord commentRecord);

    /**
     * 按对象中的非空属性作为条件，进行修改
     */
    public abstract void updateByExample(CommentRecord commentRecord);

    /**
     * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
     */
    public abstract CommentRecordVo load(CommentRecord commentRecord);

    /**
     * 按对象中的非空属性作为条件，进行查询实体
     */
    public abstract CommentRecordVo selectForObject(CommentRecord commentRecord);

    /**
     * 按对象中的非空属性作为条件，进行查询列表
     */
    public abstract List<CommentRecordVo> selectForList(CommentRecord commentRecord);

    /**
     * 按对象中的非空属性作为条件，进行分页查询
     */
    public abstract Page page(Page page, CommentRecord commentRecord);

    public abstract Page wechatPage(Page page, CommentRecord commentRecord);


    public Page pageByVo(Page page, CommentRecordVo commentRecordVo);

}