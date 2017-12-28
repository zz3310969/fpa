package org.activiti.web.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.util.Page;
import org.activiti.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/workflow/historyController")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@RequestMapping(value = "/list/{processInstanceId}")
	public ModelAndView running(@PathVariable("processInstanceId") String processInstanceId, Model model,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/workflow/history-list");
		Page<HistoricTaskInstance> page = new Page<HistoricTaskInstance>(PageUtil.PAGE_SIZE);
		int[] pageParams = PageUtil.init(page, request);

		HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId);
		List<HistoricTaskInstance> list = historicTaskInstanceQuery.listPage(pageParams[0], pageParams[1]);
		page.setResult(list);
		page.setTotalCount(historicTaskInstanceQuery.count());
		mav.addObject("page", page);
		return mav;
	}

}
