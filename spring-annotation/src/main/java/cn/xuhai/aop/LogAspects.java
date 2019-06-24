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
	 * ��ȡ�������������ʽ 
	 * 1.��������  @Before("pointCut()")
	 * 2.�������������� @Before("cn.xuhai.aop.LogAspects.pointCut()")
	 */
	@Pointcut("execution(public int cn.xuhai.aop.MathCalculator.*(..))")
	public void pointCut() {};

	
//	@Before("public int cn.xuhai.aop.MathCalculator.div(int,int)")
//	@Before("public int cn.xuhai.aop.MathCalculator.*(..)")
	// �ಿ����
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName()
				+"��ʼ���С����������б�{"+Arrays.asList(args)+"}");
	}
	
	// �ⲿ�����÷�ʽ
	@After("cn.xuhai.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName() + "�������С�����");
	}
	
	@AfterReturning(value = "pointCut()", returning="resule")
	public void logReturn(Object resule) {
		System.out.println("�������ء���������ֵ��{" + resule +"}");
	}
	
	@AfterThrowing(value="pointCut()",throwing="exception")
	public void logException(Exception exception) {
		System.out.println("�쳣�������쳣��Ϣ��{"+exception+"}");
	}
}
