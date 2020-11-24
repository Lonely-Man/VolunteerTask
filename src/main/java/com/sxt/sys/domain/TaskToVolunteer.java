package com.sxt.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("task_volunteer")
@ToString
public class TaskToVolunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Date createDate = new Date();
    private Date updateDate = new Date();
    private int taskId;
    private int volunteerId;
    private Date startDate;
    private Date endDate;
    private String address;
    private Date firstClockDate;
    private Date lastClockDate;
    private Date firstClockAddress;
    private Date lastClockAddress;
}
