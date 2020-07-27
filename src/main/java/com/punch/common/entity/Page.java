package com.punch.common.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页
 *
 * @author xiachao
 * @date 2019/10/18 14:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -3412249415095629775L;

	private Integer totalCount;

	private T list;
}
