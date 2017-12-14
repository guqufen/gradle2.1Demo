package net.fnsco.web.controller.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/h5/index", method = RequestMethod.POST)
@Api(value = "/h5/index", tags = { "首页显示接口" })
public class IndexController extends BaseController {
	private ResultDTO saveFinance( ) {
		return null;
	}
	
}
