package com.punch.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author xiachao
 * @date 2020/07/24 9:59
 */
@Data
public class BindPushDto implements Serializable {

    private static final long serialVersionUID = -3821395832156720307L;

    @NotBlank(message = "loginId不能为空")
    private String loginId;

    @NotBlank(message = "pushId不能为空")
    private String pushId;
}
