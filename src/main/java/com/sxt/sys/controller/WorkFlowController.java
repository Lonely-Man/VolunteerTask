package com.sxt.sys.controller;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sxt.sys.Vo.WorkFlowVo;
import com.sxt.sys.common.DataGridView;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.domain.Leavebill;
import com.sxt.sys.service.LeavebillService;
import com.sxt.sys.service.WorkFlowService;

@RestController
@RequestMapping("workFlow")
public class WorkFlowController {
	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private LeavebillService leavebillService;

	/**
	 * 加载部署信息数据
	 */
	@RequestMapping("loadAllDeployment")
	public DataGridView loadAllDeployment(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryProcessDeploy(workFlowVo);
	}

	/**
	 * 加载流程定义信息数据
	 */
	@RequestMapping("loadAllProcessDefinition")
	public DataGridView loadAllProcessDefinition(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryAllProcessDefinition(workFlowVo);
	}

	/**
	 * 加载流程定义信息数据
	 */
	@RequestMapping("addWorkFlow")
	public ResultObj addWorkFlow(MultipartFile mf, WorkFlowVo workFlowVo) {
		try {
			this.workFlowService.addWorkFlow(mf.getInputStream(), workFlowVo.getDeploymentName());
			return ResultObj.DEPLOYMENT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DEPLOYMENT_ERROR;
		}
	}


	/**
	 * 删除流程定义信息数据
	 */
	@RequestMapping("deleteWorkFlow")
	public ResultObj deleteWorkFlow(WorkFlowVo workFlowVo) {
		try {
			Boolean isTask = this.workFlowService.deleteWorkFlow(workFlowVo.getDeploymentId());
			if (isTask){
				return ResultObj.DELETE_SUCCESS;
			}else{
				return ResultObj.DELETE_ERROR_TASK;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 根据部署ID查看流程图
	 */
	@RequestMapping("viewProcessImage")
	public void viewProcessImage(WorkFlowVo workFlowVo, HttpServletResponse response) {
		InputStream stream = this.workFlowService.queryProcessDeploymentImage(workFlowVo.getDeploymentId());
		try {
			BufferedImage image = ImageIO.read(stream);
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write(image, "JPEG", outputStream);
			stream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 启动流程
	 */
	@RequestMapping("startProcess")
	public ResultObj viewProcessImage(WorkFlowVo workFlowVo) {
		try {
			this.workFlowService.startProcess(workFlowVo.getId());
			return ResultObj.SUMBIT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.SUMBIT_ERROR;
		}

	}

	/**
	 * 查询当前登录用户的待办任务
	 */
	@RequestMapping("loadCurrentUserTask")
	public DataGridView loadCurrentUserTask(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryCurrentUserTask(workFlowVo);
	}

	/**
	 * 根据任务ID查询请假单
	 */
	@RequestMapping("queryLeaveBillByTaskId")
	public Leavebill queryLeaveBillByTaskId(WorkFlowVo workFlowVo, Model model) {
		return this.workFlowService.queryLeaveBillByTaskId(workFlowVo.getTaskId());
	}

	/**
	 * 根据任务ID查询连线信息
	 */
	@RequestMapping("loadOutComeByTaskId")
	public Map<String, Object> loadOutComeByTaskId(WorkFlowVo workFlowVo, Model model) {
		List<String> btns = this.workFlowService.queryOutComeByTaskId(workFlowVo.getTaskId());
		Map<String, Object> map = new HashMap<>();
		map.put("list", btns);
		return map;
	}

	/**
	 * 根据Id查询批注信息
	 */
	@RequestMapping("loadAllCommentByTaskId")
	public DataGridView loadAllCommentByTaskId(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryCommentByTaskId(workFlowVo.getTaskId());
	}

	/**
	 * 完成任务
	 */
	@RequestMapping("completeTask")
	public ResultObj toTask(WorkFlowVo workFlowVo) {
		try {
			this.workFlowService.completeTask(workFlowVo);
			return ResultObj.COMPLETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.COMPLETE_ERROR;
		}
	}

	/**
	 * 根据任务ID查看流程图
	 * @return
	 */
	@RequestMapping("viewProcessImageByTaskId")
	public void viewProcessImageByTaskId(WorkFlowVo workFlowVo, HttpServletResponse response) {
		ProcessDefinition processDefinition = this.workFlowService
				.queryProcessDefinitionByTaskId(workFlowVo.getTaskId());
		String deploymentId = processDefinition.getDeploymentId();
		workFlowVo.setDeploymentId(deploymentId);

		InputStream stream = this.workFlowService.queryProcessDeploymentImage(workFlowVo.getDeploymentId());
		try {
			BufferedImage image = ImageIO.read(stream);
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write(image, "JPEG", outputStream);
			stream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据任务ID查看流程图绘线
	 */
	@RequestMapping("viewProcessImageFlowByTaskId")
	public Map<String, Object> viewProcessImageFlowByTaskId(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryTaskCoordinateByTaskId(workFlowVo.getTaskId());
	}

	/**
	 * 根据请假单Id查询请假单信息
	 */
	@RequestMapping("loadLeaveBillById")
	public Leavebill loadLeaveBillById(WorkFlowVo workFlowVo) {
		return this.leavebillService.getById(workFlowVo.getId());
	}

	/**
	 * 根据请假单ID查询批注信息
	 */
	@RequestMapping("loadCommentByLeaveBillId")
	public DataGridView loadCommentByLeaveBillId(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryCommentByLeaveBill(workFlowVo.getId());
	}

	/**
	 * 查看我的审批记录 
	 */
	@RequestMapping("loadCurrentUserHistoryTask")
	public DataGridView CurrentUserHistoryTask(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryCurrentUserHistoryTask(workFlowVo);
	}

	/**
	 * 根据任务ID查询请假单信息
	 */
	@RequestMapping("loadBusinessKeyByProcessInstanceId")
	public Leavebill loadBusinessKeyByProcessInstanceId(WorkFlowVo workFlowVo) {
		return this.workFlowService.queryBusinessKeyByProcessInstanceId(workFlowVo.getProcessInstanceId());
	}

}
