package cn.tedu.controller;

import cn.tedu.constant.UserStatus;
import cn.tedu.pojo.*;
import cn.tedu.service.*;
import cn.tedu.vo.JsonResult;
import cn.tedu.vo.PageObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author HaiDi
 * @version 下午7:29:38
 * @email 2449365663@qq.com
 */
@Controller
public class UserController {
	/*
	 * pageMapper是一个存储状态对应的身份页面的容器
	 */
	private Map<Integer, String> pageMapper = new HashMap<>();
	{
		pageMapper.put(UserStatus.STUDENT, "studenthome");
		pageMapper.put(UserStatus.TEACHER, "teacherhome");
		pageMapper.put(UserStatus.ADMIN, "adminhome");
	}

	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ClassToTeacherService classToTeacherService;
	@Autowired
	private ScoreListService scoreListService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserHeadImageService userHeadImageService;

	@RequestMapping("/doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password) {
		System.out.println("UserController.doLogin() 登录的账号密码：：： " + username + ":" + password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 2.2对用户信息进行身份认证
		subject.login(token);
		User user = userService.findByUsername(username);
		JsonResult result = new JsonResult("login ok");
		result.setData(user);
		return result;
	}

	@RequestMapping("/toAdminHome")
	public String toAdminHome(Model model) {
		System.out.println("执行到了管理员这里。。。");
		model.addAttribute("username", "suojinxing");
		return "adminhome";
	}

	@RequestMapping("/toStudentHome")
	public String toStudentHome(Model model, String username) {
		System.out.println("执行到了学生这里。。。");

		// 验证成功,学生身份
		String stuId = username;
		Student student = studentService.findByStuId(stuId);
		model.addAttribute("student", student);

		// 查询考试场次
		List<ScoreList> scoreLists = scoreListService.findAll();
		model.addAttribute("scoreLists", scoreLists);

		model.addAttribute("username", student.getName());

		return "studenthome";
	}

	@RequestMapping("/toTeacherHome")
	public String toTeacherHome(Model model,String username) {
		System.out.println("执行到了教师这里。。。");
		// -----
		// 验证成功,教师身份
		String teacherId = username; // 用户名就是教师id
		Teacher teacher = teacherService.findByTeacherId(teacherId);
		System.out.println("UserController.login() ===> " + teacher);
		model.addAttribute("teacher", teacher);

		// 获取教师教授的哪些班级,放在集合中
		List<ClassToTeacher> classToTeacherList = classToTeacherService.findByTeacherId(teacherId);
		model.addAttribute("classToTeacherList", classToTeacherList);
		System.out.println("UserController.login() ===> " + classToTeacherList);

		// 获取考试场次的id集合
		List<ScoreList> scoreLList = scoreListService.findAll();
		model.addAttribute("scoreLList", scoreLList);
		System.out.println("UserController.login() ===> " + scoreLList);

		model.addAttribute("username", teacher.getTeacherId());
		// -----
		return "teacherhome";
	}

	/**
	 * 被遗弃的方法，暂时没用！
	 * @param model
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkLogin")
	public String login(Model model, String username, String password, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, // 身份信息
				password);// 凭证信息
		// 2.2对用户信息进行身份认证
		subject.login(token);
		Integer status = userService.checkUsernameAndPassword(username, password);
		if (status == UserStatus.STUDENT) {
			// 验证成功,学生身份
			String stuId = username;
			Student student = studentService.findByStuId(stuId);
			model.addAttribute("student", student);

			// 查询考试场次
			List<ScoreList> scoreLists = scoreListService.findAll();
			model.addAttribute("scoreLists", scoreLists);

			model.addAttribute("username", student.getName());
			return pageMapper.get(status);
		} else if (status == UserStatus.TEACHER) {
			// 验证成功,教师身份
			String teacherId = username; // 用户名就是教师id
			Teacher teacher = teacherService.findByTeacherId(teacherId);
			System.out.println("UserController.login() ===> " + teacher);
			model.addAttribute("teacher", teacher);

			// 获取教师教授的哪些班级,放在集合中
			List<ClassToTeacher> classToTeacherList = classToTeacherService.findByTeacherId(teacherId);
			model.addAttribute("classToTeacherList", classToTeacherList);
			System.out.println("UserController.login() ===> " + classToTeacherList);

			// 获取考试场次的id集合
			List<ScoreList> scoreLList = scoreListService.findAll();
			model.addAttribute("scoreLList", scoreLList);
			System.out.println("UserController.login() ===> " + scoreLList);

			model.addAttribute("username", teacher.getTeacherId());
			return pageMapper.get(status);
		} else if (status == UserStatus.ADMIN) {
			// 验证成功,管理员身份
			model.addAttribute("username", "suojinxing");
			return pageMapper.get(status);
		} else {
			model.addAttribute("errorMsg", "该用户不存在!");
		}
		// 若验证失败,返回主页并提示错误
		return "index";
	}

	@ResponseBody
	@RequestMapping("/updatePassword")
	public Map<String, String> updatePassword(String username, String oldPassword, String newPassword) {
		Map<String, String> msgMap = new HashMap<String, String>();
		System.out.println("账号密码===> " + username + "," + oldPassword + "," + newPassword);

		// 交给service去修改密码
		userService.updatePassword(username, oldPassword, newPassword);
		msgMap.put("msg", "保存成功");
		return msgMap;
	}

	@ResponseBody
	@RequestMapping("/findUser")
	public JsonResult findUser(String username, Integer pageCurrent) {
		PageObject<User> pageObject = userService.findPageObjects(username, pageCurrent);
		JsonResult result = new JsonResult("查询成功");
		result.setData(pageObject);
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteUserFromUserModel")
	public JsonResult deleteUserFromUserModel(String username) {
		userService.deleteByUsername(username);
		JsonResult result = new JsonResult("delete ok!");
		return result;
	}

	@ResponseBody
	@RequestMapping("/updateUserForUserModel")
	public JsonResult updateUserForUserModel(String username, String password, String status) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(status);
		userService.updateUser(username, password, status);
		JsonResult result = new JsonResult("update ok");
		return result;
	}

	// 退出登录
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		String id = session.getId();
		System.out.println("退出登录" + id);
		session.invalidate();
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/user/initHeadImg")
	public JsonResult initHeadImg(String userId){
		String headImageName = userHeadImageService.findHeadImage(userId);
		JsonResult result = new JsonResult("init ok");
		result.setData(headImageName);
		return result;
	}

}
