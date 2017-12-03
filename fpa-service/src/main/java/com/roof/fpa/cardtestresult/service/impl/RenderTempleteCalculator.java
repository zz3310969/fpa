package com.roof.fpa.cardtestresult.service.impl;

import com.roof.chain.support.NodeResult;
import com.roof.fpa.cardtestresult.entity.CardTestResultVo;
import com.roof.fpa.cardtestresult.entity.GeneralCardTestCustomerResult;
import com.roof.fpa.scene.entity.Scene;
import com.roof.fpa.scene.service.api.ISceneService;
import com.roof.fpa.template.service.impl.TemplateService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 渲染模板
 */
public class RenderTempleteCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenderTempleteCalculator.class);
    private TemplateService templateService;
    private ISceneService sceneService;

    public NodeResult doNode(CardTestResultVo cardTestResultVo, GeneralCardTestCustomerResult generalCardTestCustomerResult) {
        Scene scene = sceneService.load(new Scene(cardTestResultVo.getSceneId()));
        com.roof.fpa.template.entity.Template template = templateService.load(new com.roof.fpa.template.entity.Template(scene.getTemplateId()));
        generalCardTestCustomerResult.setTemplateId(String.valueOf(template.getId()));
        NodeResult nodeResult = new NodeResult(NodeResult.SUCCESS);
//        try {
//            nodeResult.setMessage(mergeTemplate(template.getContent(), generalCardTestCustomerResult));
//        } catch (TemplateException | IOException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
        return nodeResult;
    }

    protected String mergeTemplate(String templateStr, Object param) throws TemplateException, IOException {
        Template t = new Template(null, new StringReader(templateStr), null);
        StringWriter writer = new StringWriter();
        t.process(param, writer);
        return writer.toString();
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    public ISceneService getSceneService() {
        return sceneService;
    }

    public void setSceneService(ISceneService sceneService) {
        this.sceneService = sceneService;
    }
}