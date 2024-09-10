package UDPABCExample;// : ChatterClient.java
// Tests the ChatterServer by starting multiple
// clients, each of which sends datagrams.

import java.io.IOException;
import java.net.*;

public class ApplicationA extends Thread {
    //
    private DatagramSocket socketToB;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];

    public ApplicationA() {

        try {
            // Auto-assign port number:
            socketToB = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Application A demarré");
    }

    public static void main(String[] args) {
        new ApplicationA().start();
    }

    public void run() {
        try {
            String outMessage = "A";
            // Envoyer A a l'application B
            socketToB.send(Dgram.toDatagram(outMessage, hostAddress, 10121));

            // Recevoir ABC de l'application C
            byte[] bufFromC = new byte[1000];
            DatagramPacket dpFromC =
                    new DatagramPacket(bufFromC, bufFromC.length);
            DatagramSocket socketFromC;
            socketFromC = new DatagramSocket(10120);
            socketFromC.receive(dpFromC);

            // Afficher ABC
            String rcvd = Dgram.toString(dpFromC);
            System.out.println(rcvd);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
} ///:~










