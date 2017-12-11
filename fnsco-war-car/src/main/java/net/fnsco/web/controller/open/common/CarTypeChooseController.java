package net.fnsco.web.controller.open.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/type", method = RequestMethod.POST)
@Api(value = "/api/type", tags = { "业务申请-查询汽车品牌" })
public class CarTypeChooseController {
	

}
