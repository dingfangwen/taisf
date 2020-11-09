package com.taisf.web.enterprise.task;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.excel.utils.ExcelUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.util.PrinterUtil;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.DayTaskRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.vo.DayTaskVO;
import com.taisf.services.order.vo.OrderSendStatsVo;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;
import com.taisf.services.supplier.manager.SupplierPrinterManagerImpl;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.constant.PrinterConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:zhangzhengguang
 * @description:
 **/
@Controller
@RequestMapping("everydayTask/")
public class EverydayTaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EverydayTaskController.class);

    @Autowired
    private OrderManagerImpl orderManagerImpl;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private OrderService orderService;

    @Resource(name = "supplierPrinterManagerImpl")
    private SupplierPrinterManagerImpl supplierPrinterManagerImpl;
    @Autowired
    private PrinterConstant printerConstant;



    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:菜品统计列表
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
        String  supplierCode = null;
        if (!Check.NuNObj(emp)){
            supplierCode = emp.getEmpBiz() ;
        }
        List<EnterpriseEntity> entityList = enterpriseService.taskByOrder(supplierCode).getData();
        request.setAttribute("entityList", entityList);
        return "everydayTask/everydayTaskList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:菜品统计列表数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, DayTaskRequest dayTaskRequest) {
        PageResult pageResult = new PageResult();
        try {

            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            String  supplierCode = null;
            if (!Check.NuNObj(emp)){
                supplierCode = emp.getEmpBiz() ;
            }
            if (Check.NuNStr(supplierCode)){
                return new PageResult();
            }
            dayTaskRequest.setSupplierCode(supplierCode);
            DataTransferObject<PagingResult<DayTaskVO>> dto = orderService.getEverydayTaskPgeList(dayTaskRequest);
            if (!dto.checkSuccess()){
                return pageResult;
            }
            PagingResult<DayTaskVO> pagingResult = dto.getData();
            if (!Check.NuNObj(pagingResult)) {
                pageResult.setRows(pagingResult.getList()==null?new ArrayList<>():pagingResult.getList());
                pageResult.setTotal(pagingResult.getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(dayTaskRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    @RequestMapping("everydayTaskListExcel")
    public void everydayTaskListExcel(HttpServletRequest request, HttpServletResponse response,DayTaskRequest dayTaskRequest) throws UnsupportedEncodingException {
        response.setContentType("octets/stream");
        String fileName = "每日任务";
        response.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1") +".xls");

        try{
            List<DayTaskVO> dayTaskVOS = orderService.findaAllEverydayTask(dayTaskRequest).getData();
            if (!Check.NuNCollection(dayTaskVOS)){
                ExcelUtil.exportExcel(response.getOutputStream(),dayTaskVOS);
            }
        }catch (Exception e){
            LogUtil.error(LOGGER, "收费列表导出excel异常:{}",e);
        }
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:企业订单配送
     **/
    @RequestMapping("orderDistributionList")
    public String orderDistributionList(HttpServletRequest request) {
        List<EnterpriseEntity> entityList = enterpriseService.findAll().getData();
        request.setAttribute("entityList", entityList);
        return "everydayTask/orderDistributionList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:企业订单配送
     **/
    @RequestMapping("finOrderDistributionList")
    @ResponseBody
    public PageResult finOrderDistributionList(HttpServletRequest request, EnterpriseListRequest enterpriseListRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            enterpriseListRequest.setSupplierCode(emp.getEmpBiz());
            PagingResult<OrderSendStatsVo> pagingResult = orderManagerImpl.finOrderDistributionList(enterpriseListRequest);
            if (!Check.NuNObj(pagingResult)) {
                pageResult.setRows(pagingResult.getList());
                pageResult.setTotal(pagingResult.getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(enterpriseListRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:配送当前企业下所有订单
     **/
    @RequestMapping("distribution")
    @ResponseBody
    public DataTransferObject<Void> distribution(HttpServletRequest request, OrderEntity orderEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if(Check.NuNObjs(orderEntity)){
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        if(Check.NuNObjs(orderEntity.getEnterpriseCode(),orderEntity.getAddressFid(),orderEntity.getOrderType())){
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        try {
            orderEntity.setOrderStatus(OrdersStatusEnum.SEND.getCode());
            orderManagerImpl.sendByEnterpriseCode(orderEntity);
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(orderEntity));
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/19
     * @description:据企业code查询企业下所有待配送订单
     **/
    @RequestMapping("findListByEnterpriseCode")
    @ResponseBody
    public PageResult findListByEnterpriseCode(HttpServletRequest request, OrderInfoRequest orderInfoRequest) {
        PageResult pageResult = new PageResult();
        try {
            if (Check.NuNObj(orderInfoRequest)){
                orderInfoRequest = new OrderInfoRequest();
            }

            PagingResult<OrderEntity> pagingResult = orderManagerImpl.findListByEnterpriseCode(orderInfoRequest);
            if (!Check.NuNObj(pagingResult)) {
                pageResult.setRows(pagingResult.getList());
                pageResult.setTotal(pagingResult.getTotal());
            }else{
                List<OrderEntity> list = new ArrayList<>();
                list.add(new OrderEntity());
                pageResult.setRows(list);
                pageResult.setTotal(1L);
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(orderInfoRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/21
     * @description:打印订单
     **/
    @RequestMapping("printTask")
    @ResponseBody
    public DataTransferObject<Void> printTask(HttpServletRequest request,DayTaskRequest dayTaskRequest) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            SupplierPrinterRequest supplierPrinterRequest = new SupplierPrinterRequest();
            supplierPrinterRequest.setSupplierCode(emp.getEmpBiz());
            supplierPrinterRequest.setIsDefault(1);
            List<SupplierPrintterEntity> listSupplierPrinter = supplierPrinterManagerImpl.findListSupplierPrinter(supplierPrinterRequest);
            if(Check.NuNCollection(listSupplierPrinter)){
                LogUtil.error(LOGGER, "printTask 查询供应商信息失败 :{}", JsonEntityTransform.Object2Json(supplierPrinterRequest));
                dto.setErrorMsg("查询供应商信息失败");
                return dto;
            }
            List<DayTaskVO> dayTaskVOS = orderService.getEverydayTaskPgeList(dayTaskRequest).getData().getList();
                if(Check.NuNCollection(dayTaskVOS)){
                    LogUtil.error(LOGGER, "printTask 查询查询任务信息失败 :{}", JsonEntityTransform.Object2Json(dayTaskRequest));
                    dto.setErrorMsg("查询任务信息失败");
                    return dto;
                }
            List<DayTaskVO> newList =dayTaskVOS.stream().sorted(Comparator.comparing(DayTaskVO::getWindowName)).collect(Collectors.toList());
            String printerNum = listSupplierPrinter.get(0).getPrinterNum();
            StringBuilder sb = new StringBuilder(1024);
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+":<BR>");
            for (DayTaskVO dayTaskVO : newList) {
                sb.append("<BOLD><B>"+dayTaskVO.getWindowName()+"<BR></B></BOLD>");
                sb.append("<BOLD><B>"+Splice(dayTaskVO.getProductName(),dayTaskVO.getProductNum())+"<BR></B></BOLD>");
                sb.append("--------------------------------<BR><BR><CUT>");
            }
            PrinterUtil.print(printerConstant.USER,printerConstant.UKEY,printerNum,sb.toString());
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    public String Splice(String productName,Integer productNum){

        Integer productNameLengtth = productName.length();
        Integer max  = 16;
        Integer tailLength = (productNum + "份").length();
        String spaceStr = "";
        int spaceNum = max - (productNameLengtth + tailLength);
        for (int i = 0; i < spaceNum ; i++) {
            spaceStr +="  ";
        }
        return productName + spaceStr + productNum + "份";
    }
}
