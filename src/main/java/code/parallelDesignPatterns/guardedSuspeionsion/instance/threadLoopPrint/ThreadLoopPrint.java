package code.parallelDesignPatterns.guardedSuspeionsion.instance.threadLoopPrint;

public class ThreadLoopPrint {
    public static void main(String[] args) {
        LockCode lockCode = LockCode.newInstance();
        Thread ta = new Thread(new PrintRunnable('A',lockCode));
        Thread tb = new Thread(new PrintRunnable('B',lockCode));
        Thread tc = new Thread(new PrintRunnable('C',lockCode));
        ta.start();
        tb.start();
        tc.start();
    }
}
