package test.classLoader;


import test.classLoader.TestClassLoader;

public class Test {
    public static void main(String[] args) {
        TestClassLoader testClassLoader = TestClassLoader.getInstance();
        System.out.println("count1: " +testClassLoader.count1);
        System.out.println("count2: "+testClassLoader.count2);
    }
}