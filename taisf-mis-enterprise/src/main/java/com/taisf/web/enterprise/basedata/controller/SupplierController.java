package com.taisf.web.enterprise.basedata.controller;

import com.taisf.services.supplier.api.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("base/supplier")
public class SupplierController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

	@Resource(name = "supplier.supplierServiceProxy")
	private SupplierService supplierService;
	
	
}
