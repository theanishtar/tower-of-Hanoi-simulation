package com.code.demo;

import java.util.Scanner;

/**
 *
 * @author Dang Tran Huu
 */
public class towerOfHanoi {

    static Scanner inp = new Scanner(System.in);
    
    void shift(int n, char a, char b) {
        System.out.printf("Chuyen dia thu %d tu coc %c sang coc %c\n", n, a, b);
    }

    void towerHanoi(int n, char a, char b, char c) {
        if (n == 1) {
            shift(1, a, c);
        } else {
            towerHanoi(n - 1, a, c, b);
            shift(n, a, c);
            towerHanoi(n - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        towerOfHanoi al = new towerOfHanoi();
        int n;
        char a = 'A', b = 'B', c = 'C';
        n = inp.nextInt();
        al.towerHanoi(n,a,b,c);
    }
}
