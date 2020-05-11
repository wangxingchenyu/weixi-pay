package com.zhileiedu.spring.weixinpay.dto;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王志雷
 * @Date: 2020/5/9 18:06
 */
public class ResultMap extends HashMap<String, Object> {
	public ResultMap() {
		put("state", true);
		put("code", 0);
		put("msg", "success");
	}

	public static ResultMap error(int code, String msg) {
		ResultMap r = new ResultMap();
		r.put("state", false);
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultMap error(String msg) {

		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}

	public static ResultMap error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
	}

	public static ResultMap ok(String msg) {
		ResultMap r = new ResultMap();
		r.put("msg", msg);
		return r;
	}

	public static ResultMap ok(Map<String, Object> par) {
		ResultMap r = new ResultMap();
		r.putAll(par);
		return r;
	}

	public static ResultMap ok() {
		return new ResultMap();
	}

	@Override
	public ResultMap put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}