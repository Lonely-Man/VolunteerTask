package com.sxt.sys.Vo;

import com.sxt.sys.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    private static final long serialVersionUID = 1L;

    //分页参数
    private Integer page=1;
    private Integer limit=10;

}
