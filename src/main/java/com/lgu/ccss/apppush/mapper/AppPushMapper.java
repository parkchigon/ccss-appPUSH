package com.lgu.ccss.apppush.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.apppush.model.AppPushVO;


@Component
public class AppPushMapper {
	
	@Autowired
	AppPushMapperOracle appPushMapperOracle;
	
	@Autowired
	AppPushMapperAltibase appPushMapperAltibase;

	public List<AppPushVO> selectAppPushTargetList(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperAltibase.selectAppPushTargetList(appPushVo);
	}

	public int updateAppPushInitStatus(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperAltibase.updateAppPushInitStatus(appPushVo);
	}

	public int updateAppPushTargetStatus(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperAltibase.updateAppPushTargetStatus(appPushVo);
	}
	
	public int updateAppPushTargetList(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperAltibase.updateAppPushTargetList(appPushVo);
	}

	public int insertAppPushSendHistory(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperOracle.insertAppPushSendHistory(appPushVo);
	}

	public int deleteAppPushTarget(AppPushVO appPushVo) {
		// TODO Auto-generated method stub
		return appPushMapperAltibase.deleteAppPushTarget(appPushVo);
	}
	// 닛산 LEAF 접속모델 조회 20190128 kangjin
	public String getConnDeviceModel(String mgrAppLoginId) {
		// TODO Auto-generated method stub
		return appPushMapperOracle.getConnDeviceModel(mgrAppLoginId);
	}	
	
}
