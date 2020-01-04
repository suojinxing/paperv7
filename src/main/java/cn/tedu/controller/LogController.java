package cn.tedu.controller;

import cn.tedu.service.LogService;
import cn.tedu.vo.JsonResult;
import cn.tedu.vo.Log;
import cn.tedu.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping("findLog")
	public JsonResult findLog(String username, Integer pageCurrent) {
//		List<Log> logList = logService.findLogs();
		PageObject<Log> pageObject = logService.findPageObjects(username, pageCurrent);
		JsonResult result = new JsonResult(pageObject);
		return result;
	}
}
