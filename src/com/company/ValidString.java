package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ValidString {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test"));

        String s = bufferedReader.readLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static String isValid(String s) {
        char[] chars = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();

        for (char aChar : chars) {
            String curr = String.valueOf(aChar);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        Map<Integer, Integer> freqOfFreq = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            freqOfFreq.put(entry.getValue(), freqOfFreq.getOrDefault(entry.getValue(), 0)+1);
        }
        if (freqOfFreq.size()>2) return "NO";
        if (freqOfFreq.size()==1) return "YES";
        boolean locked = false;
        Map.Entry<Integer, Integer> a = null;
        Map.Entry<Integer, Integer> b = null;
        for (Map.Entry<Integer, Integer> freq : freqOfFreq.entrySet()){
            if (a==null) a = freq;
            else b = freq;
        }
        if(a.getValue()!=1 && b.getValue()!=1) return "NO";
        if (a.getValue()==1 && a.getKey()!=1 && a.getKey()-1!=b.getKey()) return "NO";
        if (b.getValue()==1 && b.getKey()!=1 && b.getKey()-1!=a.getKey()) return "NO";
        return "YES";

    }

}
