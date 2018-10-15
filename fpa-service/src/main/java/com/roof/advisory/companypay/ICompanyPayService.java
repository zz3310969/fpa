package com.roof.advisory.companypay;

/**
 * com.roof.advisory.companypay
 *
 * @author liht
 * @date 2018/10/8
 */
public interface ICompanyPayService {
    /**
     * 向openid付款
     *
     * @param openId
     * @param ip
     * @return
     */
    String transfers(String openId, String ip);

    /**
     * 查询付款情况
     *
     * @param partner_trade_no
     * @return
     */
    String transferInfo(String partner_trade_no);
}
