package com.runmit.clotho.rest.common;

/**
 * Created by XP on 2014/8/21.
 */
public class RestConst {

	/*
	 * 接口通用返回值类型的说明
	 */
	public final static String RTN_OK = "0";// 成功返回值
	public final static String RTN_ERROR = "500";// 异常返回值

	public final static String RTN_PARAMLOST = "51";// 参数缺失
	public final static String RTN_URINOTEXISTS = "52";// 服务器无对应URI

	/*
	 * 获取升级版本返回信息
	 */
	public final static String RTN_GETUPGRADE_VERSION_LASTEST = "-1";// 当前版本无需升级
	public final static String RTN_GETUPGRADE_CURRENTVERSION_NOTEXIST = "1";// 传入版本不存在
	public final static String RTN_GETUPGRADE_CLIENTIDGETVERSION_NOTEXIST = "2";// clientid无对应最新版本信息
	public final static String RTN_GETUPGRADE_PLANGETVERSION_NOTEXIST = "3";// 关联版本无法找到基础版本信息

	/*
	 * 活动管理
	 */
	public final static String RTN_ACTIVITY_NOTEXIST = "1";
	public final static String RTN_ACTIVITY_HADJOINED = "2";
	public final static String RTN_ACTIVITY_NOTENOUGHSCORE = "3";

	public final static String RTN_DRIP_CODE_ILLEGAL = "1";
	public final static String RTN_DRIP_CODE_NOTEXIST = "2";
	public final static String RTN_DRIP_USER_NOTEXIST = "3";
	public final static String RTN_DRIP_USER_INVALID = "4";

	/************************* 浏览器后台常量值 ******************************/
	public final static String RTN_FAILED = "1";//失败返回值

	public final static String RTN_FAILED_REPEAT = "该用户已设置过默认搜索引擎,不能重复设置!";//重复设置值提示信息
}
