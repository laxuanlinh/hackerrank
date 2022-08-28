package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static long countTriplets(List<Long> arr, long r) {
        long triplets = 0;
        Map<Long, Integer> mapI = new HashMap<>();
        Map<Long, Integer> mapJ = new HashMap<>();
        Map<Long, Integer> mapK = new HashMap<>();
        arr = arr.stream().sorted().collect(Collectors.toList());
        for (int index = 0; index < arr.size(); index++) {
            mapI.put(arr.get(index), mapI.getOrDefault(arr.get(index), 0)+1);
            mapJ.put(arr.get(index), mapJ.getOrDefault(arr.get(index), 0)+1);
            mapK.put(arr.get(index), mapK.getOrDefault(arr.get(index), 0)+1);
        }
        for (int index = 0; index < arr.size(); index++) {
            long i = arr.get(index);
            int countI = mapI.get(i);
            int countJ = 0;
            int countK = 0;
            if(mapJ.containsKey(i*r)){
                countJ=mapJ.get(i*r);
                mapJ.put(i*r, 0);
            }
            if(mapK.containsKey(i*r*r)){
                countK=mapK.get(i*r*r);
                mapK.put(i*r*r, 0);
            }
            if (r==1)
                triplets+=binomial(countJ, 3).longValue();
            else
                triplets+=countI*countJ*countK;
        }
        System.out.println(triplets);
        return triplets;
    }

    static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N-k))
                    .divide(BigInteger.valueOf(k+1));
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
