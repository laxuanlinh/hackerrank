package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;



class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) {
        if(a.score > b.score) return -1;
        if(a.score < b.score) return 1;
        for (int i = 0; i < a.name.length(); i++) {
            if(i>=b.name.length()) return -1;
            if(a.name.charAt(i) > b.name.charAt(i)) return 1;
            else if (a.name.charAt(i) < b.name.charAt(i)) return -1;
        }
        return 0;
    }
}


public class SortingComparator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}

class Player{
    public String name;
    public int score;
    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}