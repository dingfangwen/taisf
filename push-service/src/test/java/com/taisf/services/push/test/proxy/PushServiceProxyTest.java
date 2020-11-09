package com.taisf.services.push.test.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.dto.PushMessage;
import com.taisf.services.device.entity.DeviceEntity;
import com.taisf.services.device.proxy.PushServiceProxy;
import com.taisf.services.push.request.CellApplyRequest;
import com.taisf.services.push.request.CellCloseRequest;
import com.taisf.services.push.request.MoneySendRequest;
import com.taisf.services.push.test.jiguang.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class PushServiceProxyTest extends BaseTest {

	@Autowired
	private PushServiceProxy pushService;




	@Test
	public void sendApplyCellSuccessTest() {

		CellApplyRequest moneySendRequest = new CellApplyRequest();

		moneySendRequest.setUserId("2c91340c61088e880161184900c5007a");
		moneySendRequest.setAddress("浙江省绍兴市越城区沿海赛洛城317号楼五单元 楼下");
		moneySendRequest.setBuffetFid("CD-200131-0001");
		moneySendRequest.setCellNum("9");

		DataTransferObject<Void> voidDataTransferObject = pushService.sendApplyCellSuccess(moneySendRequest);
		System.out.println(voidDataTransferObject.toJsonString());
	}


	@Test
	public void sendCloseCellSuccessTest() {

		CellCloseRequest moneySendRequest = new CellCloseRequest();

		moneySendRequest.setUserId("2c91340c61088e880161089f5563000a");
		DataTransferObject<Void> voidDataTransferObject = pushService.sendCloseCellSuccess(moneySendRequest);
		System.out.println(voidDataTransferObject.toJsonString());
	}


	@Test
	public void sendMoneySuccessTest() {

		MoneySendRequest moneySendRequest = new MoneySendRequest();
		moneySendRequest.setMoney("money");
		moneySendRequest.setName("name");
		moneySendRequest.setPhone("phone");
		moneySendRequest.setUserId("baozi");
		DataTransferObject<Void> voidDataTransferObject = pushService.sendMoneySuccess(moneySendRequest);
		System.out.println(voidDataTransferObject.toJsonString());
	}


	@Test
	public void testregistDevice() {


		DeviceEntity entity = new DeviceEntity();
		entity.setRegId("regId=");
		entity.setDeviceToken("2c91cb36634f8ba501635c90c723000c&");
		entity.setPushType(4);
		entity.setDeviceType("huawei");
		entity.setUserId("2c91340c5ffd23350160019b29cc000f");
		System.out.println("===============================" + JsonEntityTransform.Object2Json(pushService.registDevice(entity)));
	}

	@Test
	public void testlogoutDevice() {
		DeviceEntity entity = new DeviceEntity();
		entity.setRegId("tetete");
		entity.setUserId("3445");
		System.out.println("===============================" + JsonEntityTransform.Object2Json(pushService.logoutDevice(entity)));
	}

	@Test
	public void testPushMessage() {
		List<String> userIds = new ArrayList<String>();
		userIds.add("12344454");
		PushMessage message = new PushMessage();
		message.setTitle("测试标题");
		message.setContent("测试内容");
		System.out.println("===============================" + JsonEntityTransform.Object2Json(pushService.pushMessage(userIds,message)));
	}

	@Test
	public void testPushAllMessage() {
		PushMessage message = new PushMessage();
		message.setTitle("测试标题");
		message.setContent("测试内容");
		//System.out.println("===============================" + JsonEntityTransform.Object2Json(pushService.pushAllMessage(message)));
	}
	
} 
