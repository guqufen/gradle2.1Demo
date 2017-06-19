/**
 * 
 */
package net.fnsco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.fnsco.service.service.TUserService;

/**@desc 
 * @author tangliang
 * @date 2017年6月19日 下午1:03:56
 */
@RestController
public class TuserController {
	
	@Autowired
	private TUserService userService;
	
	@RequestMapping("/get")
	@ResponseBody
	public Object getTUser(Integer id){
		System.out.println("comme ommm");
		return userService.findById(id);
	}
}
