package TCPABCExemple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AppA {

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 10121);
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
            out.println("A");
            out.close();
            socket.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }

        ServerSocket s = new ServerSocket(10120);
        System.out.println("App A Started...");
        Socket socket = s.accept();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        String str = in.readLine();
        System.out.println("from C : " + str);
        socket.close();
        s.close();
    }
} 

