package com.roof.fpa.withdraw.controller;

import com.alibaba.fastjson.JSON;
import com.roof.fpa.withdraw.entity.Withdraw;
import com.roof.fpa.withdraw.entity.WithdrawVo;
import com.roof.fpa.withdraw.service.api.IWithdrawService;
import com.roof.fpa.withdraw.service.api.IWithdrawWorkflowService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.user.entity.BaseUser;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("workflow/withdraw")
public class WithdrawWorkflowController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected IWithdrawService withdrawService;

    @Autowired
    protected IWithdrawWorkflowService withdrawWorkflowService;

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
    Result startWorkflow(Withdraw withdraw, HttpServletRequest request) {
        try {
            User user = (User) BaseUserContext.getCurrentUser(request);
            withdraw.setProcessUserId(user.getId().toString());
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = withdrawWorkflowService.startWorkflow(withdraw, variables);
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
        withdrawWorkflowService.findTodoTasks(user.getId().toString(), page, null);
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
        withdrawWorkflowService.findRunningProcessInstaces(page, pageParams);
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
        withdrawWorkflowService.findRunningProcessInstaces(page, pageParams);
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
        withdrawWorkflowService.findFinishedProcessInstaces(page, pageParams);
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
        Withdraw withdraw = withdrawService.load(new Withdraw(id));
        return new Result(Result.SUCCESS, withdraw);
    }

    /**
     * 读取详细数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detail-with-vars/{id}/{taskId}")
    @ResponseBody
    public Result getwithdrawWithVars(@PathVariable("id") Long id, @PathVariable("taskId") String taskId) {
        WithdrawVo withdraw = withdrawService.load(new Withdraw(id));
        Map<String, Object> variables = taskService.getVariables(taskId);
        withdraw.setVariables(variables);
        return new Result(Result.SUCCESS, withdraw);
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
            taskService.complete(taskId, variables);
            return new Result(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("error on complete task {}, variables={}", new Object[]{taskId, JSON.toJSONString(variables), e});
            return new Result(Result.ERROR);
        }
    }

}
