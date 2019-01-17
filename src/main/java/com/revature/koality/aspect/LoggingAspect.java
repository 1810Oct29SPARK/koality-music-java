package com.revature.koality.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static Logger log = LogManager.getLogger();
	private static String entryMsgPrefix = "PerformanceMetricsAspect: entering method";
	private static String exitMsgPrefix = "PerformanceMetricsAspect: exiting method";

	@AfterReturning(pointcut = "within(com.revature.koality.service.*)")
	public void logAfterReturn(JoinPoint jp) {
		log.info(jp.getSignature());
	}

	@AfterThrowing(pointcut = "within(*)", throwing = "t")
	public void logAfterThrow(JoinPoint jp, Throwable t) {
		log.error(jp.getSignature() + " threw " + t.getClass() + " with message " + t.getMessage());
	}

	// @Around("within(com.revature.koality.*) && execution( * *(..))")
	@Around("within(com.revature.koality.*)")
	public Object log(ProceedingJoinPoint call) throws Throwable {

		long startTime = System.currentTimeMillis();

		Log logger = (call.getTarget() == null) ? LogFactory.getLog(LoggingAspect.class)
				: LogFactory.getLog(call.getTarget().getClass());

		trace(logger, true, call, null, 0);

		Object point = call.proceed();

		long endTime = System.currentTimeMillis();
		long executionTime = (endTime - startTime);
		if (logger.isInfoEnabled()) {
			if (executionTime > 500) {
				logger.info("More than 500ms [ " + call.toShortString() + " executionTime : " + executionTime + "]");
				System.out.println("Logger is running: " + "time is " + executionTime);
			}
		}

		trace(logger, false, call, point, executionTime);

		return point;
	}

	public void trace(Log logger, boolean entry, ProceedingJoinPoint call, Object retVal, long time) {
		try {
			if (entry) {
				logger.debug(
						entryMsgPrefix + " [" + call.toShortString() + "] with param : {" + call.getArgs()[0] + "}");
			} else {
				logger.debug(exitMsgPrefix + " [" + call.toShortString() + "with return as: {" + String.valueOf(retVal)
						+ "}  [executionTime : " + time + "]");
			}

		} catch (Exception ignore) {
		}
	}

}
