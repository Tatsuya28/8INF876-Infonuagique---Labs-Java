package UDPABCExample;

import java.io.IOException;
import java.net.*;

public class ApplicationC extends Thread {
    //
    private DatagramSocket socketToA;

    private InetAddress hostAddress;

    private byte[] buf = new byte[1000];

    public ApplicationC() {

        try {
            // Auto-assign port number:
            socketToA = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Application C demarré");
    }

    public static void main(String[] args) {
        new ApplicationC().start();
    }

    public void run() {
        try {
            // Recevoir AB de l'application B
            byte[] bufFromB = new byte[1000];
            DatagramPacket dpFromB = new DatagramPacket(bufFromB,
                    bufFromB.length);
            DatagramSocket socketFromB;
            socketFromB = new DatagramSocket(10122);
            socketFromB.receive(dpFromB);

            String rcvd = Dgram.toString(dpFromB);
            String outMessage = rcvd + "C";

            // Envoyer ABC a l'application A
            socketToA.send(Dgram.toDatagram(outMessage, hostAddress, 10120));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
} ///:~

