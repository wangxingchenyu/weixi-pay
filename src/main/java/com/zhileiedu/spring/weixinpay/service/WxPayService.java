package com.zhileiedu.spring.weixinpay.service;

import com.github.wxpay.sdk.WXPayUtil;
import com.zhileiedu.spring.weixinpay.utils.HttpClient;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王志雷
 * @Date: 2020/5/10 21:35
 */
@Service
public class WxPayService {


	// 生成微信支付的二维码
	public Map createNative() {

		String orderNo = "20209218";

		try {
			// 1 使用map设置生成二维码需要参数
			Map m = new HashMap();
			m.put("appid", "wx74862e0dfcf69954");
			m.put("mch_id", "1558950191");
			m.put("nonce_str", WXPayUtil.generateNonceStr());
			m.put("body", "标题容"); //课程标题
			m.put("out_trade_no", orderNo); //订单号
			m.put("total_fee", "1");
			m.put("spbill_create_ip", "127.0.0.1");
			m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
			// m.put("trade_type", "NATIVE");
			m.put("trade_type", "MWEB");

			// 2 发送httpclient请求，传递参数xml格式，微信支付提供的固定的地址
			HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
			// 设置xml格式的参数
			client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));

			client.setHttps(true);
			// 执行post请求
			client.post();

			//3 得到发送的结果	("取到的结果也是xml,所以也要转成map")
			String xml = client.getContent();
			// 把xml格式转换map集合，把map集合返回
			Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

			// 封装返回的数据
			Map map = new HashMap();
			map.put("out_trade_no", orderNo);
			map.put("total_fee", "1");
			map.put("result_code", resultMap.get("result_code"));
			map.put("code_url", resultMap.get("code_url"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	//查询订单支付状态
	public Map<String, String> queryPayStatus(String orderNo) {
		try {
			//1、封装参数
			Map m = new HashMap<>();
			m.put("appid", "wx74862e0dfcf69954");
			m.put("mch_id", "1558950191");
			m.put("out_trade_no", orderNo);
			m.put("nonce_str", WXPayUtil.generateNonceStr());

			//2 发送httpclient
			HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
			client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
			client.setHttps(true);
			client.post();

			//3 得到请求返回内容
			String xml = client.getContent();
			Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
			//6、转成Map再返回
			return resultMap;
		} catch (Exception e) {
			return null;
		}
	}

	// 添加支付记录

}
