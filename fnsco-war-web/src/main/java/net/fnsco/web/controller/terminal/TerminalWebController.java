package net.fnsco.web.controller.terminal;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.bigdata.api.terminal.TerminalService;
import net.fnsco.bigdata.service.domain.TerminalInformation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
@Controller
@RequestMapping(value="/web/terminal")
public class TerminalWebController extends BaseController{
	@Autowired
	private TerminalService terminalService;
	/**
     * 终端管理分页查询
     * 
     * @param tradeDataDTO
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = { "m:trade:list" })
    public ResultPageDTO<TerminalInformation> query(TerminalInformation TerminalInformation, Integer currentPageNum, Integer pageSize) {

        return terminalService.queryTerminalData(TerminalInformation, currentPageNum, pageSize);
    }
}
