package main;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        List<TestOb> lst = new ArrayList<>();
        lst.add(new TestOb());
        System.out.println(lst.size());
        Test3.test(lst);
        System.out.println(lst.size());
    }

    public static void test(List<TestOb> ob) {
        ob.clear();
    }
}
