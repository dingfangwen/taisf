package com.taisf.services.order.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.thread.pool.SendThreadPool;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.buffet.api.BuffetService;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.device.api.PushService;
import com.taisf.services.device.common.BaseContext;
import com.taisf.services.device.common.valenum.CellUserStatus;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.vo.DeviceCellCDVO;
import com.taisf.services.message.api.MessageInfoService;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.dto.BuffetInOrderRequest;
import com.taisf.services.order.dto.BuffetOutOrderRequest;
import com.taisf.services.order.dto.FinishOrderRequest;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.push.request.CellApplyRequest;
import com.taisf.services.push.request.CellCloseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Stack;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/8.
 * @version 1.0
 * @since 1.0
 */
@Component("order.orderOpServiceProxy")
public class OrderOpServiceProxy extends BaseContext implements OrderOpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceProxy.class);

    @Resource(name = "order.orderManagerImpl")
    private OrderManagerImpl orderManager;

    @Autowired
    private BuffetService buffetService;


    @Resource(name = "push.pushServiceProxy")
    private PushService pushServiceProxy;

    @Resource(name = "basedata.messageInfoServiceProxy")
    private MessageInfoService messageInfoService;

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();



        stack.push(1);
        stack.push(2);
        stack.push(3);

        while(!stack.empty()){
            System.out.println(stack.pop());

        }

    }

    /**
     * 根据订单号重置格子
     * @param dto
     * @param deviceId
     * @param orderSn
     * @return
     */
    private void openCellByOrderSn(DataTransferObject dto,String configCode,String deviceId, String orderSn) {
        if (!dto.checkSuccess()) {
            return ;
        }
        if (Check.NuNStr(configCode)  || Check.NuNStr(deviceId) || Check.NuNStr(orderSn)){
            dto.setErrorMsg("参数异常");
            return ;
        }
        //调用三方获取
        DataTransferObject linkOpenCellResponse = getLinkService(configCode).openCellByOrderSnRelease(deviceId, orderSn);
        if (!linkOpenCellResponse.checkSuccess()){
            dto.setErrorMsg(linkOpenCellResponse.getMsg());
            return ;
        }
    }



//    /**
//     * 根据订单号重置格子
//     * @param dto
//     * @param deviceId
//     * @param orderSn
//     * @return
//     */
//    private void resetCellByOrderSn(DataTransferObject dto,String configCode,String deviceId, String orderSn) {
//        if (!dto.checkSuccess()) {
//            return ;
//        }
//        if (Check.NuNStr(configCode) || Check.NuNStr(deviceId) || Check.NuNStr(orderSn)){
//            dto.setErrorMsg("参数异常");
//            return ;
//        }
//        //调用三方获取
//        LinkResetCellResponse linkResetCellResponse = getLinkService(configCode).resetCellByOrderSn(deviceId, orderSn);
//        if (!linkResetCellResponse.getSuccess()){
//            dto.setErrorMsg(linkResetCellResponse.getMsg());
//            return ;
//        }
//    }



    /**
     * 根据订单号 申请餐格
     * @param dto
     * @param deviceId
     * @param orderSn
     * @return
     */
    private String applyCellByOrderSn(DataTransferObject dto,String configCode,String deviceFid,String deviceId, String orderSn) {
        String cell = null;
        if (!dto.checkSuccess()) {
            return cell;
        }
        if (Check.NuNStr(deviceId) || Check.NuNStr(orderSn)){
            dto.setErrorMsg("参数异常");
            return cell;
        }
        //调用三方获取
        DataTransferObject<String> cellDto = getLinkService(configCode).applyCellByOrderSn(deviceFid,deviceId, orderSn);
        if (!cellDto.checkSuccess()){
            dto.setErrorMsg(cellDto.getMsg());
            return cell;
        }else {
            cell = cellDto.getData();
        }
        return cell;
    }





    /**
     * 获取当前的机柜信息
     * @param dto
     * @param buffetInfoRequest
     * @return
     */
    private BuffetEntity getBuffetLocal(DataTransferObject dto,BuffetInfoRequest buffetInfoRequest){
        BuffetEntity buffetEntity = null;
        if (!dto.checkSuccess()){
            return buffetEntity;
        }

        DataTransferObject<BuffetEntity> buffetInfoDto = buffetService.getBuffetLocal(buffetInfoRequest);
        if (!buffetInfoDto.checkSuccess()){
            dto.setErrorMsg(buffetInfoDto.getMsg());
            return buffetEntity;
        }
        buffetEntity = buffetInfoDto.getData();
        if (Check.NuNObj(buffetEntity)){
            dto.setErrorMsg("改餐柜不存在");
        }
        // 获取当前设备的状态
        DeviceStatusShowEnum deviceStatusShowEnum = getLinkService(buffetEntity.getConfigCode()).getDeviceStatusByDeviceId(buffetEntity.getDeviceId());
        if (DeviceStatusShowEnum.ON_LINE.getCode() != deviceStatusShowEnum.getCode()){
            dto.setErrorMsg("改餐柜不在线");
        }
        return buffetEntity;
    }


    /**
     * 后台去强制取餐
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> forceBuffetOutOrder(BuffetOutOrderRequest buffetOutOrderRequest){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetOutOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetOutOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }
        if (Check.NuNStr(buffetOutOrderRequest.getOpId())){
            dto.setErrorMsg("异常的后台操作人");
            return dto;
        }

        //获取订单信息
        OrderInfoVO base = orderManager.getOrderInfoByOrderSn(buffetOutOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }

        Integer isSelf = base.getIsSelf();
        if (Check.NuNObj(isSelf)){
            dto.setErrorMsg("异常的配送类型");
            return dto;
        }
        if (isSelf !=  YesNoEnum.YES.getCode()){
            dto.setErrorMsg("只有自提订单才能取餐");
            return dto;
        }
        if ( ordersStatusEnum.getCode() == OrdersStatusEnum.RECEIVE.getCode()){
            dto.setErrorMsg("您已取餐");
            return dto;
        }

        if ( ordersStatusEnum.getCode() != OrdersStatusEnum.SEND_BUFFET.getCode()){
            dto.setErrorMsg("当前状态不能取餐");
            return dto;
        }
        //更新取餐成功
        orderManager.buffetOutOrder(buffetOutOrderRequest,base,"后台签收");
        return dto;
    }

    /**
     * 系统签收
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> buffetOutOrderBySystemCloseDao(BuffetOutOrderRequest buffetOutOrderRequest,String configCode,String deviceId, String cellNo ){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetOutOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetOutOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }
        if (Check.NuNStr(configCode)){
            dto.setErrorMsg("异常的设备配置信息");
            return dto;
        }

        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("异常的设备号");
            return dto;
        }
        if (Check.NuNStr(cellNo)){
            dto.setErrorMsg("异常的格子编号");
            return dto;
        }
        //校验当前的格子状态
        CellUserStatus cellStatus = getLinkService(configCode).getDeviceOneCellStatus(deviceId, cellNo);
        if (Check.NuNObj(cellStatus)){
            return dto;
        }
        //只有为空的时候才处理
        if (CellUserStatus.EMPTY.getCode() != cellStatus.getCode()){
            return dto;
        }


        //获取订单信息
        OrderInfoVO base = orderManager.getOrderInfoByOrderSn(buffetOutOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }

        Integer isSelf = base.getIsSelf();
        if (Check.NuNObj(isSelf)){
            dto.setErrorMsg("异常的配送类型");
            return dto;
        }
        if (isSelf !=  YesNoEnum.YES.getCode()){
            dto.setErrorMsg("只有自提订单才能取餐");
            return dto;
        }
        if ( ordersStatusEnum.getCode() == OrdersStatusEnum.RECEIVE.getCode()){
            dto.setErrorMsg("您已取餐");
            return dto;
        }

        if ( ordersStatusEnum.getCode() != OrdersStatusEnum.SEND_BUFFET.getCode()){
            dto.setErrorMsg("当前状态不能取餐");
            return dto;
        }

        BuffetInfoRequest buffetInfoRequest = new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(base.getBuffetFid());


        if (!dto.checkSuccess()){
            return dto;
        }
        //更新取餐成功
        int count = orderManager.buffetOutOrder(buffetOutOrderRequest, base, null);
        if (count == 1){
            //发送放餐成功的消息
            this.sendCloseCellSuccess(base.getUserUid());
        }
        return dto;
    }

    /**
     * 取餐
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> buffetOutOrder(BuffetOutOrderRequest buffetOutOrderRequest){

        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetOutOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetOutOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        if (Check.NuNStr(buffetOutOrderRequest.getOpId())){
            dto.setErrorMsg("异常的操作人");
            return dto;
        }

        //获取订单信息
        OrderInfoVO base = orderManager.getOrderInfoByOrderSn(buffetOutOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }

        Integer isSelf = base.getIsSelf();
        if (Check.NuNObj(isSelf)){
            dto.setErrorMsg("异常的配送类型");
            return dto;
        }
        if (isSelf !=  YesNoEnum.YES.getCode()){
            dto.setErrorMsg("只有自提订单才能取餐");
            return dto;
        }
        if ( ordersStatusEnum.getCode() == OrdersStatusEnum.RECEIVE.getCode()){
            dto.setErrorMsg("您已取餐");
            return dto;
        }

        if ( ordersStatusEnum.getCode() != OrdersStatusEnum.SEND_BUFFET.getCode()){
            dto.setErrorMsg("当前状态不能取餐");
            return dto;
        }

        BuffetInfoRequest buffetInfoRequest = new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(base.getBuffetFid());
        //获取当前设备信息
        BuffetEntity buffetEntity = this.getBuffetLocal(dto,buffetInfoRequest);
        if (!dto.checkSuccess()){
            return dto;
        }

        //重置餐格
        this.openCellByOrderSn(dto,buffetEntity.getConfigCode(),buffetEntity.getDeviceId(),buffetOutOrderRequest.getOrderSn());
        if (!dto.checkSuccess()){
            return dto;
        }

        //更新取餐成功
        int count = orderManager.buffetOutOrder(buffetOutOrderRequest, base, null);
        if (count == 1){
            //发送放餐成功的消息
            this.sendCloseCellSuccess(base.getUserUid());
        }
        
        return dto;

    }


    /**
     * 发送申请格子成功消息
     * @param userId
     */
    private void sendCloseCellSuccess(String userId){
        SendThreadPool.execute(()->{
            LogUtil.info(LOGGER,"开始发送放餐推送: userId:{}",userId);
            if (Check.NuNStr(userId)){
                return;
            }
            CellCloseRequest cellCloseRequest = new CellCloseRequest();
            cellCloseRequest.setUserId(userId);

            //发送Message
            messageInfoService.sendMessage4CloseCell(userId);

            //发送推送
            pushServiceProxy.sendCloseCellSuccess(cellCloseRequest);


        });
    }



    /**
     * 放餐到餐柜
     * @author afi
     * @param buffetInOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> buffetInOrder(BuffetInOrderRequest buffetInOrderRequest){

        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetInOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetInOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        if (Check.NuNStr(buffetInOrderRequest.getOpId())){
            dto.setErrorMsg("异常的操作人");
            return dto;
        }

        if (Check.NuNStr(buffetInOrderRequest.getBuffetFid())){
            dto.setErrorMsg("异常的设备号");
            return dto;
        }

        //获取订单信息
        OrderEntity base = orderManager.getOrderBaseBySn(buffetInOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的定点状态");
            return dto;
        }

        Integer isSelf = base.getIsSelf();
        if (Check.NuNObj(isSelf)){
            dto.setErrorMsg("异常的配送类型");
            return dto;
        }
        if (isSelf !=  YesNoEnum.YES.getCode()){
            dto.setErrorMsg("只有自提订单才能放入餐格");
            return dto;
        }

        if ( ordersStatusEnum.getCode() != OrdersStatusEnum.HAS_PAY.getCode()){
            dto.setErrorMsg("当前订单状态不能放入餐格");
            return dto;
        }

        BuffetInfoRequest buffetInfoRequest = new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(buffetInOrderRequest.getBuffetFid());
        //获取当前设备信息
        BuffetEntity buffetEntity = this.getBuffetLocal(dto,buffetInfoRequest);
        if (!dto.checkSuccess()){
            return dto;
        }

        //根据订单号申请表格
        String cellSn = this.applyCellByOrderSn(dto,buffetEntity.getConfigCode(),buffetInOrderRequest.getBuffetFid(),buffetEntity.getDeviceId(),buffetInOrderRequest.getOrderSn());
        if (!dto.checkSuccess()){
            return dto;
        }
        //设置格子号
        buffetInOrderRequest.setCellSn(cellSn);
        //直接保存格子信息
        orderManager.buffetInOrder(buffetInOrderRequest,base);

        String address = buffetEntity.getAddress();
        String buffetFid = buffetEntity.getFid();

        //发送放餐成功的消息
        this.sendApplyCellSuccess(base.getUserUid(),address,buffetFid,cellSn);

        return dto;

    }

    /**
     * 发送申请格子成功消息
     * @param userId
     */
    private void sendApplyCellSuccess(String userId,String address,String buffetFid,String cellNum){
        SendThreadPool.execute(()->{
            LogUtil.info(LOGGER,"开始发送放餐推送: userId:{}",userId);
            if (Check.NuNStr(userId)){
                return;
            }

            //发送Message
            messageInfoService.sendMessage4ApplyCell(userId, address, buffetFid, cellNum);


            CellApplyRequest moneySendRequest = new CellApplyRequest();
            moneySendRequest.setUserId(userId);
            moneySendRequest.setAddress(address);
            moneySendRequest.setBuffetFid(buffetFid);
            moneySendRequest.setCellNum(cellNum);
            //发送推送
            pushServiceProxy.sendApplyCellSuccess(moneySendRequest);


        });
    }



    /**
     * 结束订单
     * @author afi
     * @param finishOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void>  finishOrder(FinishOrderRequest finishOrderRequest){

        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(finishOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(finishOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        if (Check.NuNStr(finishOrderRequest.getOpId())){
            dto.setErrorMsg("异常的操作人");
            return dto;
        }

        //获取订单信息
        OrderEntity base = orderManager.getOrderBaseBySn(finishOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的定点状态");
            return dto;
        }

        Integer isSelf = base.getIsSelf();
        if (Check.NuNObj(isSelf)){
            dto.setErrorMsg("异常的配送类型");
            return dto;
        }

        if (isSelf ==  YesNoEnum.NO.getCode() && ordersStatusEnum.getCode() != OrdersStatusEnum.SEND.getCode()){
            dto.setErrorMsg("当前订单状态不能结束");
            return dto;
        }else  if (isSelf ==  YesNoEnum.YES.getCode() && ordersStatusEnum.getCode() != OrdersStatusEnum.HAS_PAY.getCode()){
            dto.setErrorMsg("当前订单状态不能结束");
            return dto;

        }
        //结束订单
        orderManager.finishOrder(finishOrderRequest,base);
        return dto;

    }
}
