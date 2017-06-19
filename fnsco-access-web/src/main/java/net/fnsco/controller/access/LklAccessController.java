package net.fnsco.controller.access;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.internal.xjc.generator.bean.ImplStructureStrategy.Result;

import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.User;

@RestController
@RequestMapping(value = "/app/trade", method = RequestMethod.POST)
public class LklAccessController extends BaseController {

    /**
     * 保存拉卡拉交易数据到库
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ResultDTO findByName(@RequestParam(value = "userName", required = true) String userName) {
        return success();
    }
}
