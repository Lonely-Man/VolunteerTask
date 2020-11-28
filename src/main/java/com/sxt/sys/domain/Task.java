package com.sxt.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**活动
 * @author zouqijun
 * @version 1.0
 * @date 2020/11/24 14:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("task")
@ToString
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Date createDate=new Date();
    private Date updateDate=new Date();
    //活动标题
    @NotNull(message = "名字不能为空")
    @Valid
    private String name;
    //地址
    @NotNull(message = "地址不能为空")
    @Valid
    private String address;
    //开始时间
    @NotNull(message = "开始时间不能为空")
    @Valid
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    //结束时间
    @NotNull(message = "结束时间不能为空")
    @Valid
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    //活动内容
    private String content;
    //总人数
    private int  totalNum;
    //已参加人数
    private int  signedNum;
    @NotNull(message = "地址信息不能为空")
    @Valid
    private double latitude;
    @NotNull(message = "地址信息不能为空")
    @Valid
    private double longitude;
    public void copyParam(Task task){
        this.setName(task.getName());
        this.setAddress(task.getAddress());
        this.setUpdateDate(new Date());
        this.setContent(task.getContent());
        this.setLatitude(task.getLatitude());
        this.setLongitude(task.getLongitude());
        this.setStartDate(task.getStartDate());
        this.setEndDate(task.getEndDate());
        this.setTotalNum(task.getTotalNum());
    }
}
