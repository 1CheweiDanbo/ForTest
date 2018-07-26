package code.parallelDesignPatterns.guardedSuspeionsion.instance.threadLoopPrint;

public class PrintRunnable implements Runnable {
    private char character = '?';
    private LockCode lockCode = null;

    public PrintRunnable(char c, LockCode lockCode) {
        this.character = c;
        this.lockCode = lockCode;
    }
    @Override
    public void run() {
        int loopCount = 1;
        while (loopCount<=10){
            synchronized (lockCode){
                try {
                    while (lockCode.getCode() != this.character)
                        lockCode.wait();
                    System.out.println(this.character);
                    loopCount++;
                    lockCode.setCode();
                    lockCode.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
