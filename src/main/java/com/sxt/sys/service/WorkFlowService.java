//package com.sxt.sys.service;
//
//import java.io.InputStream;
//import java.util.List;
//import java.util.Map;
//
//import org.activiti.engine.repository.ProcessDefinition;
//
//import com.sxt.sys.Vo.WorkFlowVo;
//import com.sxt.sys.common.DataGridView;
//import com.sxt.sys.domain.Leavebill;
//
///**
// * <p>
// *  服务类
// * </p>
// *
// */
//public interface WorkFlowService {
//	/**
//	 * 查询流程部署信息
//	 */
//	public DataGridView queryProcessDeploy(WorkFlowVo workFlowVo);
//
//	/**
//	 * 查询流程定义信息
//	 */
//	DataGridView queryAllProcessDefinition(WorkFlowVo workFlowVo);
//
//	/**
//	 * 添加流程部署
//	 * @param inputStream
//	 * @param deploymentName
//	 */
//	void addWorkFlow(InputStream inputStream, String deploymentName);
//
//	/**
//	 * 删除流程部署
//	 * @param deploymentId
//	 */
//	Boolean deleteWorkFlow(String deploymentId);
//
//	/**
//	 * 根据流程部署ID查询流程图
//	 * @param deploymentId
//	 * @return
//	 */
//	InputStream queryProcessDeploymentImage(String deploymentId);
//
//	/**
//	 * 启动流程
//	 * @param leaveBillId
//	 */
//	void startProcess(Integer leaveBillId);
//
//	/**
//	 * 查询当前登录用户的待办任务
//	 * @param workFlowVo
//	 * @return
//	 */
//	DataGridView queryCurrentUserTask(WorkFlowVo workFlowVo);
//
//	/**
//	 * 根据任务ID查询请假单
//	 */
//	public Leavebill queryLeaveBillByTaskId(String taskId);
//
//	/**
//	 * 查询连线信息
//	 */
//	public List<String> queryOutComeByTaskId(String taskId);
//
//	/**
//	 * 根据Id查询批注信息
//	 */
//	DataGridView queryCommentByTaskId(String taskId);
//
//	/**
//	 * 完成任务
//	 * @param workFlowVo
//	 */
//	void completeTask(WorkFlowVo workFlowVo);
//
//	/**
//	 * 根据任务ID查询流程定义对象
//	 */
//	public ProcessDefinition queryProcessDefinitionByTaskId(String taskId);
//
//	/**
//	 * 根据任务ID查询任务节点坐标
//	 */
//	public Map<String, Object> queryTaskCoordinateByTaskId(String taskId);
//
//	/**
//	 * 根据请假单ID查询批注信息
//	 */
//	DataGridView queryCommentByLeaveBill(Integer id);
//
//	/**
//	 * 查看历史审批记录
//	 * @param workFlowVo
//	 */
//	DataGridView queryCurrentUserHistoryTask(WorkFlowVo workFlowVo);
//
//	/**
//	 * 根据实例ID查询Busnesskey
//	 */
//	public Leavebill queryBusinessKeyByProcessInstanceId(String processInstanceId);
//
//}
