package com.zhileiedu.spring.weixinpay.controller;

import com.zhileiedu.spring.weixinpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: 王志雷
 * @Date: 2020/5/9 18:02
 */
@RestController
@Slf4j
public class WxPayController {


	@Autowired
	WxPayService wxPayService;

	@GetMapping("/pay")
	public Map wxPay(){
		Map aNative = wxPayService.createNative();
		return aNative;
	}






}
