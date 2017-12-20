package com.roof.fpa.template.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.roof.fpa.template.dao.api.ITemplateDao;
import com.roof.fpa.template.entity.Template;
import com.roof.fpa.template.entity.TemplateVo;
import com.roof.fpa.template.service.api.ITemplateService;
import freemarker.template.TemplateException;
import org.roof.roof.dataaccess.api.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class TemplateService implements ITemplateService {
	private ITemplateDao templateDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateService.class);

	@Autowired
	private ListeningExecutorService listeningExecutorService;
	private LoadingCache<Long, TemplateVo> cache = CacheBuilder.newBuilder()
			.maximumSize(5)
			.refreshAfterWrite(5, TimeUnit.MINUTES)
			.build(new CacheLoader<Long, TemplateVo>() {
				@Override
				public TemplateVo load(Long id) throws Exception {
					return loadByBase(id);
				}

				@Override
				public ListenableFuture<TemplateVo> reload(Long key, TemplateVo oldValue) throws Exception {
					return listeningExecutorService.submit(new Callable<TemplateVo>() {
						@Override
						public TemplateVo call() throws Exception {
							return loadByBase(key);
						}
					});
				}
			});

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

	public TemplateVo loadByBase(Long id){
		return (TemplateVo)templateDao.reload(new Template(id));
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


	public TemplateVo loadByCache(Long id){
		try {
			return cache.get(id);
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage(),e);
		}

		return  loadByBase(id);

	}

	@Autowired
	public void setITemplateDao(
			@Qualifier("templateDao") ITemplateDao  templateDao) {
		this.templateDao = templateDao;
	}
	

}
