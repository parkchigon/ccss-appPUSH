package com.lgu.ccss.apppush.mapper;


import com.lgu.ccss.apppush.model.AppPushVO;
import com.lgu.ccss.config.annontation.Master;

@Master
public interface AppPushMapperOracle {
	int insertAppPushSendHistory(AppPushVO carPushVO);
	// 닛산 LEAF 접속모델 조회 20190128 kangjin
	String getConnDeviceModel(String mgrAppLoginId);
}
