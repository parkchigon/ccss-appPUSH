package com.lgu.ccss.apppush.service.push;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgu.ccss.apppush.config.PushConf;
import com.lgu.ccss.apppush.constant.AppPushStatusConst;
import com.lgu.ccss.apppush.mapper.AppPushDao;
import com.lgu.ccss.apppush.model.AppPushVO;
import com.lgu.ccss.apppush.service.worker.AppPushWorkerThread;
import com.lgu.ccss.common.util.HttpRequest;
import com.lgu.common.tlo.TloWriter;

@Service
public class AppPushServiceImpl implements AppPushService{
	private final Logger logger = LoggerFactory.getLogger(AppPushServiceImpl.class);
	
	@Autowired
	PushConf pushConf;
	@Autowired
	AppPushDao appPushDao;
	@Autowired
	HttpRequest httpRequest;
	@Autowired
	private TloWriter tloWriter;
	
	int vectorSize = 1000;
	public static Vector<AppPushVO> msgVector		= null;  // 벡터 크기
	public static AppPushWorkerThread workerThreadArr = null;
	Thread appPushThreadGrp[] = null;
	
	@Override
	public void doTask() throws Exception {
		logger.info("AppPushService doTask!");
		
		if(msgVector == null) {
			logger.info("msgVector create : "+vectorSize);
			msgVector= new Vector<AppPushVO>();
			msgVector.setSize(vectorSize);
			msgVector.clear();
		}
		
		if( workerThreadArr == null ){
			makeWorkerThread();
		}
		logger.info("getMessage!");
		getMessage();
	}
	

	public void makeWorkerThread() {
		int threadCnt = pushConf.getThreadCnt();
		appPushThreadGrp = new Thread[threadCnt];
		for(int i=0; i< threadCnt;i++) {
			workerThreadArr = new AppPushWorkerThread(appPushDao,pushConf,httpRequest,tloWriter);
			appPushThreadGrp[i] = new Thread(workerThreadArr);
			appPushThreadGrp[i].setName("CarPush ["+i+"] Thread");
			appPushThreadGrp[i].start();
		}
	}
	
	public void getMessage() {
		//현재 남아 있는 메시지 큐의 크기가 쓰레드 개수 보다 작을 때만 큐 가지고 옴.
		int threadCnt = pushConf.getThreadCnt();
		synchronized(msgVector){
			if(msgVector.size() >= threadCnt){
				logger.debug("return ["+msgVector.size()+"]:["+threadCnt+"]");
				return;
			}
		}
		List<AppPushVO> targetList = appPushDao.getTargetList(pushConf.getRowCount());
		if( targetList == null || targetList.size() == 0) {
			return;
		}	
		logger.info("getMessage size: "+targetList.size());
		for( int index = 0; index < targetList.size(); index++ ){
			AppPushVO appPushVo = targetList.get(index);
			appPushVo.setSendTryCnt(0);
			if(appPushDao.appPushUpdateStatus(appPushVo, AppPushStatusConst.PUSH_STATUS_SEND_MSG_ING)) {
				msgVector.add(targetList.get(index));
			}
		}
	}
}
