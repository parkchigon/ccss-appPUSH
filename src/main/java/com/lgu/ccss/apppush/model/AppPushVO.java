package com.lgu.ccss.apppush.model;

public class AppPushVO {
	
	private String msgId;
	private String msgStatus;
	private String code;
	private String msgTitle;
	private String msgCont;
	private String msgType;
	private String recvPhoneNo;
	private String sendType;
	private String svrId;
	private String orgNo;
	private String callbackNo;
	private String sendDt;
	private String regDt;
	private String regId;
	private String updDt;
	private String updId;
	private String reqPart;
	private String deviceType;
	private Integer sendTryCnt;
	
	private String carOem;

	private int rowCount;


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


	public String getMsgStatus() {
		return msgStatus;
	}


	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsgTitle() {
		return msgTitle;
	}


	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}


	public String getMsgCont() {
		return msgCont;
	}


	public void setMsgCont(String msgCont) {
		this.msgCont = msgCont;
	}


	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getRecvPhoneNo() {
		return recvPhoneNo;
	}


	public void setRecvPhoneNo(String recvPhoneNo) {
		this.recvPhoneNo = recvPhoneNo;
	}


	public String getSendType() {
		return sendType;
	}


	public void setSendType(String sendType) {
		this.sendType = sendType;
	}


	public String getSvrId() {
		return svrId;
	}


	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}


	public String getOrgNo() {
		return orgNo;
	}


	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}


	public String getCallbackNo() {
		return callbackNo;
	}


	public void setCallbackNo(String callbackNo) {
		this.callbackNo = callbackNo;
	}


	public String getSendDt() {
		return sendDt;
	}


	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	public String getUpdDt() {
		return updDt;
	}


	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}


	public String getUpdId() {
		return updId;
	}


	public void setUpdId(String updId) {
		this.updId = updId;
	}


	public String getReqPart() {
		return reqPart;
	}


	public void setReqPart(String reqPart) {
		this.reqPart = reqPart;
	}


	public int getRowCount() {
		return rowCount;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	public String getDeviceType() {
		return deviceType;
	}


	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	
	public Integer getSendTryCnt() {
		return sendTryCnt;
	}


	public void setSendTryCnt(Integer sendTryCnt) {
		this.sendTryCnt = sendTryCnt;
	}

	public String getCarOem() {
		return carOem;
	}


	public void setCarOem(String carOem) {
		this.carOem = carOem;
	}


	@Override
	public String toString() {
		return "AppPushVO [msgId=" + msgId + ", msgStatus=" + msgStatus + ", code=" + code + ", msgTitle=" + msgTitle
				+ ", msgCont=" + msgCont + ", msgType=" + msgType + ", recvPhoneNo=" + recvPhoneNo + ", sendType="
				+ sendType + ", svrId=" + svrId + ", orgNo=" + orgNo + ", callbackNo=" + callbackNo + ", sendDt="
				+ sendDt + ", regDt=" + regDt + ", regId=" + regId + ", updDt=" + updDt + ", updId=" + updId
				+ ", reqPart=" + reqPart + ", deviceType=" + deviceType + ", sendTryCnt=" + sendTryCnt + ", rowCount="
				+ rowCount + ", carOem=" + carOem + "]";
	}
	
	
}
