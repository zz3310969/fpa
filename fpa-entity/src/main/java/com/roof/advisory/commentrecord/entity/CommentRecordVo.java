package com.roof.advisory.commentrecord.entity;

import com.roof.fpa.customer.entity.Customer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author 模版生成 <br/>
 * 表名： z_comment_record <br/>
 * 描述：评价记录表 <br/>
 */
public class CommentRecordVo extends CommentRecord {

    private String appName;
    private String itemName;
    private String consultantName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evalTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evalTimeEnd;
    private Customer customer;


    private List<CommentRecordVo> commentRecordList;

    public CommentRecordVo() {
        super();
    }

    public CommentRecordVo(Long id) {
        super();
        this.id = id;
    }

    public List<CommentRecordVo> getCommentRecordList() {
        return commentRecordList;
    }

    public void setCommentRecordList(List<CommentRecordVo> commentRecordList) {
        this.commentRecordList = commentRecordList;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public Date getEvalTimeStart() {
        return evalTimeStart;
    }

    public void setEvalTimeStart(Date evalTimeStart) {
        this.evalTimeStart = evalTimeStart;
    }

    public Date getEvalTimeEnd() {
        return evalTimeEnd;
    }

    public void setEvalTimeEnd(Date evalTimeEnd) {
        this.evalTimeEnd = evalTimeEnd;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
