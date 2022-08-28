package com.company;

import java.util.Scanner;

public class CountParentheses {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] brackets = scanner.nextLine().toCharArray();
        char open = '(';
        char close = ')';
        int number = 0;
        int tmpOpen = 0;
        int tmpClose = 0;
        for (int i = 0; i < brackets.length; i++) {
            if(brackets[i]==open && tmpClose==0){
                tmpOpen++;
            } else if (brackets[i]==close && tmpOpen!=0){
                tmpClose++;
            } else if (brackets[i]==open && tmpClose!=0){
                number += tmpOpen*tmpClose;
                tmpOpen = 1;
                tmpClose = 0;
            }
        }
        number+=tmpOpen*tmpClose;
        System.out.println(number);
    }

}
