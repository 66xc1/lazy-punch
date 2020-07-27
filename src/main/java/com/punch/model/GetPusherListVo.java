package com.punch.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiachao
 * @date 2020/07/24 10:40
 */
@Data
public class GetPusherListVo implements Serializable {

    private static final long serialVersionUID = 6066724348966714459L;

    private String pushId;

    private String nickName;

    private String headImg;

}
