package code.Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int post = 55533;
        Socket socket = new Socket(host,post);
        OutputStream outputStream = socket.getOutputStream();
        String message = "hello, let`s start to connect";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }
}
