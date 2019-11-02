package cn.xuhai.algorithm.ai;

public class Demo {

	public interface MyTest{
		default void t1(String s) {
			System.out.println(s);
		}
		
		void t2(String s);
	}
	
	public class MyTestImpl implements MyTest{
		
		public void t1(String s) {
			t1(s);
		}
		@Override
		public void t2(String s) {
			t1(s);
		}
	}

	public static void main(String[] args) {
		Demo.test(s->System.out.println(s));
		Demo.test(Demo::test2);
	}
	
	public static void test(MyTest myTest) {
		myTest.t1("ssss");
	}
	public static void test2(String s) {
		System.out.println(s);
	}
	

}
