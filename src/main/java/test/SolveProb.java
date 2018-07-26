package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveProb {
    private static int x = 100;
    public static void main(String[] args) {
        SolveProb solveProb1 = new SolveProb();
        solveProb1.x++;
        SolveProb solveProb2 = new SolveProb();
        solveProb2.x++;
        solveProb1 = new SolveProb();
        solveProb1.x++;
        SolveProb.x--;
        System.out.println("x="+x);
    }
}