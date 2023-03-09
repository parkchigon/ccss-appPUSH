package com.lgu.ccss.apppush.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PushConf {
	
	// System Properties
	@Value("#{systemProperties.SERVER_ID}")
	private String svrId;
	
	// Daemon config
	@Value("${delay.time}")
	private String delayTime;
	@Value("${appPush.vectorSize}")
	private int vectorSize;
	@Value("${appPush.threadCnt}")
	private int threadCnt;
	@Value("${appPush.systemId}")
	private String systemId;
	@Value("${db.rowCount}")
	private int rowCount;
	
	// Push Config
	@Value("${push.domain}")
	private String pushDomain;
	@Value("${push.port}")
	private String pushPort;
	
	@Value("${http.client.connection.timeout}")
	private String connectionTimeout;

	// Push GCM Config
	@Value("${push.gcm.service.id}")
	private String pushGcmServiceId;
	@Value("${push.gcm.service.passwd}")
	private String pushGcmServicePasswd;
	@Value("${push.gcm.api.key}")
	private String pushGcmApiKey;
	@Value("${push.gcm.application.id}")
	private String pushGcmApplicationId;
	
	// Push APNS Config
	@Value("${push.apns.service.id}")
	private String pushApnsServiceId;
	@Value("${push.apns.service.passwd}")
	private String pushApnsServicePasswd;
	@Value("${push.apns.application.id}")
	private String pushApnsApplicationId;
	
	// Push BM-GCM Config
	@Value("${bm.push.gcm.service.id}")
	private String bmPushGcmServiceId;
	@Value("${bm.push.gcm.service.passwd}")
	private String bmPushGcmServicePasswd;
	@Value("${bm.push.gcm.api.key}")
	private String bmPushGcmApiKey;
	@Value("${bm.push.gcm.application.id}")
	private String bmPushGcmApplicationId;
	
	// Push BM-APNS Config
	@Value("${bm.push.apns.service.id}")
	private String bmPushApnsServiceId;
	@Value("${bm.push.apns.service.passwd}")
	private String bmPushApnsServicePasswd;
	@Value("${bm.push.apns.application.id}")
	private String bmPushApnsApplicationId;
	
	// Push Single Config
	@Value("${push.single.servicekey.url}")
	private String pushSingleServiceKeyUrl;
	@Value("${push.single.servicekey.id}")
	private String pushSingleServiceKeyId;
	@Value("${push.single.servicekey.bm.id}")
	private String pushSingleServiceKeyBMId;
	@Value("${push.single.servicekey.auth.key}")
	private String pushSingleServicekeyAuthKey;
	@Value("${push.single.servicekey.auth.bm.key}")
	private String pushSingleServicekeyAuthBMKey;
	
	@Value("${push.single.token.url}")
	private String pushSingleDeviceTokenUrl;
	@Value("${push.single.devicetoken.id}")
	private String pushSingleDeviceTokenId;
	@Value("${push.single.devicetoken.bm.id}")
	private String pushSingleDeviceTokenBMId;
	@Value("${push.single.devicetoken.auth.key}")
	private String pushSingleDeviceTokenAuthKey;
	@Value("${push.single.devicetoken.auth.bm.key}")
	private String pushSingleDeviceTokenAuthBMKey;
	
	@Value("${push.proxy}")
	private String pushProxy;
	@Value("${push.subproxy}")
	private String pushSubProxy;
	@Value("${push.proxyport}")
	private int pushProxyPort;
	
	// Push NISSAN LEAF GCM Config
	@Value("${el1.push.gcm.service.id}")
	private String el1pushGcmServiceId;
	@Value("${el1.push.gcm.service.passwd}")
	private String el1pushGcmServicePasswd;
	@Value("${el1.push.gcm.application.id}")
	private String el1pushGcmApplicationId;
	
	// Push NISSAN LEAF APNS Config
	@Value("${el1.push.apns.service.id}")
	private String el1pushApnsServiceId;
	@Value("${el1.push.apns.service.passwd}")
	private String el1pushApnsServicePasswd;
	@Value("${el1.push.apns.application.id}")
	private String el1pushApnsApplicationId;	
	
	// Push Single Config
	@Value("${el1.push.single.servicekey.id}")
	private String el1pushSingleServiceKeyId;
	@Value("${el1.push.single.servicekey.auth.key}")
	private String el1pushSingleServicekeyAuthKey;	
	@Value("${el1.push.single.devicetoken.id}")
	private String el1pushSingleDeviceTokenId;
	@Value("${el1.push.single.devicetoken.auth.key}")
	private String el1pushSingleDeviceTokenAuthKey;	
	
	// Push Notice Config
//	@Value("${push.announce.servicekey.url}")
//	private String pushAnnounceServiceKeyUrl;
//	@Value("${push.announce.servicekey.id}")
//	private String pushAnnounceServiceKeyId;
//	@Value("${push.announce.servicekey.auth.key}")
//	private String pushAnnounceServicekeyAuthKey;

	@Value("${push.subServiceId}")
	private String subServiceId;

	
	
	public String getSvrId() {
		return svrId;
	}

	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	public int getVectorSize() {
		return vectorSize;
	}

	public void setVectorSize(int vectorSize) {
		this.vectorSize = vectorSize;
	}

	public int getThreadCnt() {
		return threadCnt;
	}

	public void setThreadCnt(int threadCnt) {
		this.threadCnt = threadCnt;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getPushDomain() {
		return pushDomain;
	}

	public void setPushDomain(String pushDomain) {
		this.pushDomain = pushDomain;
	}

	public String getPushPort() {
		return pushPort;
	}

	public void setPushPort(String pushPort) {
		this.pushPort = pushPort;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getPushGcmServiceId() {
		return pushGcmServiceId;
	}

	public void setPushGcmServiceId(String pushGcmServiceId) {
		this.pushGcmServiceId = pushGcmServiceId;
	}

	public String getPushGcmServicePasswd() {
		return pushGcmServicePasswd;
	}

	public void setPushGcmServicePasswd(String pushGcmServicePasswd) {
		this.pushGcmServicePasswd = pushGcmServicePasswd;
	}

	public String getPushGcmApiKey() {
		return pushGcmApiKey;
	}

	public void setPushGcmApiKey(String pushGcmApiKey) {
		this.pushGcmApiKey = pushGcmApiKey;
	}

	public String getPushGcmApplicationId() {
		return pushGcmApplicationId;
	}

	public void setPushGcmApplicationId(String pushGcmApplicationId) {
		this.pushGcmApplicationId = pushGcmApplicationId;
	}

	public String getPushApnsServiceId() {
		return pushApnsServiceId;
	}

	public void setPushApnsServiceId(String pushApnsServiceId) {
		this.pushApnsServiceId = pushApnsServiceId;
	}

	public String getPushApnsServicePasswd() {
		return pushApnsServicePasswd;
	}

	public void setPushApnsServicePasswd(String pushApnsServicePasswd) {
		this.pushApnsServicePasswd = pushApnsServicePasswd;
	}

	public String getPushApnsApplicationId() {
		return pushApnsApplicationId;
	}

	public void setPushApnsApplicationId(String pushApnsApplicationId) {
		this.pushApnsApplicationId = pushApnsApplicationId;
	}

	public String getPushSingleServiceKeyUrl() {
		return pushSingleServiceKeyUrl;
	}

	public void setPushSingleServiceKeyUrl(String pushSingleServiceKeyUrl) {
		this.pushSingleServiceKeyUrl = pushSingleServiceKeyUrl;
	}

	public String getPushSingleServiceKeyId() {
		return pushSingleServiceKeyId;
	}

	public void setPushSingleServiceKeyId(String pushSingleServiceKeyId) {
		this.pushSingleServiceKeyId = pushSingleServiceKeyId;
	}

	public String getPushSingleServicekeyAuthKey() {
		return pushSingleServicekeyAuthKey;
	}

	public void setPushSingleServicekeyAuthKey(String pushSingleServicekeyAuthKey) {
		this.pushSingleServicekeyAuthKey = pushSingleServicekeyAuthKey;
	}

	
//	public String getPushAnnounceServiceKeyUrl() {
//		return pushAnnounceServiceKeyUrl;
//	}
//
//	public void setPushAnnounceServiceKeyUrl(String pushAnnounceServiceKeyUrl) {
//		this.pushAnnounceServiceKeyUrl = pushAnnounceServiceKeyUrl;
//	}
//
//	public String getPushAnnounceServiceKeyId() {
//		return pushAnnounceServiceKeyId;
//	}
//
//	public void setPushAnnounceServiceKeyId(String pushAnnounceServiceKeyId) {
//		this.pushAnnounceServiceKeyId = pushAnnounceServiceKeyId;
//	}
//
//	public String getPushAnnounceServicekeyAuthKey() {
//		return pushAnnounceServicekeyAuthKey;
//	}
//
//	public void setPushAnnounceServicekeyAuthKey(String pushAnnounceServicekeyAuthKey) {
//		this.pushAnnounceServicekeyAuthKey = pushAnnounceServicekeyAuthKey;
//	}

	public String getPushSingleDeviceTokenUrl() {
		return pushSingleDeviceTokenUrl;
	}

	public void setPushSingleDeviceTokenUrl(String pushSingleDeviceTokenUrl) {
		this.pushSingleDeviceTokenUrl = pushSingleDeviceTokenUrl;
	}

	public String getPushSingleDeviceTokenId() {
		return pushSingleDeviceTokenId;
	}

	public void setPushSingleDeviceTokenId(String pushSingleDeviceTokenId) {
		this.pushSingleDeviceTokenId = pushSingleDeviceTokenId;
	}

	public String getPushSingleDeviceTokenAuthKey() {
		return pushSingleDeviceTokenAuthKey;
	}

	public void setPushSingleDeviceTokenAuthKey(String pushSingleDeviceTokenAuthKey) {
		this.pushSingleDeviceTokenAuthKey = pushSingleDeviceTokenAuthKey;
	}

	public String getSubServiceId() {
		return subServiceId;
	}

	public void setSubServiceId(String subServiceId) {
		this.subServiceId = subServiceId;
	}

	public String getBmPushGcmServiceId() {
		return bmPushGcmServiceId;
	}

	public void setBmPushGcmServiceId(String bmPushGcmServiceId) {
		this.bmPushGcmServiceId = bmPushGcmServiceId;
	}

	public String getBmPushGcmServicePasswd() {
		return bmPushGcmServicePasswd;
	}

	public void setBmPushGcmServicePasswd(String bmPushGcmServicePasswd) {
		this.bmPushGcmServicePasswd = bmPushGcmServicePasswd;
	}

	public String getBmPushGcmApiKey() {
		return bmPushGcmApiKey;
	}

	public void setBmPushGcmApiKey(String bmPushGcmApiKey) {
		this.bmPushGcmApiKey = bmPushGcmApiKey;
	}

	public String getBmPushGcmApplicationId() {
		return bmPushGcmApplicationId;
	}

	public void setBmPushGcmApplicationId(String bmPushGcmApplicationId) {
		this.bmPushGcmApplicationId = bmPushGcmApplicationId;
	}

	public String getBmPushApnsServiceId() {
		return bmPushApnsServiceId;
	}

	public void setBmPushApnsServiceId(String bmPushApnsServiceId) {
		this.bmPushApnsServiceId = bmPushApnsServiceId;
	}

	public String getBmPushApnsServicePasswd() {
		return bmPushApnsServicePasswd;
	}

	public void setBmPushApnsServicePasswd(String bmPushApnsServicePasswd) {
		this.bmPushApnsServicePasswd = bmPushApnsServicePasswd;
	}

	public String getBmPushApnsApplicationId() {
		return bmPushApnsApplicationId;
	}

	public void setBmPushApnsApplicationId(String bmPushApnsApplicationId) {
		this.bmPushApnsApplicationId = bmPushApnsApplicationId;
	}

	public String getPushProxy() {
		return pushProxy;
	}

	public void setPushProxy(String pushProxy) {
		this.pushProxy = pushProxy;
	}

	public String getPushSubProxy() {
		return pushSubProxy;
	}

	public void setPushSubProxy(String pushSubProxy) {
		this.pushSubProxy = pushSubProxy;
	}

	public int getPushProxyPort() {
		return pushProxyPort;
	}

	public void setPushProxyPort(int pushProxyPort) {
		this.pushProxyPort = pushProxyPort;
	}

	public String getEl1pushGcmServiceId() {
		return el1pushGcmServiceId;
	}

	public void setEl1pushGcmServiceId(String el1pushGcmServiceId) {
		this.el1pushGcmServiceId = el1pushGcmServiceId;
	}

	public String getEl1pushGcmServicePasswd() {
		return el1pushGcmServicePasswd;
	}

	public void setEl1pushGcmServicePasswd(String el1pushGcmServicePasswd) {
		this.el1pushGcmServicePasswd = el1pushGcmServicePasswd;
	}

	public String getEl1pushGcmApplicationId() {
		return el1pushGcmApplicationId;
	}

	public void setEl1pushGcmApplicationId(String el1pushGcmApplicationId) {
		this.el1pushGcmApplicationId = el1pushGcmApplicationId;
	}

	public String getEl1pushApnsServiceId() {
		return el1pushApnsServiceId;
	}

	public void setEl1pushApnsServiceId(String el1pushApnsServiceId) {
		this.el1pushApnsServiceId = el1pushApnsServiceId;
	}

	public String getEl1pushApnsServicePasswd() {
		return el1pushApnsServicePasswd;
	}

	public void setEl1pushApnsServicePasswd(String el1pushApnsServicePasswd) {
		this.el1pushApnsServicePasswd = el1pushApnsServicePasswd;
	}

	public String getEl1pushApnsApplicationId() {
		return el1pushApnsApplicationId;
	}

	public void setEl1pushApnsApplicationId(String el1pushApnsApplicationId) {
		this.el1pushApnsApplicationId = el1pushApnsApplicationId;
	}

	public String getEl1pushSingleServiceKeyId() {
		return el1pushSingleServiceKeyId;
	}

	public void setEl1pushSingleServiceKeyId(String el1pushSingleServiceKeyId) {
		this.el1pushSingleServiceKeyId = el1pushSingleServiceKeyId;
	}

	public String getEl1pushSingleServicekeyAuthKey() {
		return el1pushSingleServicekeyAuthKey;
	}

	public void setEl1pushSingleServicekeyAuthKey(String el1pushSingleServicekeyAuthKey) {
		this.el1pushSingleServicekeyAuthKey = el1pushSingleServicekeyAuthKey;
	}

	public String getEl1pushSingleDeviceTokenId() {
		return el1pushSingleDeviceTokenId;
	}

	public void setEl1pushSingleDeviceTokenId(String el1pushSingleDeviceTokenId) {
		this.el1pushSingleDeviceTokenId = el1pushSingleDeviceTokenId;
	}

	public String getEl1pushSingleDeviceTokenAuthKey() {
		return el1pushSingleDeviceTokenAuthKey;
	}

	public void setEl1pushSingleDeviceTokenAuthKey(String el1pushSingleDeviceTokenAuthKey) {
		this.el1pushSingleDeviceTokenAuthKey = el1pushSingleDeviceTokenAuthKey;
	}

	public String getPushSingleDeviceTokenBMId() {
		return pushSingleDeviceTokenBMId;
	}

	public void setPushSingleDeviceTokenBMId(String pushSingleDeviceTokenBMId) {
		this.pushSingleDeviceTokenBMId = pushSingleDeviceTokenBMId;
	}

	public String getPushSingleDeviceTokenAuthBMKey() {
		return pushSingleDeviceTokenAuthBMKey;
	}

	public void setPushSingleDeviceTokenAuthBMKey(String pushSingleDeviceTokenAuthBMKey) {
		this.pushSingleDeviceTokenAuthBMKey = pushSingleDeviceTokenAuthBMKey;
	}

	public String getPushSingleServiceKeyBMId() {
		return pushSingleServiceKeyBMId;
	}

	public void setPushSingleServiceKeyBMId(String pushSingleServiceKeyBMId) {
		this.pushSingleServiceKeyBMId = pushSingleServiceKeyBMId;
	}

	public String getPushSingleServicekeyAuthBMKey() {
		return pushSingleServicekeyAuthBMKey;
	}

	public void setPushSingleServicekeyAuthBMKey(String pushSingleServicekeyAuthBMKey) {
		this.pushSingleServicekeyAuthBMKey = pushSingleServicekeyAuthBMKey;
	}
	
	
	
}
