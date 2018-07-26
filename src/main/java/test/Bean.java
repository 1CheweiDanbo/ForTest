package test;

public class Bean {
    private int a;
    private int b;
    public Bean(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "a:" + a +
                ", b:" + b ;
    }
}
