package cn.tedu.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.vo.JsonResult;

/**
 * 
 * @author HaiDi
 * @version 下午7:34:41
 * @email 2449365663@qq.com
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
	private static final long serialVersionUID = 1L;

	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandlerRuntimeException(Throwable t) {
		System.out.println("执行了全局异常");
		t.printStackTrace();
		return new JsonResult(t);
	}

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doHandleShiroException(ShiroException e) {
		JsonResult r = new JsonResult();
		r.setState(0);
		if (e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		} else if (e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		} else if (e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		} else if (e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		} else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}
}
