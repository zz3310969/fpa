package com.roof.advisory.advisoryproduct.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.advisory.advisorypricing.entity.AdvisoryPricing;
import com.roof.advisory.advisorypricing.entity.AdvisoryPricingVo;
import com.roof.advisory.advisorypricing.service.api.IAdvisoryPricingService;
import org.roof.roof.dataaccess.api.Page;
import com.roof.advisory.advisoryproduct.dao.api.IAdvisoryProductDao;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProduct;
import com.roof.advisory.advisoryproduct.entity.AdvisoryProductVo;
import com.roof.advisory.advisoryproduct.service.api.IAdvisoryProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author liht
 */
@Service
public class AdvisoryProductService implements IAdvisoryProductService {
    private IAdvisoryProductDao advisoryProductDao;

    @Autowired
    private IAdvisoryPricingService advisoryPricingService;

    @Override
    public Serializable save(AdvisoryProduct advisoryProduct) {
        advisoryProduct.setState(1);
        //保存价格
        if (advisoryProduct.getAdvisoryPricing() != null) {
            AdvisoryPricing pricing = advisoryProduct.getAdvisoryPricing();
            pricing.setState(1);
            pricing.setAppId(advisoryProduct.getAppId());
            pricing.setConsultantId(advisoryProduct.getConsId());
            pricing.setValidityStartTime(advisoryProduct.getValidityStartTime());
            pricing.setValidityEndTime(advisoryProduct.getValidityEndTime());
            pricing.setFixType("ALONE");
            pricing.setStatus(1);
            advisoryPricingService.save(pricing);
            advisoryProduct.setAdvisId(pricing.getId());
        }
        return advisoryProductDao.save(advisoryProduct);
    }

    public void delete(AdvisoryProduct advisoryProduct) {
        advisoryProductDao.delete(advisoryProduct);
    }

    public void deleteByExample(AdvisoryProduct advisoryProduct) {
        advisoryProductDao.deleteByExample(advisoryProduct);
    }

    public void update(AdvisoryProduct advisoryProduct) {
        advisoryProductDao.update(advisoryProduct);
    }

    @Override
    public void updateIgnoreNull(AdvisoryProduct advisoryProduct) {
        if (advisoryProduct.getAdvisoryPricing() != null && advisoryProduct.getId() != null) {
            advisoryPricingService.updateIgnoreNull(advisoryProduct.getAdvisoryPricing());
        }
        advisoryProductDao.updateIgnoreNull(advisoryProduct);
    }

    public void updateByExample(AdvisoryProduct advisoryProduct) {
        advisoryProductDao.update("updateByExampleAdvisoryProduct", advisoryProduct);
    }

    @Override
    public AdvisoryProductVo load(AdvisoryProduct advisoryProduct) {
        AdvisoryProductVo productVo = (AdvisoryProductVo) advisoryProductDao.reload(advisoryProduct);

        AdvisoryPricingVo advisoryProductVo = advisoryPricingService.load(new AdvisoryPricing(productVo.getAdvisId()));
        AdvisoryPricing advisoryPricing = new AdvisoryPricing();
        BeanUtils.copyProperties(advisoryProductVo, advisoryPricing);
        productVo.setAdvisoryPricing(advisoryPricing);
        return productVo;
    }

    @Override
    public AdvisoryProductVo selectForObject(AdvisoryProduct advisoryProduct) {
        return (AdvisoryProductVo) advisoryProductDao.selectForObject("selectAdvisoryProduct", advisoryProduct);
    }

    @Override
    public List<AdvisoryProductVo> selectForList(AdvisoryProduct advisoryProduct) {
        return (List<AdvisoryProductVo>) advisoryProductDao.selectForList("selectAdvisoryProduct", advisoryProduct);
    }

    @Override
    public Page page(Page page, AdvisoryProduct advisoryProduct) {
        page = advisoryProductDao.page(page, advisoryProduct);
        List<AdvisoryProductVo> list = (List<AdvisoryProductVo>) page.getDataList();
        for (AdvisoryProductVo vo : list
                ) {
            AdvisoryPricingVo advisoryProductVo = advisoryPricingService.load(new AdvisoryPricing(vo.getAdvisId()));
            AdvisoryPricing advisoryPricing = new AdvisoryPricing();
            BeanUtils.copyProperties(advisoryProductVo, advisoryPricing);
            vo.setAdvisoryPricing(advisoryPricing);
        }
        return page;
    }

    @Override
    public Serializable findAndCreate(Long consultantId) {
        AdvisoryProduct advisoryProduct = new AdvisoryProduct();
        advisoryProduct.setState(1);
        advisoryProduct.setConsId(consultantId);
        List<AdvisoryProductVo> list = this.selectForList(advisoryProduct);
        if (list.size() > 0) {
            return list.get(0).getId();
        }
        advisoryProduct.setName("默认咨询产品");
        return this.save(advisoryProduct);
    }

    @Autowired
    public void setIAdvisoryProductDao(
            @Qualifier("advisoryProductDao") IAdvisoryProductDao advisoryProductDao
    ) {
        this.advisoryProductDao = advisoryProductDao;
    }


}
