package test.classLoader;

public class TestClassLoader {

    private static TestClassLoader testClassLoader = new TestClassLoader();
    public static int count1;
    public static int count2 = 0;

    private TestClassLoader() {
        count1++;
        count2++;
    }

    public static TestClassLoader getInstance(){
        return testClassLoader;
    }
}
