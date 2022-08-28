package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudulentActivityNotifications {

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int num = 0;
        int[] countingSort = new int[201];
        for (int i = 0; i < d; i++) {
            countingSort[expenditure.get(i)]++;
        }
        for (int i = d; i < expenditure.size(); i++) {
            int median = getMedian(countingSort, d);
            if (median*2 <= expenditure.get(i)) num++;
            countingSort[expenditure.get(i)]++;
            countingSort[expenditure.get(i-d)]--;
        }
        return num;
    }

    public static int getMedian(int[] countingSort, int d){
        int left = 0;
        int right = 0;
        int counter = -1;
        if (d%2==0){
            for (int j = 0; j < 201; j++) {
                counter+=countingSort[j];
                if(counter>=d/2-1) {
                    left = j;
                }
                if (counter>=d/2){
                    right = j;
                }
                if (left!=0&&right!=0) break;
            }
            return left+right/2;
        } else {
            for (int j = 0; j < 201; j++) {
                counter+=countingSort[j];
                if (counter>=d/2){
                    return j;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
