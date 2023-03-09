package com.lgu.ccss.apppush.service.worker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lgu.ccss.apppush.config.PushConf;
import com.lgu.ccss.apppush.constant.AppPushConst;
import com.lgu.ccss.apppush.constant.AppPushDataMapConst;
import com.lgu.ccss.apppush.constant.AppPushStatusConst;
import com.lgu.ccss.apppush.mapper.AppPushDao;
import com.lgu.ccss.apppush.model.AppPushVO;
import com.lgu.ccss.apppush.model.PushVO;
import com.lgu.ccss.apppush.service.push.AppPushServiceImpl;
import com.lgu.ccss.common.exception.PushException;
import com.lgu.ccss.common.tlo.TloConst;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;

import com.lgu.ccss.common.util.HttpRequest;
import com.lgu.common.tlo.TloWriter;
//import com.lgu.common.tlo.TloWriter;
import com.lgu.common.util.JsonUtil;



public class AppPushWorkerThread implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(AppPushWorkerThread.class);
	
	HttpRequest httpRequest;
	AppPushDao appPushDao;
	PushConf pushConf;
	TloWriter tloWriter;
	
	private String apnsServiceId;
	private String apnsServicePasswd;
	private String apnsApplicationId;
	
	private String gcmServiceId;
	private String gcmServicePasswd;
	private String gcmApplicationId;
	
	private String singleDeviceTokenId;
	private String singleDeviceTokenAuthKey;
	
	private String singleServiceKeyId;
	private String singleServicekeyAuthKey;		
	
	public AppPushWorkerThread(AppPushDao appPushDao,PushConf pushConf,HttpRequest httpRequest,TloWriter tloWriter) {
		this.httpRequest = httpRequest;
		this.appPushDao = appPushDao;
		this.pushConf = pushConf;
		this.tloWriter=tloWriter;
	}
	@Override
	public void run() {
		while(true) {
			
			try {
				AppPushVO pushMsgVo = null;
				synchronized(AppPushServiceImpl.msgVector){
					if(AppPushServiceImpl.msgVector.size() > 0){
						pushMsgVo=(AppPushVO)AppPushServiceImpl.msgVector.remove(0);
					}
				}
				
				if(pushMsgVo==null){
					Thread.sleep(300);
					continue;
				}
				
				logger.info("CALL run ======================> RUN START");
				
				processMessage(pushMsgVo);				
				
				logger.info("CALL run ======================> RUN FINISH");
			} catch (InterruptedException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void processMessage(AppPushVO pushMsgVo) throws Exception {
		logger.info("processMessage pushMsgVo({})"+pushMsgVo);
		
		// 닛산 LEAF 접속모델 조회 20190128 kangjin
		String connDeviceId = appPushDao.getConnDeviceModel(pushMsgVo.getRecvPhoneNo());
		
		// 닛산 LEAF 분기로직 20190128 kangjin
		if("EL1".equals(connDeviceId)) {
			
			apnsServiceId = pushConf.getEl1pushApnsServiceId();
			apnsServicePasswd = pushConf.getEl1pushApnsServicePasswd();
			apnsApplicationId = pushConf.getEl1pushApnsApplicationId();
			
			gcmServiceId = pushConf.getEl1pushGcmServiceId();
			gcmServicePasswd = pushConf.getEl1pushGcmServicePasswd();
			gcmApplicationId = pushConf.getEl1pushGcmApplicationId();
			
			singleDeviceTokenId = pushConf.getEl1pushSingleDeviceTokenId();
			singleDeviceTokenAuthKey = pushConf.getEl1pushSingleDeviceTokenAuthKey();
			
			singleServiceKeyId = pushConf.getEl1pushSingleServiceKeyId();
			singleServicekeyAuthKey = pushConf.getEl1pushSingleServicekeyAuthKey();				
			
		}else if("SYMC_C300C".equals(connDeviceId)){
			
			apnsServiceId = pushConf.getPushApnsServiceId();
			apnsServicePasswd = pushConf.getPushApnsServicePasswd();
			apnsApplicationId = pushConf.getPushApnsApplicationId();
			
			gcmServiceId = pushConf.getPushGcmServiceId();
			gcmServicePasswd = pushConf.getPushGcmServicePasswd();
			gcmApplicationId = pushConf.getPushGcmApplicationId();
			
			singleDeviceTokenId = pushConf.getPushSingleDeviceTokenBMId();
			singleDeviceTokenAuthKey = pushConf.getPushSingleDeviceTokenAuthBMKey();
			
			singleServiceKeyId = pushConf.getPushSingleServiceKeyBMId();
			singleServicekeyAuthKey = pushConf.getPushSingleServicekeyAuthBMKey();			
			
		}else {
			apnsServiceId = pushConf.getPushApnsServiceId();
			apnsServicePasswd = pushConf.getPushApnsServicePasswd();
			apnsApplicationId = pushConf.getPushApnsApplicationId();
			
			gcmServiceId = pushConf.getPushGcmServiceId();
			gcmServicePasswd = pushConf.getPushGcmServicePasswd();
			gcmApplicationId = pushConf.getPushGcmApplicationId();
			
			singleDeviceTokenId = pushConf.getPushSingleDeviceTokenId();
			singleDeviceTokenAuthKey = pushConf.getPushSingleDeviceTokenAuthKey();
			
			singleServiceKeyId = pushConf.getPushSingleServiceKeyId();
			singleServicekeyAuthKey = pushConf.getPushSingleServicekeyAuthKey();	
		}
		
		setTloData(pushMsgVo);
		// 전송 카운트 맵으로 저장
		String resultStatus = AppPushStatusConst.PUSH_STATUS_SEND_MSG_FAIL;
		PushVO pushVo = setPushDataByOSInfo(pushMsgVo);
		if(pushVo == null) {
			appPushDao.appPushProcessFinish(pushMsgVo, resultStatus);
			setTloData(pushMsgVo,resultStatus);
			return;
		}
		
		int snedCnt = pushMsgVo.getSendTryCnt();
		pushMsgVo.setSendTryCnt(snedCnt+1);
		if(sendPushMsg(pushVo)) {
			resultStatus = AppPushStatusConst.PUSH_STATUS_SEND_MSG_SUCCESS;
		}else {
			if(snedCnt<3) {
				logger.error("Send Push Failed.... Add to Vector for retry");
				AppPushServiceImpl.msgVector.add(pushMsgVo);
				return;
			}
		}
		
		setTloData(pushMsgVo,resultStatus);
		appPushDao.appPushProcessFinish(pushMsgVo, resultStatus);
	}
	public PushVO setPushDataByOSInfo(AppPushVO pushMsgVo) throws Exception {
		
		PushVO pushVO = null;
		if(AppPushConst.APP_OS_INFO_ANDROID.equals(pushMsgVo.getDeviceType())) {
			// Android
			String serviceType = getServiceType(pushMsgVo.getCarOem());
			
			if(AppPushConst.APP_SERVICE_TYPE_AM.equals(serviceType)){
				pushVO = new PushVO(gcmApplicationId, gcmServiceId, gcmServicePasswd);
			} else if(AppPushConst.APP_SERVICE_TYPE_BM.equals(serviceType)){
				pushVO = new PushVO(pushConf.getBmPushGcmApplicationId(), pushConf.getBmPushGcmServiceId(), pushConf.getBmPushGcmServicePasswd());
			} else {
				logger.error("### Android Service Type Error ###");
				logger.error("### ERROR Push Seq : " + pushMsgVo.getCarOem());
				// 지원하지 않는 서비스 정보
				return null;
			}
		}else if(AppPushConst.APP_OS_INFO_IPHONE.equals(pushMsgVo.getDeviceType())){
			// Iphone
			String serviceType = getServiceType(pushMsgVo.getCarOem());
			if(AppPushConst.APP_SERVICE_TYPE_AM.equals(serviceType)){
				pushVO = new PushVO(apnsApplicationId, apnsServiceId, apnsServicePasswd);				
			}else if(AppPushConst.APP_SERVICE_TYPE_BM.equals(serviceType)){
				pushVO = new PushVO(pushConf.getBmPushApnsApplicationId(), pushConf.getBmPushApnsServiceId(), pushConf.getBmPushApnsServicePasswd());
			}else {
				logger.error("### Iphone Service Type Error ###");
				logger.error("### ERROR Push Seq : " + pushMsgVo.getCarOem());
				// 지원하지 않는 서비스 정보
				return null;
			}
		}else {
			logger.error("### Device Type Error ###");
			logger.error("### ERROR Push Seq : " + pushMsgVo.getDeviceType());
			// 지원하지 않는 디바이스 단말정보 업데이트
			return null;
		}
		pushVO.setRequestPart(pushMsgVo.getReqPart());
		pushVO.setServiceKey(pushMsgVo.getRecvPhoneNo());
		pushVO.setSubServiceId(createPushId());
		pushVO.setPayload(pushMsgVo.getMsgCont());
		return pushVO;
	}
	
	private String getServiceType(String carOem) {
		if(carOem==null)
			return null;
		if(carOem.equals("thinkware") || carOem.equals("NS")) // 닛산 LEAF 분기로직 20190128 kangjin
			return AppPushConst.APP_SERVICE_TYPE_AM;
		else if(carOem.equals("SY"))
			return AppPushConst.APP_SERVICE_TYPE_BM;
		else
			return null;
	}
	
	public boolean sendPushMsg(PushVO pushData) throws PushException, Exception{
		String singleTokenResult = singleToken(pushData);
		JsonObject result = jsonResultParse(singleTokenResult);
		JsonObject resultError = (JsonObject)result.get(AppPushConst.RESULT_MESSAGE_ERROR);
		if(result != null && !(AppPushConst.RESULT_MESSAGE_200.equals(resultError.get(AppPushConst.RESULT_MESSAGE_CODE).getAsString()))) {
			logger.error("### Push Message 발송 실패 ### ");
			return false;
		}
		if("200".equals(result.get(AppPushConst.RESULT_MESSAGE_RESULT_CODE).getAsString())) {
			logger.info("### Push Message 발송 완료 ###");
			return true;
		}
		return false; 

	}
	public static void setTloData(AppPushVO appPushVo) {
		String msgId = appPushVo.getMsgId();
		if(AppPushDataMapConst.tloMap.containsKey(msgId)) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, TloConst.LOG_TYPE_SVC);
		tlo.put(TloData.SID, appPushVo.getOrgNo());
		tlo.put(TloData.REQ_TIME, appPushVo.getRegDt());
		tlo.put(TloData.CARRIER_TYPE, TloConst.CARRIER_TYPE_ETC);
		tlo.put(TloData.PUSH_SVC_CLASS, TloConst.MP01);
		tlo.put(TloData.PUSH_REQ_TIME, TloData.getNowDate());
		AppPushDataMapConst.tloMap.put(appPushVo.getMsgId(), tlo);	
	}
	private void setTloData(AppPushVO pushData,String resultCode) {
		
		if (pushData == null) {
			logger.debug("############# PUSH DATA VO NUll !!!!");
			return;
		}
		Map<String, String> tlo = (Map<String, String>) AppPushDataMapConst.tloMap.get(pushData.getMsgId());
		String statusCode;
		if(resultCode.equals(AppPushStatusConst.PUSH_STATUS_SEND_MSG_SUCCESS)) {
			statusCode = AppPushStatusConst.PUSH_RESULT_CODE_SEND_SUCCESS;
		}else {
			statusCode = AppPushStatusConst.PUSH_RESULT_CODE_SEND_FAIL;
		}
		tlo.put(TloData.PUSH_RES_TIME, TloData.getNowDate());
		tlo.put(TloData.RESULT_CODE, statusCode);
		tlo.put(TloData.RSP_TIME, TloData.getNowDate());
		tlo.put(TloData.PUSH_RESULT_CODE, resultCode);
		
		logger.debug("tlo : "+AppPushDataMapConst.tloMap.get(pushData.getMsgId())+"tlocheck : "+AppPushDataMapConst.tloMap.containsKey(pushData.getMsgId())+"TloData.getNowDate() : "+TloData.getNowDate() + "| statusCode : "+statusCode + " | TloData.getNowDate() : "+TloData.getNowDate() + " | resultCode : "+resultCode );
		
		TloUtil.setTloData(tlo);
		tloWriter.write(tlo);
		
		AppPushDataMapConst.tloMap.remove(pushData.getMsgId());
		logger.debug("############# TLO WRITE  DONE !!!!");
	}
	
	public String singleServicekey(PushVO pushVO) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AppPushConst.PUSH_KEY_REQUEST_PART, pushVO.getRequestPart());
		params.put(AppPushConst.PUSH_KEY_REQUEST_TIME, new SimpleDateFormat(AppPushConst.DATE_FORMAT_YYYYMMDDHHMMSS).format(new Date()));
		params.put(AppPushConst.PUSH_KEY_PUSH_ID, createPushId());
		params.put(AppPushConst.PUSH_KEY_SERVICE_ID, pushVO.getServiceId());
		params.put(AppPushConst.PUSH_KEY_SERVICE_PASS, pushVO.getServicePass());
		params.put(AppPushConst.PUSH_KEY_APPLICATION_ID, pushVO.getApplicationId());
		
		// AM/BM 타입별 서비스키값
		params.put(AppPushConst.PUSH_KEY_SERVICE_KEY, pushVO.getServiceKey());
		params.put(AppPushConst.PUSH_KEY_SUB_SERVICE_ID, pushVO.getSubServiceId());
		
		JSONObject jsonPayload = new JSONObject();
		JSONParser parser = new JSONParser();
		logger.info("payload : "+ pushVO.getPayload());
		jsonPayload = (JSONObject) parser.parse(pushVO.getPayload());
		params.put(AppPushConst.PUSH_KEY_PAYLOAD, jsonPayload);
		
		logger.debug("####################### Push Message Post!! ##########################");
		String result = httpRequest.post(
				pushConf.getPushDomain()+AppPushConst.SIGN_COLON+pushConf.getPushPort()
				+ pushConf.getPushSingleServiceKeyUrl(),
				JsonUtil.marshallingJson(params), singleServiceKeyId,
				singleServicekeyAuthKey);
		logger.debug("####################### Message 발송 ##########################");
		return result;
	}
	
	public String singleToken(PushVO pushVO) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AppPushConst.PUSH_KEY_REQUEST_PART, pushVO.getRequestPart());
		params.put(AppPushConst.PUSH_KEY_REQUEST_TIME, new SimpleDateFormat(AppPushConst.DATE_FORMAT_YYYYMMDDHHMMSS).format(new Date()));
		params.put(AppPushConst.PUSH_KEY_PUSH_ID, createPushId());
		params.put(AppPushConst.PUSH_KEY_SERVICE_ID, pushVO.getServiceId());
		params.put(AppPushConst.PUSH_KEY_SERVICE_PASS, pushVO.getServicePass());
		params.put(AppPushConst.PUSH_KEY_APPLICATION_ID, pushVO.getApplicationId());
		
		// AM/BM 타입별 서비스키값
		params.put(AppPushConst.PUSH_KEY_DEVICE_TOKEN, pushVO.getServiceKey());
		params.put(AppPushConst.PUSH_KEY_SUB_SERVICE_ID, pushVO.getSubServiceId());
		
		JSONObject jsonPayload = new JSONObject();
		JSONParser parser = new JSONParser();
		logger.info("payload : "+ pushVO.getPayload());
		jsonPayload = (JSONObject) parser.parse(pushVO.getPayload());
		params.put(AppPushConst.PUSH_KEY_PAYLOAD, jsonPayload);
		
		logger.info("####################### Send Push Message Start ##########################");
		String result = httpRequest.postProxy(
				pushConf.getPushDomain()+AppPushConst.SIGN_COLON+pushConf.getPushPort()
				+ pushConf.getPushSingleDeviceTokenUrl(),
				JsonUtil.marshallingJson(params), singleDeviceTokenId,
				singleDeviceTokenAuthKey, pushConf.getPushProxy(), pushConf.getPushSubProxy(),pushConf.getPushProxyPort());
		logger.info("####################### Send Push Message End ##########################");
		return result;
	}
	
	public String createPushId() throws Exception {
		String toDay = new SimpleDateFormat(AppPushConst.DATE_FORMAT_YYYYMMDD).format(new Date());
		Random rd = new Random();
		int max, min;
		max = 9999; min = 1000;
		String ramdomNumber = String.valueOf(rd.nextInt(max - min +1) + min);
		
		StringBuilder sb = new StringBuilder(toDay);
		return sb.append(ramdomNumber).toString();
	}
	public JsonObject jsonResultParse(String resultJson) throws PushException {
		
		try {
			JsonObject jsonObject = new JsonParser().parse(resultJson).getAsJsonObject();
			return jsonObject;
		} catch (Exception e) {
			throw new PushException(resultJson);
		}
	}	
}
