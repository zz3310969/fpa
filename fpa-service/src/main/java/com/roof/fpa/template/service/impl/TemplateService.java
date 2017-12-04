package com.roof.fpa.template.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import freemarker.template.TemplateException;
import org.roof.roof.dataaccess.api.Page;
import com.roof.fpa.template.dao.api.ITemplateDao;
import com.roof.fpa.template.entity.Template;
import com.roof.fpa.template.entity.TemplateVo;
import com.roof.fpa.template.service.api.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TemplateService implements ITemplateService {
	private ITemplateDao templateDao;

	public Serializable save(Template template){
		return templateDao.save(template);
	}

	public void delete(Template template){
		templateDao.delete(template);
	}
	
	public void deleteByExample(Template template){
		templateDao.deleteByExample(template);
	}

	public void update(Template template){
		templateDao.update(template);
	}
	
	public void updateIgnoreNull(Template template){
		templateDao.updateIgnoreNull(template);
	}
		
	public void updateByExample(Template template){
		templateDao.update("updateByExampleTemplate", template);
	}

	public TemplateVo load(Template template){
		return (TemplateVo)templateDao.reload(template);
	}
	
	public TemplateVo selectForObject(Template template){
		return (TemplateVo)templateDao.selectForObject("selectTemplate",template);
	}
	
	public List<TemplateVo> selectForList(Template template){
		return (List<TemplateVo>)templateDao.selectForList("selectTemplate",template);
	}
	
	public Page page(Page page, Template template) {
		return templateDao.page(page, template);
	}

	public String mergeTemplate(String templateStr, Object param) throws TemplateException, IOException {
		freemarker.template.Template t = new freemarker.template.Template(null, new StringReader(templateStr), null);
		StringWriter writer = new StringWriter();
		t.process(param, writer);
		return writer.toString();
	}

	@Autowired
	public void setITemplateDao(
			@Qualifier("templateDao") ITemplateDao  templateDao) {
		this.templateDao = templateDao;
	}
	

}
