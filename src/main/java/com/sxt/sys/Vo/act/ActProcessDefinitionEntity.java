package com.sxt.sys.Vo.act;

import lombok.Data;

/**
 * 流程定义的转化类
 * @author LJH
 *
 */
@Data
public class ActProcessDefinitionEntity {
	private String id;
	private String name;
	private String key;
	private Integer version;
	private String deploymentId;
	private String resourceName;
	private String diagramResourceName;
	public String getId() {
		return id;
	}

}
