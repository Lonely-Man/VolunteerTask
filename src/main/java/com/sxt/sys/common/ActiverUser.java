package com.sxt.sys.common;

import com.sxt.sys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * shiro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiverUser implements Serializable {

    private static final long serialVersionUID = 5814843856302532367L;
    private User user;
    private List<String> roles;
    private List<String> permissions;
}
