package com.pwz.webservice;

import javax.jws.WebService;

/**
 * 
 * @author zhaoxb
 * 类说明
 */
@WebService(targetNamespace="https//www.jyfuwu.com/interface/xxx/wsdl")
public interface JYFuWuOAWebService {

	/**
	 * 查询门禁打卡记录接口
	 * @param StartDateTime
	 * @param EndDateTime
	 * @param Userid
	 * @return
	 */
	public String QueryAttendanceDate(String StartDateTime, String EndDateTime, String Userid);
	
	/**
	 * 会议室详情信息
	 * @return
	 */
	public String GetMeetingRoomInfo();
	
	
	/**
	 * 查询会议信息
	 * @param StartDateTime
	 * @param EndDateTime
	 * @param HYDD
	 * @return
	 */
	public String QueryMeetingRoomData(String StartDateTime, String EndDateTime, String HYDD);
	
	/**
	 * 查询档案接口
	 * @return
	 */
	public String QueryFileInfo(String gcbh, String gcmc);
	
	/**
	 * 查询短信结果
	 * @return
	 */
	public String QuerySMSStatus(String TaskID);
	
	/**
	 * 发送短信接口
	 */
	public String SendSMS(String Mobilephone, String Message);

	/**
	 * 查询会议信息
	 * @param StartDateTime
	 * @param EndDateTime
	 * @return
	 * @author yzj
	 * @date 2017年9月19日 上午10:38:17
	 */
	String QueryHuiYiInfo(String StartDateTime, String EndDateTime);
	
	/**
	 * 查询工作人员绩效信息
	 * @param BeginDate
	 * @param EndDate
	 * @return
	 * @author houxw
	 * @date 2017年11月22日 上午10:35:33
	 */
	public String GetJobTaskAnalysis(String BeginDate, String EndDate,String TaskID,String UserName);
}
