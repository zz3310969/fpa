package com.roof.advisory.consultant.service.api;

import com.roof.advisory.consultant.entity.Consultant;

import java.util.List;

/**
 * com.roof.advisory.consultant.service.api
 *
 * @author liht
 * @date 2018/3/13
 */
public interface IConsultantImService {
    /**
     * 获取所有在线咨询师
     *
     * @return
     */
    public List<Consultant> getAllOnLine();

    /**
     * 是否在线
     *
     * @param consultant
     * @return
     */
    public Boolean isOnLine(Consultant consultant);


}
