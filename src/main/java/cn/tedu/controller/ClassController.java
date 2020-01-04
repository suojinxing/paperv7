package cn.tedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.pojo.Class;
import cn.tedu.service.ClassService;
import cn.tedu.vo.JsonResult;

/**
 * 班级控制器
 * 
 * @author HaiDi
 * @version 下午1:06:59
 * @email 2449365663@qq.com
 */
@Controller
public class ClassController {
	@Autowired
	private ClassService classService;

	@ResponseBody
	@RequestMapping("/findClass")
	public JsonResult findClass() {
		List<Class> classList = classService.findAll();
		JsonResult result = new JsonResult(classList);
		return result;
	}

	@ResponseBody
	@RequestMapping("/updateClassModel")
	public JsonResult updateClassModel(String id, String headmasterId,
			String headmasterName, String classSize, String liberalOrSciences) {
		System.out.println("ClassController.updateClassModel() ===> " + id);
		System.out.println(
				"ClassController.updateClassModel() ===> " + headmasterId);
		System.out.println(
				"ClassController.updateClassModel() ===> " + headmasterName);
		System.out.println(
				"ClassController.updateClassModel() ===> " + classSize);
		System.out.println(
				"ClassController.updateClassModel() ===> " + liberalOrSciences);

		// 创建对象
		
		classService.updateById(id, headmasterId, headmasterName, classSize, liberalOrSciences);;
		JsonResult result = new JsonResult("更新成功");
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteClassById")
	public JsonResult deleteClassById(String classId) {
		classService.deleteById(classId);
		JsonResult result = new JsonResult("删除成功");
		return result;
	}
}
