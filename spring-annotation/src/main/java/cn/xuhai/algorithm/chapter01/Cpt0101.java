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
	public static void main(String[] args) {
		System.out.println(Cpt0101.gcd(7, 14));
	}
}
