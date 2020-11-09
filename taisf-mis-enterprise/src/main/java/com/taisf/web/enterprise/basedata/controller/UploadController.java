package com.taisf.web.enterprise.basedata.controller;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.common.constant.PathConstant;
import com.taisf.web.enterprise.basedata.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangzhengguang
 * @create 2017-10-17
 **/
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private PathConstant pathConstant;

    /**
     * @author:zhangzhengguang
     * @date:2017/10/17
     * @description:图片上传支持一次上传多张图片
     **/
    @RequestMapping("/uploadPics")
    public
    @ResponseBody
    List<ImageVO> uploadPics(@RequestParam(required = false) MultipartFile[] pics, HttpServletRequest request,String type) throws Exception {
        if (Check.NuNStr(type)){
            type = "common";
        }
        List<ImageVO> urls = new ArrayList<>();
        try {
            for (MultipartFile file : pics) {
                ImageVO vo = getStorePath(type);
                file.transferTo(new File(vo.getPath()));
                urls.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }

    /**
     * 获取文件的存档路径
     * @param type
     * @return
     */
    private ImageVO getStorePath(String type) {
        ImageVO vo = new ImageVO();
        String tmpPath = File.separator  + type + File.separator +DateUtil.dateFormat(new Date()) + File.separator;
        File dest = new File(pathConstant.FILE_PATH+tmpPath);
        if (!dest.exists()){
            dest.mkdirs();
        }
        //设置路径
        tmpPath +=  UUIDGenerator.hexUUID();
        vo.setDbPath(tmpPath);
        vo.setPath(pathConstant.FILE_PATH + tmpPath);
        vo.setFullPath(pathConstant.PIC_URL + tmpPath);
        return vo;
    }


}
