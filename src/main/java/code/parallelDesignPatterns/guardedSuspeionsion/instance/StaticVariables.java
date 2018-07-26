package code.parallelDesignPatterns.guardedSuspeionsion.instance;

public class StaticVariables extends Thread {
    public static String[] CHARS = new String[]{"A","B","C"};
    public static int POS = 0;
    private int count  = 10;

    public StaticVariables(String name) {
        this.setName(name);
    }

    @Override
    public void run(){
        while (count>0){
            if(this.getName().equals(CHARS[POS])){
                this.print();
                this.count--;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private synchronized void print() {
        System.out.println(this.getName());
        POS = (POS >= CHARS.length-1 ? 0 : ++POS);
    }

    public static void main(String[] args) {
        new StaticVariables("A").start();
        new StaticVariables("B").start();
        new StaticVariables("C").start();
    }
}
