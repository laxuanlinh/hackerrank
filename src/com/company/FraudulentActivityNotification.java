package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FraudulentActivityNotification {

    static int activityNotifications(List<Integer> exp, int d){
        int notifications = 0;
        List<Integer> list = new ArrayList<>();
        for (int i=d; i<exp.size(); i++){
            if (i==d){
                for (int j=1; j<=d; j++){
                    list.add(exp.get(i-j));
                }
                list = sort(list);
            } else {
                list = quickInsert(list, exp.get(i-1));
            }

            double median = 0.0;
            try {
                if (list.size() % 2==1){
                    median = ((double)list.get(list.size()/2-1)+(double)list.get(list.size()/2+1))/2;
                } else {
                    median = ((double)list.get(list.size()/2-1)+(double)list.get(list.size()/2))/2;
                }
            } catch (Exception e){
                System.out.println(list.size());
                throw new RuntimeException("Something wrong");
            }
            if (exp.get(i) >= median*2){
                notifications++;
            }
        }
        return notifications;
    }

    static List<Integer> quickInsert(List<Integer> list, Integer integer){
        list.remove(0);
        List<Integer> output = new ArrayList<>();
        if (integer > list.get(list.size()-1)){
            output.addAll(list);
            output.add(integer);
            return output;
        }
        boolean alreadyAdded = false;
        for (int i=0; i<list.size(); i++){
            if (i==0 && integer < list.get(0)){
                output.add(integer);
                output.addAll(list);
                break;
            } else if (i>0 && integer >= list.get(i-1) && integer <= list.get(i) && !alreadyAdded){
                output.add(integer);
                alreadyAdded = true;
            }
            output.add(list.get(i));
        }
        return output;
    }

    static List<Integer> sort(List<Integer> list){
        if (list.size()<2){
            return list;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i=0; i<list.size()/2; i++){
            left.add(list.get(i));
        }
        for (int i=list.size()/2; i<list.size(); i++){
            right.add(list.get(i));
        }
        return merge(sort(left), sort(right));
    }

    static List<Integer> merge(List<Integer> left, List<Integer> right){
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<left.size() && j<right.size()){
            if (left.get(i) < right.get(j)){
                list.add(left.get(i));
                i++;
            } else {
                list.add(right.get(j));
                j++;
            }
        }
        if (i==left.size()){
            for (int index=j; index < right.size(); index++){
                list.add(right.get(index));
            }
        } else {
            for (int index = i; index < left.size(); index++){
                list.add(left.get(index));
            }
        }
        return list;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int d = Integer.parseInt(firstMultipleInput[1]);
        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        bufferedReader.close();
    }

}
