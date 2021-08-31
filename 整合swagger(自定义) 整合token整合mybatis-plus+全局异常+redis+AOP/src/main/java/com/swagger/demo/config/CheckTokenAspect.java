package com.swagger.demo.config;

import com.swagger.demo.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * token效验切面
 */
@Aspect
@Component
public class CheckTokenAspect {

	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 设置token效验切入点,在注解的位置切入代码
	 */
	@Pointcut("@annotation(com.swagger.demo.config.CheckToken)")
	public void checkTokenCut() {

	}

	@Before("checkTokenCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 读取缓存
		String token = redisUtils.get("token");
		if (token == null) {
			throw new BaseException(BaseErrorEnum.USER_INVALID);
		}
		if (StringUtils.isEmpty(token)) {
			throw new BaseException(BaseErrorEnum.USER_INVALID);
		}
	}

}
