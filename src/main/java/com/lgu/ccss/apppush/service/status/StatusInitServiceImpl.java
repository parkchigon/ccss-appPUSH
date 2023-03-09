package com.lgu.ccss.apppush.service.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgu.ccss.apppush.constant.AppPushStatusConst;
import com.lgu.ccss.apppush.mapper.AppPushDao;
import com.lgu.ccss.apppush.model.AppPushVO;

@Service
public class StatusInitServiceImpl implements StatusInitService{
	private final Logger logger = LoggerFactory.getLogger(StatusInitServiceImpl.class);
	
	@Autowired
	AppPushDao appPushDao;
	
	@Override
	public boolean doTask() {
		// TODO Auto-generated method stub
		AppPushVO appPushVo = new AppPushVO();
		logger.debug("########################################################");
		logger.debug("#                  UPDATE INIT STATUS                  #");
		logger.debug("########################################################");
		return appPushDao.updateAppPushInitStatus(appPushVo, AppPushStatusConst.PUSH_STATUS_SEND_MSG_READY);
	}
}
