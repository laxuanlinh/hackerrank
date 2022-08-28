package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrequencyQuery {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(List<Integer> query : queries){
            int a = query.get(0);
            int b = query.get(1);
            if(a==1){
                map.put(b, map.getOrDefault(b, 0)+1);
                freqMap.put(map.get(b), freqMap.getOrDefault(map.get(b), 0)+1);
            }
            if(a==2){
                if (map.getOrDefault(b, 0)>0)
                    freqMap.put(map.get(b), Math.max(freqMap.getOrDefault(map.get(b), 0)-1, 0));
                map.put(b, map.getOrDefault(b, Math.max(map.getOrDefault(b, 0)-1, 0)));
            }
            if(a==3){
                if(freqMap.getOrDefault(b, 0) > 0){
                    result.add(1);
                } else {
                    result.add(0);
                }
            }
        }
        System.out.println("=====================================================");
        for (Integer i : result)
            System.out.println(i);
        return result;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
