package com.taisf.web.oms.basedata.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taisf.services.supplier.api.SupplierService;

@Controller
@RequestMapping("base/supplier")
public class SupplierController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

	@Resource(name = "supplier.supplierServiceProxy")
	private SupplierService supplierService;
	
	
}
