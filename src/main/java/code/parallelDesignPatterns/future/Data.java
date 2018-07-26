package code.parallelDesignPatterns.future;

public interface Data {
    String getResult() throws InterruptedException;
}
