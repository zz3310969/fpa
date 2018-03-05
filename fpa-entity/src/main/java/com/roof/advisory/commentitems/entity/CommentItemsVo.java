package com.roof.advisory.commentitems.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： z_comment_items <br/>
 * 描述：评价项表 <br/>
 */
public class CommentItemsVo extends CommentItems {

    private String commentTemplateName;

    private String evalModeText;

    private String prosetaionTypeText;

    private List<CommentItemsVo> commentItemsList;

    public CommentItemsVo() {
        super();
    }

    public CommentItemsVo(Long id) {
        super();
        this.id = id;
    }

    public List<CommentItemsVo> getCommentItemsList() {
        return commentItemsList;
    }

    public void setCommentItemsList(List<CommentItemsVo> commentItemsList) {
        this.commentItemsList = commentItemsList;
    }

    public String getCommentTemplateName() {
        return commentTemplateName;
    }

    public void setCommentTemplateName(String commentTemplateName) {
        this.commentTemplateName = commentTemplateName;
    }

    public String getEvalModeText() {
        return evalModeText;
    }

    public void setEvalModeText(String evalModeText) {
        this.evalModeText = evalModeText;
    }

    public String getProsetaionTypeText() {
        return prosetaionTypeText;
    }

    public void setProsetaionTypeText(String prosetaionTypeText) {
        this.prosetaionTypeText = prosetaionTypeText;
    }
}
