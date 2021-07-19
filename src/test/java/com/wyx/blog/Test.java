package com.wyx.blog;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public  class Test {
    public static void main(String[] args) {
        String[] strs = {"12347809933", "67891", "12347809933", "98765432102", "67891", "12345"};
        List lis = new LinkedList();
        List<String> lis2 = new LinkedList();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            lis.add(s);
            lis2 = del(lis);
        }
        for (String string : lis2) {
            System.out.println(string);
        }
    }

    public static List del(List a) {

        for (int i = 0; i < a.size()-1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).equals(a.get(j))) {
                    a.remove(a.get(j));
                }
            }
        }
        return a;
    }
}