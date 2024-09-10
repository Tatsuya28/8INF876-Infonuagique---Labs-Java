package echoTelnet;


import java.io.*;
import java.net.*;
/**
 *
 * Pour tester avec un client :
 * dans les commandes windows taper :telnet localhost 8190
 */
public class EchoServer {

    public static void main(String[] args)
    {
        try
        {
            // Creation du socket serveur
            ServerSocket s = new ServerSocket(8190);

            System.out.println("En attente de connexion du client...\n");
            // Attendre qu'un client se connecte
            Socket incoming = s.accept();
            System.out.println("Client connecte !\n");

            // Creation du "buffer" qui va lire les donnes en provenance du server
            BufferedReader in = new BufferedReader( new InputStreamReader(incoming.getInputStream()));

            // Creation du "out" pour ecrire les output vers le client
            PrintWriter out = new PrintWriter(incoming.getOutputStream(),true);

            out.println("Bonjour ! Tapez BYE pour quitter ");

            // ecouter les reponses du client
            boolean fin = false;

            while (!fin)
            {
                String line = in.readLine();
                if(line == null)
                    fin = true;
                else
                {
                    out.println("Echo: " + line);

                    if (line.trim().equals("BYE"))
                        fin = true;
                }
            } // end while
            incoming.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}