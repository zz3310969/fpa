package com.roof.fpa.partner.service.api;

/**
 * Created by zhenglt on 2018/1/6.
 */
public interface IPartnerService {
    public Boolean isPartner(String unionid);

    Boolean bind(String unionid,Long customerId);

}
