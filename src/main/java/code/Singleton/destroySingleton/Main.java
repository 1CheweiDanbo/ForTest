package code.Singleton.destroySingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    private static String filePath = "C:\\Users\\chewei\\Desktop\\text.txt";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton s1;
        Singleton s2 = Singleton.getInstance();
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s2);
        oos.flush();
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (Singleton)ois.readObject();
        ois.close();
        fis.close();

        System.out.println("result:"+ (s1 == s2));
    }
}
