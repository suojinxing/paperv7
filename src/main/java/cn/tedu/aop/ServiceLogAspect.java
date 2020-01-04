package cn.tedu.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tedu.annotation.RequestLog;
import cn.tedu.service.LogService;
import cn.tedu.util.IpUtils;
import cn.tedu.util.ShiroUtil;
import cn.tedu.vo.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author HaiDi
 * @version 上午10:05:11
 * @email 2449365663@qq.com
 */
@Slf4j
@Order(1)
@Aspect
@Component // 交给spring管理该类
public class ServiceLogAspect {

	// 1. 定义切入点
	@Pointcut("@annotation(cn.tedu.annotation.RequestLog)")
	public void recordLog() {
	}

	@Around("recordLog()")
	public Object serviceAround(ProceedingJoinPoint jp) throws Throwable {
		log.info("ServiceLogAspect开始执行切面");
		long start = System.currentTimeMillis();
		Object object = jp.proceed();
		long end = System.currentTimeMillis();
		long time = end - start;
		log.info("ServiceLogAspect执行日志切面结束");
		// 保存日志
		saveLog(jp,time);
		
		return object;
	}
	
	@Autowired
	private LogService logService;
	
	private void saveLog(ProceedingJoinPoint jp, long time) throws NoSuchMethodException, SecurityException, JsonProcessingException {
		MethodSignature ms = (MethodSignature) jp.getSignature();
		Class<?>[] parameterTypes = ms.getParameterTypes();
		Class<? extends Object> cls = jp.getTarget().getClass();
		Method method = cls.getDeclaredMethod(ms.getName(), parameterTypes);
		String params = new ObjectMapper().writeValueAsString(jp.getArgs());
		
		Log log = new Log();
		log.setCreatedTime(new Date());
		log.setIp(IpUtils.getIpAddr());
		log.setMethod(method.getName() + "|" + new ObjectMapper().writeValueAsString(parameterTypes));
		RequestLog requestLog = method.getDeclaredAnnotation(RequestLog.class);
		if(requestLog!=null) {
			log.setOperation(requestLog.value());
		}
		log.setParams(params);
		log.setTime(time);
		log.setUsername(ShiroUtil.getName());
		// 保存日志
		logService.saveLog(log);
	}
}
