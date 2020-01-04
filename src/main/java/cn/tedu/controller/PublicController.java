package cn.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author HaiDi
 * @version 下午8:02:54
 * @email 2449365663@qq.com
 */
@Controller
public class PublicController {
	@RequestMapping("/{pageName}")
	public String login(@PathVariable String pageName) {
		return pageName;
	}
//	@RequestMapping("{model}/{pageName}")
//	public String login(@PathVariable String model,@PathVariable String pageName) {
//		return model + "/" + pageName;
//	}
}
