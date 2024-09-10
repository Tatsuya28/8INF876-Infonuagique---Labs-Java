package UDPABCExample;

import java.io.IOException;
import java.net.*;

public class ApplicationB extends Thread {
    //
    private DatagramSocket socketToC;

    private InetAddress hostAddress;

    private byte[] buf = new byte[1000];

    public ApplicationB() {

        try {
            // Auto-assign port number:
            socketToC = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Application B demarré");
    }

    public static void main(String[] args) {
        new ApplicationB().start();
    }

    public void run() {
        try {
            // Recevoir A de l'application A
            byte[] bufFromA = new byte[1000];
            DatagramPacket dpFromA = new DatagramPacket(bufFromA,
                    bufFromA.length);
            DatagramSocket socketFromA;
            socketFromA = new DatagramSocket(10121);
            socketFromA.receive(dpFromA);

            String rcvd = Dgram.toString(dpFromA);
            String outMessage = rcvd + "B";

            // Envoyer AB a l'application C
            socketToC.send(Dgram.toDatagram(outMessage, hostAddress, 10122));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
} ///:~

