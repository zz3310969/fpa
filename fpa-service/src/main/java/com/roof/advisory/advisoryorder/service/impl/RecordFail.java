package com.roof.advisory.advisoryorder.service.impl;

import com.roof.advisory.advisoryorder.entity.AdvisoryOrderVo;
import com.roof.chain.api.ValueStack;
import com.roof.chain.support.NodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.roof.advisory.advisoryorder.service.impl
 *
 * @author liht
 * @date 2018/6/19
 */
public class RecordFail {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordFail.class);

    public String doNode(AdvisoryOrderVo orderVo, ValueStack valueStack) {

        


        return NodeResult.SUCCESS;
    }

}
