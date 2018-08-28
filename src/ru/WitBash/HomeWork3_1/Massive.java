package ru.WitBash.HomeWork3_1;

import java.util.*;

public class Massive {
    public static void main(String[] args) {

        String[] array = {"one", "two", "three", "four", "five", "six", "seven", "one", "four",
                "two", "eight", "one", "nine", "seven", "ten", "six", "one", "two", "five", "three"};
        ArrayList<String> a1 = new ArrayList<String>();
        uniqueMember(array, a1);
        repeatMember(array, a1);
    }

    static void uniqueMember(String[] arr, ArrayList<String> a) {
        a.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (!arr[i].equals(arr[j])) count++;
                if (count == i) a.add(arr[i]);
            }
        }
        System.out.println(a);
    }

    static void repeatMember(String[] arr, ArrayList<String> a) {
        for (int i = 0; i < a.size(); i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (a.get(i).equals(arr[j])) count++;
            }
            if (count > 1) System.out.println(a.get(i) + " встречается " + count + " раз(а)");
        }
    }
}
