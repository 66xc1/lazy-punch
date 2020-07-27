package com.punch.common.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.BindingResult;

import lombok.Data;

/**
 * 统一返回结果
 *
 * @author xiachao
 * @date 2019/10/18 14:32
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -7354220488953274990L;

	private boolean state;

	private String msg;

	private String code;

	private T data;

	public Result() {
	}

	public static <T> Result<T> success() {
		Result<T> result = new Result<>();
		result.setState(true);
		return result;
	}

	public static <T> Result<T> success(T data) {
		Result<T> result = Result.success();
		result.setData(data);
		return result;
	}

	public static <T> Result<T> success(T data, String msg) {
		Result<T> result = Result.success();
		result.setData(data);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<Page<List<T>>> successPage(Integer totalCount, List<T> list) {
		return Result.success(new Page<>(totalCount, list));
	}

	public static <T> Result<T> fail(String msg) {
		Result<T> result = new Result<>();
		result.setState(false);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> fail(String code, String msg) {
		Result<T> result = Result.fail(msg);
		result.setCode(code);
		return result;
	}

	public static <T> Result<T> fail(String code, String msg, T data) {
		Result<T> result = Result.fail(code, msg);
		result.setData(data);
		return result;
	}

	public static <T> Result<T> fail(BindingResult bindingResult) {
		return Result.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
	}

}
