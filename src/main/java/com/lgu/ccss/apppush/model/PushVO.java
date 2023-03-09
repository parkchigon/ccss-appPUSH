package com.lgu.ccss.apppush.model;

public class PushVO {
	private String requestPart;
	private String requestTime;
	private String pushId;
	private String serviceId;
	private String servicePass;
	private String applicationId;
	private String serviceKey;
	private String subServiceId;
	private String payload;
	
	public PushVO(){
	}
	public PushVO(String applicationId, String serviceId, String servicePass) {
		this.applicationId = applicationId;
		this.serviceId = serviceId;
		this.servicePass = servicePass;
	}
	
	public String getRequestPart() {
		return requestPart;
	}
	public void setRequestPart(String requestPart) {
		this.requestPart = requestPart;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServicePass() {
		return servicePass;
	}
	public void setServicePass(String servicePass) {
		this.servicePass = servicePass;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public String getSubServiceId() {
		return subServiceId;
	}
	public void setSubServiceId(String subServiceId) {
		this.subServiceId = subServiceId;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}
