package cn.xuhai.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspects {
	/**
	 * 抽取公共的切入点表达式 
	 * 1.本类引用  @Before("pointCut()")
	 * 2.其他切面类引用 @Before("cn.xuhai.aop.LogAspects.pointCut()")
	 */
	@Pointcut("execution(public int cn.xuhai.aop.MathCalculator.*(..))")
	public void pointCut() {};

	
//	@Before("public int cn.xuhai.aop.MathCalculator.div(int,int)")
//	@Before("public int cn.xuhai.aop.MathCalculator.*(..)")
	// 类部引用
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName()
				+"开始运行。。。参数列表：{"+Arrays.asList(args)+"}");
	}
	
	// 外部类引用方式
	@After("cn.xuhai.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "结束运行。。。");
	}
	
	@AfterReturning(value = "pointCut()", returning="resule")
	public void logReturn(Object resule) {
		System.out.println("正常返回。。。返回值：{" + resule +"}");
	}
	
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void logException(Exception exception) {
		System.out.println("异常。。。异常信息：{"+exception+"}");
	}
}
