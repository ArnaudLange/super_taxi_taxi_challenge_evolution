package reseau;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import static java.lang.Thread.sleep;

public class Client
{
    public static void main (String args[])
    {
        ClientEnvoie clientEnvoie;
        ClientReception clientReception;
        OutputStream out;
        InputStream in;

        try
        {
            Socket socket = new Socket("localhost", 2009);
            out = socket.getOutputStream();
            in = socket.getInputStream();

            clientReception = new ClientReception(in);
            clientReception.start();
            clientEnvoie = new ClientEnvoie(out);
            clientEnvoie.start();

            while(clientReception.isAlive())
                sleep(200);

            clientEnvoie.close();
            socket.close();
        }
        catch (IOException e)
        {
            System.err.println("Erreur de connexion avec le serveur");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

