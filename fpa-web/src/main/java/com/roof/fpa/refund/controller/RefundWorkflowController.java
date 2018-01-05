package com.roof.fpa.refund.controller;

import com.alibaba.fastjson.JSON;
import com.roof.fpa.refund.entity.Refund;
import com.roof.fpa.refund.entity.RefundVo;
import com.roof.fpa.refund.service.api.IRefundService;
import com.roof.fpa.refund.service.api.IRefundWorkflowService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("workflow/refund")
public class RefundWorkflowController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IRefundService refundService;

    @Autowired
    protected IRefundWorkflowService refundWorkflowService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    /**
     * 启动请假流程
     */
    @ApiOperation(value = "启动流程")
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public @ResponseBody
    Result startWorkflow(Refund refund, HttpServletRequest request) {
        try {
            User user = (User) BaseUserContext.getCurrentUser(request);
            refund.setProcessUserId(user.getId().toString());
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = refundWorkflowService.startWorkflow(refund, variables);
            return new Result(Result.SUCCESS, "流程已启动，流程ID：" + processInstance.getId());
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                return new Result(Result.ERROR, "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动流程失败：", e);
                return new Result(Result.ERROR, "系统内部错误！");
            }
        } catch (Exception e) {
            logger.error("启动流程失败：", e);
            return new Result(Result.ERROR, "系统内部错误！");
        }
    }

    /**
     * 获取任务列表
     */
    @ApiOperation(value = "根据登入用户获取任务列表")
    @RequestMapping(value = "list/task")
    public @ResponseBody
    Result taskList(HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        User user = (User) BaseUserContext.getCurrentUser(request);
        refundWorkflowService.findTodoTasks(user, page, null);
        return new Result(Result.SUCCESS, page);
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @ApiOperation(value = "读取运行中的流程实例")
    @RequestMapping(value = "list/running")
    public @ResponseBody Result runningList(HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        int[] pageParams = {0, 10};
        refundWorkflowService.findRunningProcessInstaces(page, pageParams);
        return new Result(Result.SUCCESS, page);
    }

    /**
     * 读取流程历史
     *
     * @return
     */
    @RequestMapping(value = "list/history")
    public @ResponseBody Result historyList(HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        int[] pageParams = {1, 2};
        refundWorkflowService.findRunningProcessInstaces(page, pageParams);
        return new Result(Result.SUCCESS, page);
    }

    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "list/finished")
    public @ResponseBody Result finishedList(HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        int[] pageParams = {1, 2};
        refundWorkflowService.findFinishedProcessInstaces(page, pageParams);
        return new Result(Result.SUCCESS, page);
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "task/claim/{id}")
    public @ResponseBody Result claim(@PathVariable("id") String taskId, HttpServletRequest request) {
        User user = (User) BaseUserContext.getCurrentUser(request);
        taskService.claim(taskId, user.getId().toString());
        return new Result(Result.SUCCESS, "任务已签收");
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}")
    public @ResponseBody Result getDetail(@PathVariable("id") Long id) {
        Refund refund = refundService.load(new Refund(id));
        return new Result(Result.SUCCESS, refund);
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail-with-vars/{id}/{taskId}")
    @ResponseBody
    public Result getRefundWithVars(@PathVariable("id") Long id, @PathVariable("taskId") String taskId) {
        RefundVo refund = refundService.load(new Refund(id));
        Map<String, Object> variables = taskService.getVariables(taskId);
        refund.setVariables(variables);
        return new Result(Result.SUCCESS, refund);
    }

    /**
     * 完成任务
     *
     * @return
     */
    @RequestMapping(value = "complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result complete(@PathVariable("id") String taskId, Map<String, Object> variables) {
        try {
            System.out.println("入参:" + JSON.toJSONString(variables));
            refundWorkflowService.completeWorkflow(taskId, variables);
            return new Result(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, JSON.toJSONString(variables), e});
            return new Result(Result.ERROR,e.getMessage());
        }
    }

}
