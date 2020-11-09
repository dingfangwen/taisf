package com.taisf.services.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pathConstant")
public class PathConstant {

	@Value("#{'${pic.url}'}")
	public  String PIC_URL;

	@Value("#{'${file.path}'}")
	public  String FILE_PATH;

}
