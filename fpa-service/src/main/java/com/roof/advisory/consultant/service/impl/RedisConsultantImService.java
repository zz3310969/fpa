package com.roof.advisory.consultant.service.impl;

import com.roof.advisory.consultant.entity.Consultant;
import com.roof.advisory.consultant.service.api.IConsultantImService;

import java.util.List;

/**
 * com.roof.advisory.consultant.service.api
 *
 * @author liht
 * @date 2018/3/13
 */
public class RedisConsultantImService implements IConsultantImService {


    @Override
    public List<Consultant> getAllOnLine() {
        return null;
    }


    @Override
    public Boolean isOnLine(Consultant consultant) {
        return null;
    }
}
