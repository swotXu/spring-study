package cn.xuhai.algorithm.chapter01;

import java.util.Scanner;

import org.junit.Test;

/**
 * �㷨��һ�¡�����
 * @author apink
 *
 */
public class Cpt0101 {

	public static void main(String[] args) {
		System.out.println(Cpt0101.gcd(7, 14));
	}
	
	/**
	 * ŷ������㷨
	 * ���������Ǹ����� p �� q �����Լ����
	 */
	public static int gcd(int p, int q) {
		return q == 0? p : gcd(q, p % q);
	}
	/**
	 * ����һ���������Ķ�������ʽ
	 * @param x
	 */
	public static void tenToTwoString(int x) {
		int y = x;
		//����1
		String s2 = "";
		for(int n = x; n > 0; n >>= 1)
			s2 = (n & 1) + s2;
		System.out.println(y +"�Ķ�����Ϊ��"+ s2);
		
		//����2
		String s = "";
		do{
			s = (x & 1) + s;
		}while((x >>= 1) > 0);
		System.out.println(y +"�Ķ�����Ϊ��"+ s);
		
		Integer.toBinaryString(y);
	}
	
	@Test
	public void test01() {
		System.out.println(true && false || true && true); // true
		System.out.println((1 + 2.26)/2);	// 1.63
		System.out.println(1 + 2 + "3");	// 33
	}
	@Test
	public void test02() {
		for (int i = 0; i < 16; i++) {
			Cpt0101.tenToTwoString(i);
		}
	}
	@Test
	public void test03() {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		System.out.println(a == b && b == c);
	}
	
	
	
}
