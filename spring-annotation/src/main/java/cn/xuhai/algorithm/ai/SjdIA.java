package cn.xuhai.algorithm.ai;

import java.util.Random;
import java.util.Scanner;

public class SjdIA {
	private final int SHI_TOU = 0;
    private final int JIAN_DAO = 1;
    private final int BU = 2;

    private final int ERRO = -1;
    private Scanner scanner = new Scanner(System.in);
    private int[][] memory = new int[3][3];     // ��¼ǰ���������
    private int prev = SHI_TOU;  // ��һ�����ּ�¼ ��ʼ��ʯͷ
    
    private int player_win = 0;
    private int ai_win = 0;
    private int fair_win = 0;
    
    private int[] test = {2,2,1,0,2,1};

    public void start(){
        int user_now = ERRO; // ��ҵ�ǰ��ȭ���
        int i = 0;
        while (ERRO != (user_now = userInput(i))){
            // �²��û���ǰ���Ľ��
            System.out.println("~~~~~~~�²⣺~~~~~~~" );
            int guess = getMax(memory, prev);
            printResult(false, guess);
            // ���������Ӧ�ó�ʲô����
            int win = getWin(guess);
            System.out.print("Ӧ�ó������Ӯ��" + win);
            printResult(true, win);
            System.out.println("~~~~~~~~~~~~~~~~~~~" );

            // �鿴���
            int pk = pk(user_now, win);
            pkPrint(pk);

            System.out.println("---------------------");
            // ��¼���
            memory[prev][user_now] ++;
            prev = user_now;
            i ++;
            if(i == 500) break;
        }
        System.out.println("���Ӯ��" + player_win + ",������Ӯ��" + ai_win + ",ƽ�֣�" + fair_win);
        printMemory();
    }
    private void printMemory(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(memory[i][j] + " ");
            }
                System.out.println();
        }
    }

    /**
     * ��ȡ������
     * @param memory
     * @param prev
     * @return
     */
    private int getMax(int[][] memory, int prev){
    	int index = -1;
        int max = -1;
        for (int i = 0; i < 3; i++) {
        	System.out.print("memory[" + prev + "][" + i + "]=" + memory[prev][i] + "\t");
        	if (memory[prev][i] > max) {
        		max = memory[prev][i];
        		index = i;
        	}
        }
        return index;
    }

    /**
     * ����Ӧ�ó�ʲô
     * @param i
     * @return
     */
    private int getWin(int i){
        return (i + 2 ) % 3;
    }

    /**
     * pk
     * @param player    ��ҽ��
     * @param ai    �����˽��
     * @return  1-������Ӯ  0-ƽ�� -1-���Ӯ
     */
    private int pk(int player, int ai){
        if(player == ai) return 0;
        return (player + 2 ) % 3 == ai ? -1 : 1;
    }
    private void pkPrint(int result){
        System.out.println("------------���������");
        switch (result) {
            case -1:
            	ai_win ++;
                System.out.println("������Ӯ"); break;
            case 0:
            	fair_win ++;
                System.out.println("ƽ��"); break;
            case 1:
            	player_win ++;
                System.out.println("���Ӯ"); break;
        }
        System.out.println();
    }

    private int userInput(int i){
        System.out.println("ʯͷ����...(0.ʯͷ��1.������2.��)...\n" + "�����");
//        String inStr = scanner.nextLine();
//        if (inStr == null) return ERRO;
//
//        inStr = inStr.length() > 1 ? inStr.substring(0,1) : inStr;
//        int now = Integer.valueOf(inStr);
//        int now = new Random().nextInt(100) % 3;
        int now = test[i % test.length];
        System.out.println(now);
        if (now > BU) {
            System.out.println("��ȭ�����Ϲ淶��");
            return ERRO;
        }
        printResult(false, now);
        return now;
    }

    /**
     *
     * @param type      true - ������  false - ��
     * @param result
     */
    private void printResult(boolean type, int result){
        String name = type? "������" : "���";
        switch (result) {
            case 0:
                System.out.println(name + "����ʯͷ"); break;
            case 1:
                System.out.println(name + "��������"); break;
            case 2:
                System.out.println(name + "������"); break;
        }
    }

    public static void main(String[] args) {
    	SjdIA ai = new SjdIA();
        ai.start();

    }
}
