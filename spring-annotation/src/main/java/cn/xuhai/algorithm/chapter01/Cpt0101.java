package cn.xuhai.algorithm.chapter01;

/**
 * 欧几里得算法
 * 计算两个非负整数 p 和 q 的最大公约数。
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
