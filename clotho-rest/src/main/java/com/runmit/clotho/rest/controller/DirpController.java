package com.runmit.clotho.rest.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.drip.ActivationCode;
import com.runmit.clotho.core.domain.drip.DripRecord;
import com.runmit.clotho.core.service.ActivationCodeService;
import com.runmit.clotho.core.service.DripService;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.rest.common.OkHttpClientSingleton;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@Controller
@RequestMapping(value = "/drip")
public class DirpController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DirpController.class);

	@Value("${drip.uc.check}")
	private String ucCheckUrl;
	@Value("${drip.uc.check.key}")
	private String ucKey;
	@Value("${drip.pay.present}")
	private String payPresentUrl;
	@Value("${drip.rule.date.end}")
	private String ruleDateEnd;
	@Value("${drip.rule.days.count}")
	private int daysCount;
	@Value("${drip.present.amount}")
	private int amount;

	@Autowired
	private DripService dripService;
	@Autowired
	private ActivationCodeService codeService;

	@RequestMapping(value = "/checkdate")
	public @ResponseBody CommonResp index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommonResp resp = new CommonResp();
		Date now = new Date();
		if (now.after(DateUtils.parseDateTime(ruleDateEnd))) {
			resp.setRtn("-1");
			resp.setRtmsg("活动已过期");
		}else{
			resp.setRtn("0");
		}
		return resp;
	}

	@RequestMapping(value = "/active")
	public @ResponseBody CommonResp active(
			@RequestParam("account") String account,
			@RequestParam("code") String code) {
		CommonResp resp = new CommonResp();
		try {
			Date now = new Date();
			if (now.after(DateUtils.parseDateTime(ruleDateEnd))) {
				resp.setRtn("6");
				resp.setRtmsg("活动已过期");
				return resp;
			}
			int length = code.length();
			if (length == 12) {
				Pattern pattern = Pattern.compile("[0-9a-zA-Z]{12}");
				Matcher matcher = pattern.matcher(code);
				boolean b = matcher.matches();
				if (!b) {
					resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
					resp.setRtmsg("激活码不存在");
					return resp;
				}
			} else {
				Pattern pattern = Pattern.compile("[0-9a-zA-Z]{8}");
				Matcher matcher = pattern.matcher(code);
				boolean b = matcher.matches();
				if (!b) {
					resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
					resp.setRtmsg("激活码不存在");
					return resp;
				}
				ActivationCode acode = codeService.getActivationCode(code);
				if (null == acode) {
					resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
					resp.setRtmsg("激活码不存在");
					return resp;
				}
				if (acode.getStatus() == 0) {
					resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
					resp.setRtmsg("激活码已被使用");
					return resp;
				} else if (!acode.getDateEnd().after(new Date())) {
					resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
					resp.setRtmsg("激活码已过期");
					return resp;
				}
			}
			int uid = 0;
			int accountType = account.contains("@") ? 1 : 2;
			long ts = System.currentTimeMillis();
			StringBuilder sbsign = new StringBuilder();
			sbsign.append(URLEncoder.encode(account, "utf-8")).append("_")
					.append(accountType).append("_").append(ts).append("_")
					.append(ucKey);
			String sign = DigestUtils.md5Hex(sbsign.toString());
			OkHttpClient ucclient = OkHttpClientSingleton.getInstance();
			StringBuilder sburl = new StringBuilder();
			sburl.append("this.ucCheckUrl?account=").append(account)
					.append("&ts=").append(ts).append("&accountType=")
					.append(accountType).append("&sign=").append(sign);
			Request ucrequest = new Request.Builder().url(sburl.toString())
					.get().addHeader("Content-Type", "application/json")
					.addHeader("language", "zh_CN")
					.addHeader("superProjectId", "5")
					.addHeader("clientId", "5").build();
			Response ucresponse = ucclient.newCall(ucrequest).execute();
			JSONObject jsonObject = JSONObject.parseObject(ucresponse.body()
					.string());
			if (!jsonObject.containsKey("rtn")) {
				uid = jsonObject.getIntValue("userid");
			} else {
				resp.setRtn(RestConst.RTN_DRIP_USER_NOTEXIST);
				resp.setRtmsg("账号不存在");
				return resp;
			}

			DripRecord last = dripService.getLast(uid);
			if (null != last) {
				long count = dripService.getCountByUid(uid,
						last.getCreatetime(),
						DateUtils.parseDateTime(ruleDateEnd));
				if (count >= daysCount) {
					resp.setRtn(RestConst.RTN_DRIP_USER_INVALID);
					resp.setRtmsg("超过次数限制");
					return resp;
				}
			}

			JSONObject params = new JSONObject();
			params.put("uid", uid);
			params.put("amount", amount);
			params.put("currencyId", 6);
			params.put("countryCode", "");
			params.put("clientId", 5);
			params.put("presentType", 4);
			params.put("operator", "clotho-rest");

			OkHttpClient client = OkHttpClientSingleton.getInstance();
			Request request = new Request.Builder()
					.url(payPresentUrl)
					.post(RequestBody.create(
							MediaType.parse("application/json"),
							params.toJSONString())).build();
			Response response = client.newCall(request).execute();
			JSONObject result = JSON.parseObject(response.body().string());
			if (result.getIntValue("rtn") != 0) {
				LOGGER.error("drip active pay present {}",
						result.getString("errMsg"));
				resp.setRtn(RestConst.RTN_ERROR);
				resp.setRtmsg("激活失败");
			} else {
				if (length == 8) {
					codeService.update(code);
				}
				DripRecord record = new DripRecord();
				record.setAccount(account);
				record.setUid(uid);
				record.setCode(code);
				record.setAmount(amount);
				dripService.saveDripRecord(record);
				resp.setRtn(RestConst.RTN_OK);
				resp.setRtmsg("激活成功");
			}

		} catch (Exception e) {
			LOGGER.error("drip active error", e);
			resp.setRtn(RestConst.RTN_ERROR);
			resp.setRtmsg("系统异常");
		}

		return resp;
	}
}
