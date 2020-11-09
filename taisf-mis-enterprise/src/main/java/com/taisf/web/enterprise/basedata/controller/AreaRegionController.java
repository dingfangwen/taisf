package com.taisf.web.enterprise.basedata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.jk.framework.base.utils.Check;
import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.base.service.AreaRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("base/region")
public class AreaRegionController {
	
	@Resource(name="base.areaRegionServiceProxy")
	private AreaRegionService areaRegionService;
	
	/**
	 * 根据区域父code查询 子 区域
	 * @param pid
	 * @param response
	 */
	@RequestMapping(value="listByParentCode",method = RequestMethod.POST)
	public void listByParentCode(HttpServletResponse response,@RequestBody String pid) throws IOException {
		Map<String, Object> map = JSON.parseObject(pid,new TypeReference<Map<String, Object>>(){} );
		Object obj = map.get("pid");
		if(Check.NuNStr((String)obj)){
			String jsonString = JSONArray.toJSONString(new ArrayList<AreaRegionEntity>());
			response.getWriter().println(jsonString);
		}else{
			List<AreaRegionEntity> list = areaRegionService.findAllByParentCode(Integer.parseInt((String) obj));
			String jsonString = JSONArray.toJSONString(list);
			response.getWriter().println(jsonString);
		}
	}	

}
