package main;

public class Test {
    public static void main(String[] args) {
        TestOb ob = new TestOb();
        ob.setVal("OldVal");
        test(ob);
        System.out.println(ob.getVal());
    }

    public static void test(TestOb ob) {
        ob = null;
        ob = new TestOb();
        ob.setVal("New Val");
    }
}
