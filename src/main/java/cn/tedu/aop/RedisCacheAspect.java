package cn.tedu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.tedu.annotation.RedisCacheFind;
import cn.tedu.util.ObjectMapperUtil;
import redis.clients.jedis.JedisCluster;

@Order(2)
@Component
@Aspect
public class RedisCacheAspect {
	@Autowired
	private JedisCluster jedis;

	@Around("@annotation(redisCacheFind)")
	public Object around(ProceedingJoinPoint jp, RedisCacheFind redisCacheFind) throws Throwable {
		System.out.println("redis分片成功");
		Object obj = null;
		String key = getKey(jp, redisCacheFind);
		System.out.println(key);
		// 1. 查询缓存数据
		String result = jedis.get(key);
		try {
			if (StringUtils.isEmpty(result)) { // 如果不存在缓存，则查询数据库
				obj = jp.proceed(); // 执行目标方法，获取结果数据
				// 将数据保存到redis中
				String json = ObjectMapperUtil.toJSON(obj);
				// 判断用户是否传入超时时间
				if (redisCacheFind.seconds() == 0) {
					// 说明用户没有指定超时时间。
					jedis.set(key, json);
				} else {
					int seconds = redisCacheFind.seconds();
					// 否则，指定用户的超时时间
					jedis.setex(key, seconds, json);
				}
			} else { // 存在缓存数据redis中有需要的数据
				// 将json转换为对象
				Class<?> targetClass = getType(jp);
				obj = ObjectMapperUtil.toObject(result, targetClass);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		System.out.println("成功进入到redisAOP中");
		return obj;
	}

	// 获取方法的返回值类型
	private Class<?> getType(ProceedingJoinPoint jp) {
		MethodSignature mSignature = (MethodSignature) jp.getSignature();
		return mSignature.getReturnType();
	}

	private String getKey(ProceedingJoinPoint jp, RedisCacheFind redisCacheFind) {
		MethodSignature ms = (MethodSignature) jp.getSignature();
		String className = ms.getDeclaringTypeName();
		String methodName = ms.getName();
		System.out.println("name1 ++" + className);
		System.out.println("name2" + methodName);

		String key = redisCacheFind.key();
		if (key == null || key == "") {
			Object arg0 = jp.getArgs();
			// 自己赋值 类名+方法名+::+参数
			key = className + methodName + "::" + arg0;
		} else {
			key = className + methodName + "::" + key;
		}
		return key;
	}
}
