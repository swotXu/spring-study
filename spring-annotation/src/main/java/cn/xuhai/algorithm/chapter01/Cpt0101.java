package cn.xuhai.algorithm.chapter01;

/**
 * ŷ������㷨
 * ���������Ǹ����� p �� q �����Լ����
 * @author xuhai
 */
public class Cpt0101 {

	public static int gcd(int p, int q) {
		return q == 0? p : gcd(q, p % q);
	}
	
	public static void test() {
		System.out.println(true && false || true && true); // true
		System.out.println((1 + 2.26)/2);	// 1.63
		System.out.println(1 + 2 + "3");	// 33
	}
	public static void main(String[] args) {
//		System.out.println(Cpt0101.gcd(7, 14));
		Cpt0101.test();
	}
}
