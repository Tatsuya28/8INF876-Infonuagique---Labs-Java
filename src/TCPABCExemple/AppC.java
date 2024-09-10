package TCPABCExemple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AppC {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(10122);
        System.out.println("App C Started...");
        Socket socket = s.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket
                .getInputStream()));
        String str = in.readLine();
        System.out.println("from B : " + str);
        socket.close();
        try {
            socket = new Socket("localhost", 10120);
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
            out.println(str + "C");
            socket.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }
        s.close();
    }
} 