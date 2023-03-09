package com.lgu.ccss.apppush.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.apppush.config.PushConf;
import com.lgu.ccss.apppush.constant.AppPushConst;
import com.lgu.ccss.apppush.constant.AppPushStatusConst;
import com.lgu.ccss.apppush.model.AppPushVO;

@Component
public class AppPushDao {
	private final Logger logger = LoggerFactory.getLogger(AppPushDao.class);
	
	@Autowired
	PushConf pushConf;
	@Autowired
	private AppPushMapper appPushMapper;
	
	public List<AppPushVO> getTargetList(int selectSize) {
		// 예약전송시간 이전 메시지 + 대기상태  메시지를 조회
		
		AppPushVO appPushVo = new AppPushVO();
		Date nowDt = new Date();
		appPushVo.setSendDt(new SimpleDateFormat(AppPushConst.DATE_FORMAT_YYYYMMDDHHMMSS).format(nowDt));
		appPushVo.setMsgStatus(AppPushStatusConst.PUSH_STATUS_SEND_MSG_READY);
		appPushVo.setSvrId(pushConf.getSvrId());
		appPushVo.setRowCount(selectSize);
		logger.debug("### select Data : "+appPushVo.toString());
		List<AppPushVO> targetList = appPushMapper.selectAppPushTargetList(appPushVo);

		return targetList;
	}
	public boolean updateAppPushInitStatus(AppPushVO appPushVo,String status) {
		appPushVo.setMsgStatus(status);
		appPushVo.setUpdId(pushConf.getSystemId());
		logger.debug("### update Data : "+appPushVo.toString());
		
		int resultStatus = appPushMapper.updateAppPushInitStatus(appPushVo);
		logger.debug("ResultStatus : "+resultStatus);
		
		
		return true;
	}
	public boolean appPushUpdateStatus(AppPushVO appPushVo,String status) {
		appPushVo.setMsgStatus(status);
		appPushVo.setUpdId(pushConf.getSystemId());
		logger.debug("### update Data : "+appPushVo.toString());
		int resultStatus = appPushMapper.updateAppPushTargetStatus(appPushVo);
		logger.debug("rseult : "+resultStatus);
		if(resultStatus<1) {
			// 실패시 처리
			logger.debug("Status update Fail");
			return false;
		}
		return true;
	}
	public void appPushProcessFinish(AppPushVO carPushVo,String code) {
		logger.debug("############ AppPushVO : "+carPushVo.toString());
		carPushVo.setMsgStatus(code);
		carPushVo.setUpdId(pushConf.getSystemId());
		carPushVo.setSendDt(new SimpleDateFormat(AppPushConst.DATE_FORMAT_YYYYMMDDHHMMSS).format(new Date()));
		logger.debug("####### insertAppPushSendHistory");
		int result = appPushMapper.insertAppPushSendHistory(carPushVo); // 성공 이력 저장
		logger.debug("insertAppPushSendHistory Result : "+result);
		logger.debug("####### deleteAppPushTarget");
		appPushMapper.deleteAppPushTarget(carPushVo);
	}
	
	// 닛산 LEAF 접속모델 조회 20190128 kangjin
	public String getConnDeviceModel(String recvPhoneNo) {
		logger.debug("############ recvPhoneNo : "+recvPhoneNo);
		String connDeviceId = appPushMapper.getConnDeviceModel(recvPhoneNo);
		logger.debug("getConnDeviceModel connDeviceId : "+connDeviceId);
		return connDeviceId;
	}	
}
