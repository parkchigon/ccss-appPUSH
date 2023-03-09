package com.lgu.ccss.apppush.mapper;

import java.util.List;

import com.lgu.ccss.apppush.model.AppPushVO;
import com.lgu.ccss.config.annontation.Slave;

@Slave
public interface AppPushMapperAltibase {
	List<AppPushVO> selectAppPushTargetList(AppPushVO carPushVO);
	AppPushVO selectTargetMsg(AppPushVO carPushVO);
	int updateAppPushInitStatus(AppPushVO carPushVO);
	int updateAppPushTargetStatus(AppPushVO carPushVO);
	int updateAppPushTargetList(AppPushVO carPushVO);
	int deleteAppPushTarget(AppPushVO carPushVO);
	
}
